package io.zipcoder.casino.DiceGame.Craps;

import io.zipcoder.casino.CardGame.Solitaire.Tableau;
import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Console;
import io.zipcoder.casino.DiceGame.Dice;
import io.zipcoder.casino.DiceGame.DiceGame;
import io.zipcoder.casino.Interfaces.Gamble;
import io.zipcoder.casino.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Craps extends DiceGame implements Gamble {
    Scanner scanner;

    private Casino casino = Casino.getInstance();
    private int rollNumber = 0;
    private CrapsPlayers crapsPlayer;
    private int minBet;
    private Player player;
    private Dice dice;
    private int pointer;
    private int result;
    private int totalWin;


    public Craps(Player player) {
        CrapsPlayers crappyPlayer = new CrapsPlayers(player);
        this.crapsPlayer = new CrapsPlayers(player);
        this.minBet = 10;
        this.dice = new Dice();
        //this.result = 0;
        scanner = new Scanner(System.in);
    }

    public void gamePlay() {
        crapsPlayer.setWallet(crapsPlayer.getWallet());
        System.out.println("What would you like to bet? Min bet is: $" + minBet);
        int amount = scanner.nextInt();
        if (amount < minBet) {
            System.out.println("Sorry but the minimum bet is $" + minBet);
            gamePlay();
        }
        crapsPlayer.setInitialBet(amount);
        System.out.println("Are you ready to roll?  yes or no");
        String response = scanner.next();
        if (response.equalsIgnoreCase("yes")) {
            firstRoll();
            remainingRolls();
        } else if (response.equalsIgnoreCase("no")) {
            // gamePlay();
            end();
        } else {
            System.out.println("no valid");
        }
    }

    public int rollDice() {
        int sum = rollDice(2);
        System.out.println("Total = " + sum);
        System.out.println("______________");
        setResult(sum);
        return sum;
    }

    public void firstRoll() {
        int result = rollDice();
        if (result == 7 || result == 11) {
            win(crapsPlayer);
        } else if (result == 2 || result == 3 || result == 12) {
            lose(crapsPlayer);
        } else {
            pointer = result;
        }
    }

    public void remainingRolls() {
        System.out.println("The pointer is: " + pointer);
        System.out.println("Are you ready to roll?  yes or no");
        String response = scanner.next();
        if (response.equalsIgnoreCase("yes")) {
            int result = rollDice();
            if (result == pointer) {
                win(crapsPlayer);
            } else if (result == 7) {
                lose(crapsPlayer);
            } else {
                remainingRolls();
            }
        } else if (response.equalsIgnoreCase("no")) {
            System.out.println("would you like to exit?");
            String response2 = scanner.next();
            if (response2.equalsIgnoreCase("yes")) {
                exitTable(crapsPlayer);
                casino.chooseGame();
            } else if (response2.equalsIgnoreCase("no")) {
                gamePlay();
            }
        } else {
            System.out.println("not valid");
        }
    }

    public int getRollResult()
    {
        return this.result;
    }

    public int betAmount(int amount, Player player) {
        return amount;
    }


    public void win(CrapsPlayers crapsPlayers) {
        System.out.println("Congrats! You won: $" + crapsPlayers.getInitialBet());
        totalWin = crapsPlayers.getInitialBet();
        crapsPlayers.setWallet(crapsPlayers.getWallet() + totalWin);
        displayWallet();
        playAgain();
    }

    public void lose(CrapsPlayers crapsPlayers) {
        System.out.println("I'm so sorry, you lose!");
        crapsPlayers.setWallet(crapsPlayers.getWallet() - crapsPlayers.getInitialBet());
        displayWallet();
        playAgain();
    }

    public void playAgain() {
        System.out.println("Would you like to play again?");
        String response = scanner.next();
        if (response.equalsIgnoreCase("yes")) {
            start();
        } else if (response.equalsIgnoreCase("no")) {
            end();
        } else {
            System.out.println("Sorry I didn't quite get that, try again!");
        }
    }

    public void start() {
        gamePlay();
    }

    public void end() {
        System.out.println("Would you like to leave the table?");
        String response = scanner.next();
        if (response.equalsIgnoreCase("yes")) {
            exitTable(crapsPlayer);
            casino.chooseGame();
        } else if (response.equalsIgnoreCase("no")) {
            start();
        }

    }

    public void exitTable(CrapsPlayers crapsPlayer) {
        removePlayer(player);
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void addPlayer(Player player) {
        CrapsPlayers crappyPlayer = new CrapsPlayers(player);
        this.crapsPlayer = crappyPlayer;
    }

    public void removePlayer(Player player) {
        if (crapsPlayer.getPlayer() == player) {
            this.crapsPlayer = null;
        }
    }

    public CrapsPlayers getCrapsPlayer() {
        return crapsPlayer;
    }

    public void displayWallet() {
        System.out.println("You have: $" + crapsPlayer.getWallet());
    }
}
