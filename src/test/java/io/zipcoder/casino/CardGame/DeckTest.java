package io.zipcoder.casino.CardGame;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    Deck deck = new Deck();

    @Test
    public void testDeck() {
        String actual = deck.deckOfCards.get(0).toString();
        String expected = "HEARTS-ACE";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDraw() {
        String actual = deck.draw().toString();
        String expected = "CLUBS-KING";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testShuffle() {
        deck.shuffle();
        String actual = deck.deckOfCards.get(0).toString();
        String expected = "HEARTS-ACE";

        Assert.assertNotEquals(expected, actual);

        String actual1 = deck.deckOfCards.get(1).toString();
        String expected1 = "HEARTS-TWO";

        Assert.assertNotEquals(expected1, actual1);
    }

    @Test
    public void testDeck3() {
        Deck deck2 = new Deck();
        for (Card c : deck2.deckOfCards) {
            System.out.println(c.toString());
        }

        deck2.shuffle();
        System.out.println("*******");
        for (Card c : deck2.deckOfCards) {
            System.out.println(c.toString());
        }
    }
}