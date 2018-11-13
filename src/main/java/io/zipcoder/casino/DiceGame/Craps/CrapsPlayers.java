package io.zipcoder.casino.DiceGame.Craps;

import io.zipcoder.casino.Player;

public class CrapsPlayers {

        private Player player;
        private int wallet;
        private int initialBet;
        private int rollValue;
        private int betPot;

        public CrapsPlayers(Player player) {
            this.player = player;
            this.initialBet = 0;
            this.rollValue = 0;
            this.betPot = 0;
            this.wallet = 500;
        }

        public Player getPlayer() {
            return player;
        }

        public void addToBetPot(int amount) {
            betPot += amount;
            this.player.setWallet(player.getWallet()- amount);
        }

        public int getInitialBet() {
            return initialBet;
        }

        public void setInitialBet(int amount){
            this.initialBet = amount;
        }

    public void setWallet(int wallet) {
            this.wallet = wallet;
    }

    public int getWallet() {
            return wallet;
    }
}
