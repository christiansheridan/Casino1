package io.zipcoder.casino.CardGame;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    public Stack<Card> deckOfCards = new Stack<>();

    public void shuffle(){
        Collections.shuffle(deckOfCards);
    }

    public Card draw(){
       return deckOfCards.pop();
    }

    public Card flip(){
        return deckOfCards.peek();
    }

    // needs 1-13 of each suit
    // assign each index to a specific card,
    // e.g. 0-12 = spades; 13-25 = clubs; 26-38 = hearts; 39-51 = diamonds

    public Deck() {
        for (Suit suit : Suit.values()) {
            for (Face face : Face.values()) {
                deckOfCards.push(new Card(suit, face));
            }
        }
    }
}
