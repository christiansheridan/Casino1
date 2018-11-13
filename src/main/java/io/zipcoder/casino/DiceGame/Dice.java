package io.zipcoder.casino.DiceGame;

public class Dice {

//    private int[] die;
//    private ArrayList<Dice> dice;

//    public Dice() {
//        this.die = new int[6];
//    }
//
//    public Dice(int numOfDice) {
//        this.dice = new ArrayList<Dice>(numOfDice);
//    }

    public static int rollDie() {
        return (int) Math.floor((Math.random() * 6) + 1);
    }


}
