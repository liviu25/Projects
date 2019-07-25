package practic.server.impl;

import org.springframework.stereotype.Service;
import practic.model.Card;
import practic.model.Game;
import practic.model.Player;
import practic.model.User;
import practic.repository.CardRepository;
import practic.repository.GameRepository;
import practic.repository.PlayerRepository;
import practic.repository.UserRepository;
import practic.service.IObserver;
import practic.service.IServer;
import practic.service.ServiceException;

import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class GameServer implements IServer {

    private Map<String, IObserver> loggedClients;
    private UserRepository userRepository;
    private GameRepository gameRepository;
    private PlayerRepository playerRepository;
    private CardRepository cardRepository;
    private Game currentGame;
    private List<String> allCards;

//    private Map<Integer,Game> games;


    public GameServer(UserRepository userRepository, GameRepository gameRepository,
                      PlayerRepository playerRepository, CardRepository cardRepository) {
        this.userRepository=userRepository;
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.cardRepository=cardRepository;
        loggedClients=new ConcurrentHashMap<>();
        currentGame =new Game();
//        games=new HashMap<>();
        gameRepository.save(currentGame);
//        games.put(currentGame.getId(),currentGame);
        allCards=new ArrayList<>();
        allCards.add("J");
        allCards.add("J");
        allCards.add("J");

        allCards.add("Q");
        allCards.add("Q");
        allCards.add("Q");

        allCards.add("K");
        allCards.add("K");
        allCards.add("K");

        allCards.add("Joker");

    }


    @Override
    public User login(String username, String password) throws ServiceException {

        User user = userRepository.findOne(username);

        if(user.getPassword().equals(password))
        {
            return user;
        }
        else throw new ServiceException("Authentication failed.");
    }

    @Override
    public void addObeserver(User user, IObserver client) throws ServiceException {
        if(user!=null)
        {
            if (loggedClients.get(user.getUsername()) != null)
                throw new ServiceException("User already logged in.");
            loggedClients.put(user.getUsername(), client);
            System.out.println("User "+user.getUsername()+" logged in.");
        }
    }

    @Override
    public void logout(User user, IObserver client) throws ServiceException {
        IObserver localClient=loggedClients.remove(user.getUsername());
        if (localClient==null)
            throw new ServiceException("User "+user.getUsername()+" is not logged in.");
        System.out.println("User "+user.getUsername()+" logged out.");
    }






    @Override
    public void joinGame(String username) throws ServiceException {


        System.out.println(currentGame.getId());

        if(currentGame.getActivePlayers().size()==0)
        {
            Player player=new Player((username));
            player.setGame(currentGame);
            currentGame.getActivePlayers().add(player);
            System.out.println(username+" joined");
        }
        else if(currentGame.getActivePlayers().size()==1)
        {
            Player player=new Player((username));
            player.setGame(currentGame);
            currentGame.getActivePlayers().add(player);
            System.out.println(username+" joined");

        }
        else if(currentGame.getActivePlayers().size()==2)
        {
            Player player=new Player((username));
            player.setGame(currentGame);
            currentGame.getActivePlayers().add(player);
            System.out.println(username+" joined");
            startGame();
        }
        else if(currentGame.getActivePlayers().size()==3)
        {
            throw new ServiceException("Game started.");
        }
    }




    public void notifyPlayer()
    {
        ExecutorService executor= Executors.newFixedThreadPool(4);

        Player player= currentGame.getActivePlayers().get(currentGame.getCurrentPlayer());
        String username=player.getUsername();


        if(username != null)
        {
            System.out.println(username+"'s turn");
            IObserver client=loggedClients.get(username);
            executor.execute(() ->{
                try {
                    client.notifyMove();
                }
                catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }

    private void nextPlayer()
    {


        if(currentGame.getCurrentPlayer()==2)
        {
            currentGame.setCurrentPlayer(0);
        }
        else
            currentGame.setCurrentPlayer(currentGame.getCurrentPlayer()+1);
    }


    public void startGame() {
        Collections.shuffle(allCards);
        int k=0;
        for (int i = 0; i < 3; i++) {
            Player player = currentGame.getActivePlayers().get(i);
            for (int j = 0; j < 3; j++) {
                List<Card> cards = player.getCards();
                Card card=new Card(allCards.get(k));
                card.setPlayer(player);
                cards.add(card);
                k++;
            }
        }
        Player firstPlayer = currentGame.getActivePlayers().get(0);
        Card card=new Card(allCards.get(k));
        card.setPlayer(firstPlayer);
        firstPlayer.getCards().add(card);



        ExecutorService executor= Executors.newFixedThreadPool(4);

        System.out.println("Game started");
        for (Player player : currentGame.getActivePlayers()) {
            String username=player.getUsername();
            if(username != null)
            {

                IObserver client=loggedClients.get(username);
                executor.execute(() ->{
                    try {
                        client.gameStarted();
                    }
                    catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        executor.shutdown();
        if(gameIsFinished())
            notifyGameFinished();
        else
            notifyPlayer();
    }

    private int firstIndexOfCard(List<Card> list,String cardValue)
    {
        for (Card card : list) {
            if(card.getValue().equals(cardValue))
            {
                return list.indexOf(card);
            }
        }
        return -1;
    }

    @Override
    public void makeMove(String username, String cardValue) {

        Player player = currentGame.getActivePlayers().get(currentGame.getCurrentPlayer());
        List<Card> cards = player.getCards();
        int index = firstIndexOfCard(cards, cardValue);
        cards.remove(index);

        if(gameIsFinished())
            notifyGameFinished();
        else {
            nextPlayer();

            Card card=new Card(cardValue);
            card.setPlayer(currentGame.getActivePlayers()
                    .get(currentGame.getCurrentPlayer()));
            currentGame.getActivePlayers()
                    .get(currentGame.getCurrentPlayer())
                    .getCards().add(card);


            gameRepository.update(currentGame);


            notifyPlayer();
        }
    }


    private boolean gameIsFinished() {

        for (int i = 0; i < 3; i++) {
            Player player = currentGame.getActivePlayers().get(i);
            List<Card> cards = player.getCards();
            String firstCardValue=cards.get(0).getValue();
            boolean ok=true;
            for (Card card : cards) {
                if(!card.getValue().equals(firstCardValue))
                {
                    ok=false;
                }
                System.out.println(card.getValue());
            }
            if(ok)
            {
                return true;
            }
            System.out.println("---");

        }

        return false;
    }

    @Override
    public List<String> loadCards(String username) {
        for (Player activePlayer : currentGame.getActivePlayers()) {
            if(activePlayer.getUsername().equals(username))
            {
                List<Card> cards = activePlayer.getCards();
                List<String> cardValues=new ArrayList<>();
                for (Card card : cards) {
                    cardValues.add(card.getValue());
                }
                return cardValues;
            }
        }
        return null;
    }

    @Override
    public String getWinner() {
        return currentGame.getWinner();
    }

    @Override
    public List<String> getInGamePlayers() {
        List<String> rez=new ArrayList<>();
        for (Player activePlayer : currentGame.getActivePlayers()) {
            if(currentGame.getActivePlayers().get(currentGame.getCurrentPlayer()).getUsername().equals(activePlayer.getUsername()) )
                rez.add(activePlayer.getUsername()+"'s turn");
            else
                rez.add(activePlayer.getUsername());
        }
        return rez;
    }

    public void notifyGameFinished() {

        currentGame.setWinner(currentGame.getActivePlayers().get(currentGame.getCurrentPlayer()).getUsername());
        gameRepository.update(currentGame);

        ExecutorService executor= Executors.newFixedThreadPool(4);

        System.out.println("Game finished");
        for (Player player : currentGame.getActivePlayers()) {
            String username=player.getUsername();
            if(username != null)
            {
                System.out.println("User "+username+" notified game finished");
                IObserver client=loggedClients.get(username);
                executor.execute(() ->{
                    try {
                        client.gameFinished();
                    }
                    catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        executor.shutdown();
    }
}
