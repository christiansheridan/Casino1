package io.zipcoder.casino;

import java.util.Scanner;

public class Slots {

    String[] reels = new String[5];
    String[] names = {"♡", "♢", "♤",
            "♧", "♬", "WIN", "♡", "♢", "♤",
            "♧", "♬", "Kris", "Nhu", "Froilan",
            "Dolio",
            "Leon", "Wilhem", "WOW", "ZCW", "Sian", "Dan", "Nancy", "Melanie", "Tariq", "Janelle", "Jutta"};

    Casino casino = Casino.getInstance();
    Player player;

    public Slots() {
        int bet;
        boolean flag = false;
        this.player = Casino.getPlayer();
    }

//    public static void main(String[] args) {
//
////        Slots s = new Slots();
////        s.startSlots();
//
//
//    }

    public void startSlots() {
        String choice;
        int bet;
        int pot;
        Scanner scan = new Scanner(System.in);
        System.out.println("What would you like to do?");
        System.out.println("Type -Play- or -Quit-:");
        choice = scan.nextLine().toLowerCase();

        switch (choice) {
            case "quit":
                System.out.println("Goodbye");
                casino.chooseGame();
                break;
            case "play":
                bet = placeYourBet();
                player.setWallet(player.getWallet() - bet);
                FillReels(bet);
                // player.setWallet(player.getWallet() + pot);

                break;

            default:
                startSlots();

        }

    }

    private int placeYourBet() {
        Scanner scan = new Scanner(System.in);
        System.out.println("How much would you like to bet?");
        System.out.println("Type a number between 5 - 100 chips:");
        return scan.nextInt();

    }


    public void FillReels(int bet) {
        String choice;

        int win = 0;
        int zcw = 0;

        int compareReelNum = 0;
        int payOut;
        printScreen1();
        System.out.print("///\t\t\t|");
        for (int i = 0; i < reels.length; i++) {
            reels[i] = names[(int) (Math.random() * 26)];

            if (i > 0) {
                if (reels[i] == reels[i - 1]) {
                    compareReelNum++;
                }
            }

            if (reels[i] == "WIN") {
                win++;
            }
            if (reels[i] == "ZCW") {
                zcw++;
            }

            System.out.printf("| %8s |", reels[i]);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }

            if (i == reels.length - 1) {
                System.out.println("|\t\t|\t\t///\t | |");
            }
        }

        printScreen2();


        payOut = amountWon(bet, win, zcw, compareReelNum);

        System.out.println("\t\t\t\t\t\t\t\t\tYour bet:\t" + bet + "\tCHIPS");
        if (payOut > 0) {
            System.out.println("\t\t\t\t\t\t\t\t\tYOU WON:\t" + payOut + "\tCHIPS");
            player.setWallet(player.getWallet() + payOut);
        }

        startSlots();
    }


    private int amountWon(int bet, int win, int zcw, int compareReelNum) {
        int jackpot = 0;

        int winTotal = ((bet * 2) * win);

        int zcwTotal = ((bet * 5) * zcw);

        if (compareReelNum == 4) {
            jackpot = 1000000;
            System.out.println("YOU HIT THE JACKPOT!!!!!!");

        }


        return winTotal + zcwTotal + jackpot;
    }

    private void printScreen1() {
        System.out.println(" ////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("///																				        ///");
        System.out.println("///								    THE THUNDER DOME							        ///");
        System.out.println("///																				        ///");
        System.out.println("///		    _____________________________________________________________________       ///  ___");
        System.out.println("///		    |																	|	    /// (   )");
        System.out.println("///		    |																	|	    ///	 | |");
        System.out.println("///		    |																	|	    ///	 | |");

    }

    private void printScreen2() {
        System.out.println("///		    |																	|	    ///__| | S   ");
        System.out.println("///		    |																	|	    ///____| P   ");
        System.out.println("///		    |___________________________________________________________________|	    ///      I   ");
        System.out.println("///																	                    ///      N   ");                                                                            ///
        System.out.println("///																                        ///");                                                                            ///
        System.out.println(" /////    	 															            /////");                                                                            ///
        System.out.println("  //////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("  /////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("  /////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("  /////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("  /////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("  /////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("  /////////////////////////////////////////////////////////////////////////////////////");
    }
}
