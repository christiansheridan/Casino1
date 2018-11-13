package io.zipcoder.casino.DiceGame.Craps;

import io.zipcoder.casino.DiceGame.Craps.Craps;
import io.zipcoder.casino.DiceGame.Craps.CrapsPlayers;
import io.zipcoder.casino.DiceGame.DiceGame;
import io.zipcoder.casino.Player;
import org.junit.Assert;
import org.junit.Test;

public class CrapsTest {

    Player Crappy = new Player("Crappy");
    Craps game1 = new Craps(Crappy);


    @Test
    public void rollDiceTest(){
        int actual = game1.rollDice();
        Assert.assertTrue((2 < actual) && (actual < 12));
    }

    @Test
    public void testFirstRollPointer(){
        // making sure that in first roll that result is pointer
        game1.rollDice();
        game1.setPointer(8);
        int expected = 8;
        int actual = game1.getPointer();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFirstRoll_Result(){
        // making sure that in first roll that result is pointer
       // game1.firstRoll();
        game1.setResult(game1.getRollResult());
        int expected = game1.getRollResult();
        int actual = game1.getPointer();
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testFirstRoll_Win() {
        // making sure that in first roll if 7 player wins
        game1.betAmount(10, Crappy);
        game1.setResult(7);
        Crappy.setWallet(Crappy.getWallet() + 10);
        int expected = 510;
        int actual = Crappy.getWallet();
        Assert.assertEquals(expected, actual);

    }


    @Test
    public void testRemainingRolls(){
        // making sure that if remaining = pointer player wins
        game1.remainingRolls();
        game1.setPointer(8);
        game1.setResult(6);

        int expected = 510;
        int actual = Crappy.getWallet();



    }

    @Test
    public void testRemainingRolls_(){
        // making sure that if remaining = !pointer keep rolling dice
        game1.remainingRolls();
        game1.setResult(game1.getRollResult());

        int expected = game1.getRollResult();
        int actual = game1.getPointer();
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testWin(){


    }

    @Test
    public void testLose(){

    }

    @Test
    public void testEnd(){ //**********************

    }

    @Test
    public void testAddPlayer(){
        String expected = "Crappy";
        String actual = Crappy.getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddPlayer_(){
        Player expected = Crappy;
        Player actual = game1.getCrapsPlayer().getPlayer();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemovePlayer(){ //***************

    }

    @Test
    public void testExitTable(){

    }




}
