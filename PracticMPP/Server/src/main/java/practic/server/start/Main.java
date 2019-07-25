package practic.server.start;

import practic.model.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static List<Card> list=new ArrayList<>();
    static int firstIndexOfCard(String cardValue)
    {
        for (Card card : list) {
            if(card.getValue().equals(cardValue))
            {
                return list.indexOf(card);
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        list.add(new Card("a"));
        list.add(new Card("b"));
        list.add(new Card("a"));


        System.out.println(firstIndexOfCard("c"));
    }
}
