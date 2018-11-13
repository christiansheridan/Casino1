package io.zipcoder.casino;

// BlackJack
// Solitaire
// Craps
// Roulette?
// Slot Machine?

// Jackpot option? Low chance but player wins $1mil
//
//import io.zipcoder.casino.CardGame.BlackJack.BlackJack;
//import io.zipcoder.casino.DiceGame.Craps.Craps;
//import io.zipcoder.casino.CardGame.Solitaire.Solitaire;
//import io.zipcoder.casino.Interfaces.Game;
//
//public class Casino {
//
//    private int money;
//    private String casinoName;
//    private Game game;
//    private Player player;
//    private String currentGame;
//
//    public void selectGame(int gameNum) {
//
//        switch (gameNum) {
//            case 1:
//                Game blackJack = new BlackJack(player);
//                break;
//            case 2:
//                Game solitaire = new Solitaire(player);
//                break;
//            case 3:
//                Game craps = new Craps(player);
//                break;
//            case 4:
//                leaveCasino();
//                break;
//            default:
//                System.out.println("Input unknown, please pick again.");
//                break;
//        }
//    }
//
//    public void leaveCasino() {
//    }
//
//}


import io.zipcoder.casino.CardGame.BlackJack.BlackJack;
import io.zipcoder.casino.CardGame.BlackJack.BlackJackGameplay;
import io.zipcoder.casino.CardGame.BlackJack.Console_BlackJack;
import io.zipcoder.casino.CardGame.Solitaire.Solitaire;
import io.zipcoder.casino.DiceGame.Craps.Craps;

public final class Casino {

    private final static Casino instance = new Casino();

    private static Player player = new Player();

    private String casinoName;

    public Casino() {
    }

    public Casino(Player player) {
        this.casinoName = "Thunder Theta";
        this.player = player;
    }

    public void chooseGame() {
        Player player = Casino.getPlayer();
        String userInput = Console.getStringInput("\nHi "+ player.getName() + "! What game would you like to play?\n\n<< BlackJack - Solitaire - Craps - Slots - Leave >>");
        String input = userInput.toUpperCase();

        boolean flag = true;

        while (flag) {
            switch (input) {
                case "SOLITAIRE":
                    Solitaire s = new Solitaire(player);
                    s.start();
                    flag = false;
                    break;
                case "BLACKJACK":
                    BlackJack blackJack = new BlackJack(player);
                    BlackJackGameplay blackJackGameplay = new BlackJackGameplay(blackJack);
                    Console_BlackJack.blackJackWelcome();
                    blackJackGameplay.start();
                    flag = false;
                    break;
                case "CRAPS":
                    Craps craps = new Craps(player);
                    craps.gamePlay();
                    flag = false;
                    break;
                case "SLOTS":
                    Slots slot = new Slots();
                    slot.startSlots();
                    flag = false;
                    break;
                case "LEAVE":
                    Console_BlackJack.finalGoodbye(player);
                    flag = false;
                    break;
                default:
                    Console_BlackJack.inputError();
                    this.chooseGame();
                    break;
            }
        }
    }

    // public void setPlayer(Player player) {
//        this.player = player;
//    }

    public static Player getPlayer() {
        return player;
    }

    public String getCasinoName() {
        return this.casinoName;
    }

    public static Casino getInstance() {
        return instance;
    }

}


