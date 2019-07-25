package practic.service;

import practic.model.User;

import java.util.List;

public interface IServer {
    User login(String username, String password) throws ServiceException;

    void addObeserver(User user, IObserver client) throws ServiceException;

    void logout(User user, IObserver client) throws ServiceException;

    void joinGame(String username) throws ServiceException;
    void startGame();

    void makeMove(String username,String card);

    List<String> loadCards(String username);

    String getWinner();

    List<String> getInGamePlayers();
}
