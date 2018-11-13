package io.zipcoder.casino;

public class Main {
    public static void main(String[] args) {
        String playerName = Console.getStringInput("\n\nWelcome to Thunder Theta! \nThe best Casino in the area with THREE FULL GAMES and A SLOT MACHINE! \n\nWhat is your name?");
        Player player = new Player(playerName);
        Casino casino = new Casino(player);
        casino.chooseGame();
    }
}
