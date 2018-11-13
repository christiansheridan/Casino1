package io.zipcoder.casino.DiceGame;

import io.zipcoder.casino.Interfaces.Game;

public abstract class DiceGame implements Game {

    private int numOfDice = 2;
    private int[] die = new int[numOfDice];
    private char one = '⚀';
    private char two = '⚁';
    private char three = '⚂';
    private char four = '⚃';
    private char five = '⚄';
    private char six = '⚅';

    public void displayDice(){

    }

    public int rollDice(int numOfDice) {
        int sum = 0;

        for (int i = 0; i < numOfDice; i++) {
            die[i] = (int) Math.floor((Math.random() * 6) + 1);
            sum += die[i];
            if (die[i] == 1) {
                System.out.println(one);
            } else if (die[i] == 2) {
                System.out.println(two);
            } else if (die[i] == 3) {
                System.out.println(three);
            } else if (die[i] == 4) {
                System.out.println(four);
            } else if (die[i] == 5) {
                System.out.println(five);
            } else if (die[i] == 6) {
                System.out.println(six);
            }


        }
        return sum;
    }
}