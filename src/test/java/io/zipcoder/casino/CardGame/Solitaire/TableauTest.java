package io.zipcoder.casino.CardGame.Solitaire;

import io.zipcoder.casino.CardGame.Card;
import io.zipcoder.casino.CardGame.Deck;
import io.zipcoder.casino.Player;
import org.junit.Assert;
import org.junit.Test;

import static io.zipcoder.casino.CardGame.Card.toCard;
import static io.zipcoder.casino.CardGame.Solitaire.Solitaire.lastStack;
import static io.zipcoder.casino.CardGame.Solitaire.Solitaire.tempStack;

public class TableauTest {
    private Solitaire s = new Solitaire(new Player("df"));

    private Tableau stack1 = new Tableau();
    private Tableau stack2 = new Tableau();
    private Deck testDeck = new Deck();

    @Test
    public void addTest() {
        stack1.add(testDeck.draw());
        stack1.add(testDeck.draw());
        stack1.add(testDeck.draw());
        stack1.add(testDeck.draw());

        Integer actual = stack1.size();
        Integer expected = 4;

        for(Card c : stack1.stack) System.out.println(c.toString());
        Assert.assertEquals(expected,actual);
        tempStack.removeAllElements();
    }

    @Test
    public void pullTest() {
        stack1.add(testDeck.draw());
        stack1.add(testDeck.draw());
        stack1.add(testDeck.draw());
        stack1.add(testDeck.draw());

        stack1.pull(toCard('Q', 'C'));
        Integer actual = tempStack.size();
        Integer expected = 3;

        for(Card c : stack1.stack) System.out.println(c.toString());
        Assert.assertEquals(expected,actual);
        tempStack.removeAllElements();
    }

    @Test
    public void placeTest() {
        stack1.add(testDeck.draw ());
        stack1.add(testDeck.draw());
        stack1.add(testDeck.draw());
        stack1.add(testDeck.draw());

        stack2.add(toCard('K', 'H'));
        stack1.pull(toCard('Q', 'C'));
        lastStack = stack1.stack;
        stack2.place();
        Integer actual = stack2.size();
        Integer expected = 4;

        stack2.stack.forEach(e -> System.out.println(e));
        Assert.assertEquals(expected,actual);
        tempStack.removeAllElements();
    }

    @Test
    public void placeTest2() {
        stack1.add(testDeck.draw());
        stack1.add(testDeck.draw());
        stack1.add(testDeck.draw());
        stack1.add(testDeck.draw());

        stack2.add(toCard('K', 'H'));
        stack1.pull(toCard('J', 'C'));
        lastStack = stack1.stack;
        stack2.place();
        Integer actual = stack2.size();
        Integer expected = 1;

        Assert.assertEquals(expected,actual);
        tempStack.removeAllElements();
    }

    @Test
    public void placeTest3() {//on empty stack
        stack1.add(testDeck.draw());


        stack1.pull(toCard('K', 'C'));
        lastStack = stack1.stack;
        stack2.place();
        Integer actual = stack2.size();
        Integer expected = 1;

        Assert.assertEquals(expected,actual);
        tempStack.removeAllElements();
    }

    //tests whether top card on the pulled-from stack is uncovered after pulling
    @Test
    public void unCoverTest() {
        stack1.add(testDeck.draw());
        stack1.add(testDeck.draw());
        stack1.add(testDeck.draw());
        stack1.stack.forEach(e -> e.setCovered(true));
        stack1.add(testDeck.draw());
        stack1.stack.peek().setCovered(false);

        for(Card c : stack1.stack) System.out.println("Stack 1 " + c.toString() + " " + c.isCovered());
        System.out.println("*******");

        stack2.add(toCard('Q', 'H'));

        stack1.pull(toCard('J', 'C'));
        lastStack = stack1.stack;
        stack2.place();

//        for(Card c : stack2.stack) System.out.println("Stack 2 " + c.toString() + " " + c.isCovered());
//        System.out.println();
//        for(Card c : stack1.stack) System.out.println("Stack 1 " + c.toString() + " " + c.isCovered());
//        System.out.println();
//        for(Card c : tempStack) System.out.println("tempStack " + c.toString() + " " + c.isCovered());
        Boolean actual = stack1.stack.peek().isCovered();
        Assert.assertFalse(actual);
        tempStack.removeAllElements();
    }

//    @Test
//    public void unCoverTest2() {
//        stack1.add(testDeck.draw());
//        stack1.add(testDeck.draw());
//        stack1.add(testDeck.draw());
//        stack1.stack.forEach(e -> e.setCovered(true));
//        stack1.add(testDeck.draw());
//        stack1.stack.peek().setCovered(false);
//
//        for(Card c : stack1.stack) System.out.println("Stack 1 " + c.toString() + " " + c.isCovered());
//        System.out.println("\n** Before **** After **\n");
//
//        stack2.add(toCard('K', 'H'));
//
//        stack1.pull(toCard('Q', 'C'));
//        stack2.place();
////        for(Card c : stack1.stack) System.out.println("Stack 1 " + c.toString() + " " + c.isCovered());
////        System.out.println();
////        for(Card c : stack2.stack) System.out.println("Stack 2 " + c.toString() + " " + c.isCovered());
////        System.out.println();
////        for(Card c : tempStack) System.out.println("tempStack " + c.toString() + " " + c.isCovered());
//        Boolean actual = stack1.stack.peek().isCovered();
//        Assert.assertFalse(actual);
//        tempStack.removeAllElements();
//    }
}