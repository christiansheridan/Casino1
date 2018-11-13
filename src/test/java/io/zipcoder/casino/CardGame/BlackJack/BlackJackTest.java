package io.zipcoder.casino.CardGame.BlackJack;

import io.zipcoder.casino.CardGame.Card;
import io.zipcoder.casino.CardGame.Deck;
import io.zipcoder.casino.CardGame.Face;
import io.zipcoder.casino.CardGame.Suit;
import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BlackJackTest {


    Deck deck = new Deck();
    Player player = new Player("Jack Black");
    BlackJack blackJack = new BlackJack(player);
    BlackJackPlayer testPlayer = blackJack.getPlayer(1);
    ArrayList<Card> testHand = testPlayer.getPlayerHand();
    BlackJackGameplay gamePlay = new BlackJackGameplay(blackJack);

    @Test
    public void testHit() {
        blackJack.deal();
        blackJack.hit(testPlayer);

        int expected = 3;
        int actual = testHand.size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testHitAgain_NoBust() {
        Card ace = new Card(Suit.HEARTS, Face.ACE);
        Card two = new Card(Suit.HEARTS, Face.TWO);
        Card six = new Card(Suit.HEARTS, Face.SIX);
        testHand.add(ace);
        testHand.add(two);
        testHand.add(six);

        deck.shuffle();
        blackJack.hit(testPlayer);

        boolean expected = false;
        boolean actual = (testPlayer.getHandValue() > 21);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testHitAgain_BustOrWin() {
        Card queen = new Card(Suit.HEARTS, Face.QUEEN);
        Card king = new Card(Suit.HEARTS, Face.KING);
        testHand.add(queen);
        testHand.add(king);

        deck.shuffle();
        blackJack.hit(testPlayer);

        boolean expected = true;
        boolean actual = (testPlayer.getHandValue() >= 21);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSplit() {
        blackJack.setJustDealt(true);
        Card seven1 = new Card(Suit.HEARTS, Face.SEVEN);
        Card seven2 = new Card(Suit.SPADES, Face.SEVEN);

        testHand.add(seven1);
        testHand.add(seven2);

        blackJack.split(testPlayer);

        Face expected = testPlayer.getPlayerHand().get(0).getFace();
        Face actual = testPlayer.getSecondHand().get(0).getFace();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testBetAmount() {
        int expected = 50;
        int actual = blackJack.betAmount(50, testPlayer);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testBetAmount_BetPotUpdate() {

        blackJack.betAmount(50, testPlayer);
        int expected = 50;
        int actual = testPlayer.getBetPot();


        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDoubleDown() {
        blackJack.betAmount(50, testPlayer);
        blackJack.setJustDealt(true);
        testPlayer.setInitialBet(50);
        blackJack.doubleDown(testPlayer);

        int expected = 100;
        int actual = testPlayer.getBetPot();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDeal_player() {
        blackJack.deal();

        int expected = 2;
        int actual = testHand.size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDeal_dealer() {
        blackJack.deal();

        int expected = 2;
        int actual = blackJack.getDealer().getPlayerHand().size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCountPlayerHand_HasAce_OneOrEleven() {
        Card ace = new Card(Suit.HEARTS, Face.ACE);
        Card two = new Card(Suit.HEARTS, Face.TWO);
        Card six = new Card(Suit.HEARTS, Face.SIX);

        testHand.add(ace);
        testHand.add(two);
        testHand.add(six);

        int total_aceEqualsOne = 9;
        int total_aceEqualsEleven = 19;

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(total_aceEqualsOne);
        expected.add(total_aceEqualsEleven);
        ArrayList<Integer> actual = blackJack.countPlayerHand(testPlayer);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCountPlayerHand_HasAce_CantBeEleven() {
        Card ace = new Card(Suit.HEARTS, Face.ACE);
        Card king = deck.draw();
        Card queen = deck.draw();

        testHand.add(ace);
        testHand.add(king);
        testHand.add(queen);

        int total_aceEqualsOne = 21;      // int total_aceEqualsEleven = 31;

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(total_aceEqualsOne);
        ArrayList<Integer> actual = blackJack.countPlayerHand(testPlayer);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCountPlayerHand_NoAce() {
        Card king = deck.draw();
        Card two = new Card(Suit.HEARTS, Face.TWO);
        Card six = new Card(Suit.HEARTS, Face.SIX);

        testHand.add(king);
        testHand.add(two);
        testHand.add(six);

        int total = 18;

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(total);
        ArrayList<Integer> actual = blackJack.countPlayerHand(testPlayer);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testJustDealt() {
        blackJack.setJustDealt(false);

        boolean expected = false;
        boolean actual = blackJack.getJustDealt();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddPlayer() {
        Player newPlayer = new Player("Dwight White");
        blackJack.addPlayer(newPlayer);

        int expected = 3;
        int actual = blackJack.getBlackJackPlayers().size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemovePlayer() {
        Player newPlayer = new Player("Dwight White");
        blackJack.addPlayer(newPlayer);
        blackJack.removePlayer(newPlayer);

        int expected = 2;
        int actual = blackJack.getBlackJackPlayers().size();

        Assert.assertEquals(expected, actual);
    }

 //////////// keep having trouble with creating new test class (intellij auto-downloads junit 5.2 instead

    @Test
    public void testDetermineActivePlayer_isPlayer() {
        gamePlay.setTurnNumber(2);

        gamePlay.determineActivePlayer(blackJack);
        BlackJackPlayer active = gamePlay.getActivePlayer();

        String expected = "Jack Black";
        String actual = active.getPlayer().getName();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDetermineActivePlayer_isDealer() {
        gamePlay.setTurnNumber(3);

        gamePlay.determineActivePlayer(blackJack);
        BlackJackPlayer active = gamePlay.getActivePlayer();

        String expected = "Dealer";
        String actual = active.getPlayer().getName();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCheckFirstTurnWin_Dealer() {
        gamePlay.getDealer().setHandValue(21);
        testPlayer.setBetPot(50);

        gamePlay.checkFirstTurnWin(blackJack);

        int expected = 400;
        int actual = testPlayer.getPlayer().getWallet();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCheckFirstTurnWin_Player() {
        testPlayer.setHandValue(21);
        testPlayer.setBetPot(50);

        gamePlay.checkFirstTurnWin(blackJack);

        int expected = 600;
        int actual = testPlayer.getPlayer().getWallet();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testTwoStands_PlayerWin() {
        testPlayer.setHandValue(20);
        blackJack.getDealer().setHandValue(17);
        testPlayer.setBetPot(50);
        gamePlay.twoStands(testPlayer);

        int expected = 550;
        int actual = testPlayer.getPlayer().getWallet();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testTwoStands_PlayerLoss() {
        testPlayer.setHandValue(17);
        blackJack.getDealer().setHandValue(20);
        testPlayer.setBetPot(50);
        gamePlay.twoStands(testPlayer);

        int expected = 450;
        int actual = testPlayer.getPlayer().getWallet();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testTwoStands_PlayerTie() {
        testPlayer.setHandValue(17);
        blackJack.getDealer().setHandValue(17);
        gamePlay.twoStands(testPlayer);

        boolean expected = false;
        boolean actual = gamePlay.getFlag();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDealerTurn() {
        blackJack.getDealer().setHandValue(20);
        gamePlay.dealerTurn();

        int expected = 1;
        int actual = gamePlay.getStands();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDealerTurn2() {
        gamePlay.dealerTurn();
        gamePlay.dealerTurn();

        int expected = 2;
        int actual = gamePlay.getStands();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDealerTurn_Turn() {
        gamePlay.setTurnNumber(5);

        gamePlay.dealerTurn();

        int expected = 6;
        int actual = gamePlay.getTurnNumber();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDealerHitResult_DealerLoss() {
        blackJack.getDealer().setHandValue(22);
        gamePlay.dealerHitResult();

        boolean expected = false;
        boolean actual = gamePlay.getFlag();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDealerHitResult_DealerWin() {
        blackJack.getDealer().setHandValue(21);
        gamePlay.dealerHitResult();

        boolean expected = false;
        boolean actual = gamePlay.getFlag();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDealerHitResult_DealerStand() {
        blackJack.getDealer().setHandValue(16);
        gamePlay.dealerHitResult();

        boolean expected = true;
        boolean actual = gamePlay.getFlag();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPlayerChoice_Hit() {
        Card ace = new Card(Suit.HEARTS, Face.ACE);
        Card two = new Card(Suit.HEARTS, Face.TWO);

        testHand.add(ace);
        testHand.add(two);
        gamePlay.playerChoice("HIT");

        int expected = 3;
        int actual = testPlayer.getPlayerHand().size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPlayerChoice_Stand() {
        gamePlay.playerChoice("stand");

        int expected = 1;
        int actual = gamePlay.getStands();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPlayerChoice_DD() {
        testPlayer.setInitialBet(50);
        testPlayer.setBetPot(50);
        blackJack.setJustDealt(true);
        gamePlay.playerChoice("double DOWN");

        int expected = 100;
        int actual = testPlayer.getBetPot();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testHitResult_Win() {
        testPlayer.setHandValue(21);
        testPlayer.setBetPot(50);
        gamePlay.hitResult(testPlayer);

        int expected = 550;
        int actual = testPlayer.getPlayer().getWallet();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testHitResult_Lose() {
        testPlayer.setHandValue(25);
        testPlayer.setBetPot(50);
        gamePlay.hitResult(testPlayer);

        int expected = 450;
        int actual = testPlayer.getPlayer().getWallet();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateWallet() {
        testPlayer.setBetPot(250);

        gamePlay.updateWallet(1, '+');

        int expected = 750;
        int actual = testPlayer.getPlayer().getWallet();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testResetGame_Hand() {
        Card ace = new Card(Suit.HEARTS, Face.ACE);
        Card two = new Card(Suit.HEARTS, Face.TWO);

        testHand.add(ace);
        testHand.add(two);
        gamePlay.resetGame();

        int expected = 0;
        int actual = testPlayer.getPlayerHand().size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testResetGame_BetPot() {
        testPlayer.setBetPot(999999999);
        gamePlay.resetGame();

        int unexpected = 999999999;
        int actual = testPlayer.getBetPot();

        Assert.assertNotEquals(unexpected, actual);
    }


    @Test
    public void testResetGame_Stand(){
        gamePlay.setStands(10);
        gamePlay.resetGame();

        int expected = 0;
        int actual = gamePlay.getStands();

        Assert.assertEquals(expected, actual);
    }

//    @Test
//    public void testAskForBet(){
//        gamePlay.askForBet(testPlayer);
//
//        int expected = 50;
//        int actual = testPlayer.getBetPot();
//
//        Assert.assertEquals(expected, actual);
//    }

//    @Test
//    public void testPlayAgain(){
//        gamePlay.askPlayAgain("YES");
//
//        testPlayer.setBetPot(999999999);
//        gamePlay.resetGame();
//
//        int expected = 0;
//        int actual = testPlayer.getBetPot();
//
//        Assert.assertEquals(expected, actual);
//    }

//    @Test
//    public void testPlayerChoice_Quit(){
//        gamePlay.setFlag(true);
//        gamePlay.playerChoice("Quit");
//
//        boolean expected = false;
//        boolean actual = gamePlay.getFlag();
//
//        Assert.assertEquals(expected, actual);
//    }

}
