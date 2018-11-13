package io.zipcoder.casino.CardGame.BlackJack;

import io.zipcoder.casino.CardGame.Card;
import io.zipcoder.casino.CardGame.Deck;
import io.zipcoder.casino.CardGame.Face;
import io.zipcoder.casino.CardGame.Suit;
import io.zipcoder.casino.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BlackJackPlayerTest {

    Deck deck = new Deck();
    Player player = new Player("Jack Black");
    BlackJack blackJack = new BlackJack(player);
    BlackJackPlayer testPlayer = blackJack.getPlayer(1);
    ArrayList<Card> testHand = testPlayer.getPlayerHand();

    @Test
    public void testPlayerHasAce_True() {
        Card ace = new Card(Suit.HEARTS, Face.ACE);

        testHand.add(ace);
        testHand.add(deck.draw());
        testHand.add(deck.draw());

        boolean expected = true;
        boolean actual = testPlayer.hasAce();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPlayerHasAce_False() {
        testHand.add(deck.draw());
        testHand.add(deck.draw());
        testHand.add(deck.draw());

        boolean expected = false;
        boolean actual = testPlayer.hasAce();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetDealerHand() {
        ArrayList<Card> dealerHand = blackJack.getPlayer(0).getPlayerHand();
        dealerHand.add(deck.draw());
        dealerHand.add(deck.draw());
        dealerHand.add(deck.draw());

        int expected = 2;
        int actual = blackJack.getPlayer(0).getDealerHand().size();

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void testGetDealerHand_NotDealer() {
        testHand.add(deck.draw());
        testHand.add(deck.draw());
        testHand.add(deck.draw());

        testPlayer.getDealerHand();
    }

    @Test
    public void testAddToBetPot() {

        testPlayer.addToBetPot(50);

        int expected = 50;
        int actual = testPlayer.getBetPot();


        Assert.assertEquals(expected, actual);
    }

}
