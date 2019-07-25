package practic.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import practic.model.Card;
import practic.model.Game;
import practic.model.Player;
import practic.repository.GameRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameRestController {
    private static final String template = "Hello, %s!";

    @Autowired
    private GameRepository gameRepository;




    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, name);

    }

    @CrossOrigin
    @RequestMapping( method= RequestMethod.GET)
    public List<Card> getAll(@RequestParam("gameID") int gameID){

        List<Card> rez=new ArrayList<>();
        for (Game game : gameRepository.findAll()) {
            if(game.getId()==gameID)
            {
                for (Player activePlayer : game.getActivePlayers()) {
                    for (Card card : activePlayer.getCards()) {
                        rez.add(card);
                        System.out.println(card);
                    }
                }
            }
        }
        return rez;


//        for (Game game : gameRepository.findAll()) {
//            if(game.getId()==gameID)
//            for (Player activePlayer : game.getActivePlayers()) {
//                if(activePlayer.getId()==playerID)
//                {
//                    for (Move move : activePlayer.getMoves()) {
//                        moveList.add(move);
//                        System.out.println(move);
//                    }
//                }
//            }
//        }
//
//
//        return moveList;
    }
}
