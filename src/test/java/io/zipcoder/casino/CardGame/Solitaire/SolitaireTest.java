package io.zipcoder.casino.CardGame.Solitaire;

import io.zipcoder.casino.CardGame.Card;
import io.zipcoder.casino.Player;
import org.junit.Assert;
import org.junit.Test;

import static io.zipcoder.casino.CardGame.Card.toCard;

public class SolitaireTest {
    Solitaire s = new Solitaire(new Player("Murphy"));

    @Test
    public void testfind() {
        s.resetDeck();
        s.tab1.add(toCard('A', 'H'));
        s.tab2.add(toCard('5', 'D'));

        System.out.println(s.findTab(toCard('A', 'H')).stack.peek());
    }

    @Test
    public void testPull2() {
        s.resetDeck();
        s.tab1.add(toCard('A', 'H'));
        s.tab2.add(toCard('5', 'D'));

        Integer preSize = s.tab1.size();
        s.pull("AH"); //main method tested
        Integer postSize = s.tab1.size();

        Integer actual = preSize - postSize;
        Integer expected = 1;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void drawCard() {
        s.drawCard();
        System.out.println(s.wastePile.peek().toString());
        Assert.assertEquals(1, s.wastePile.size());
    }

    @Test
    public void pullFromWaste() {
        s.resetDeck();
        s.drawCard();
        Card c = s.pickUp().pop();
        String actual = c.toString();
        String expected = "CLUBS-KING";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDeal() {
        s.resetDeck();
        s.deal();
        int i = 0;
        for (Tableau tab : s.arrayTabs) {
            System.out.println("tab " + i);
            i++;
            tab.stack.forEach(e -> System.out.println(e));
        }
        Assert.assertTrue(s.arrayTabs[0].size() == 1);
        Assert.assertTrue(s.arrayTabs[1].size() == 2);
        Assert.assertTrue(s.arrayTabs[2].size() == 3);
        Assert.assertTrue(s.arrayTabs[3].size() == 4);
        Assert.assertTrue(s.arrayTabs[4].size() == 5);
        Assert.assertTrue(s.arrayTabs[5].size() == 6);
        Assert.assertTrue(s.arrayTabs[6].size() == 7);
        Assert.assertTrue(s.solitaireDeck.deckOfCards.size() == (52 - 28));
    }

    @Test
    public void testCardCoverage() {
        //whether top card on each tab is uncovered
        s.resetDeck();
        s.deal();
        int i = 0;
        for (Tableau tab : s.arrayTabs) {
            System.out.println("tab " + i);
            i++;
            tab.stack.forEach(e -> System.out.println(e + " " + e.isCovered()));
        }
        Assert.assertFalse(s.arrayTabs[0].stack.peek().isCovered());
        Assert.assertFalse(s.arrayTabs[1].stack.peek().isCovered());
        Assert.assertFalse(s.arrayTabs[2].stack.peek().isCovered());
        Assert.assertFalse(s.arrayTabs[3].stack.peek().isCovered());
        Assert.assertFalse(s.arrayTabs[4].stack.peek().isCovered());
        Assert.assertFalse(s.arrayTabs[5].stack.peek().isCovered());
        Assert.assertFalse(s.arrayTabs[6].stack.peek().isCovered());
    }

    @Test
    public void testPull(){
        s.resetDeck();
        s.deal();
        Card expected = s.tab1.stack.peek();
        s.pull("KC");
        Card actual = s.tempStack.peek();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testPlace(){
        s.resetDeck();
        s.deal();
        Card expected = s.tab1.stack.peek();
        s.pull("KC");
        s.tab2.add(s.tempStack.pop());
        Card actual = s.tab2.stack.peek();
        s.print();
        Assert.assertEquals(expected,actual);
        s.tempStack.removeAllElements();
    }

    @Test
    public void testShuffle(){
        s.resetDeck();
        Card actual = s.solitaireDeck.deckOfCards.peek();
        s.shuffle();
        Card expected = s.solitaireDeck.deckOfCards.peek();
        Assert.assertNotEquals(expected,actual);
    }

    @Test
    public void peekWaste(){
        s.resetDeck();
        Card actual = s.solitaireDeck.deckOfCards.peek();
        s.drawCard();
        Card expected = s.wastePile.peek();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void resetDeck(){
        s.resetDeck();
        Card actual = s.solitaireDeck.deckOfCards.peek();
        s.shuffle();
        s.resetDeck();
        Card expected = s.solitaireDeck.deckOfCards.peek();
        Assert.assertEquals(expected,actual);
    }
}