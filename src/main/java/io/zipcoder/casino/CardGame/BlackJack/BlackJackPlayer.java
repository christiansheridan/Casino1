package io.zipcoder.casino.CardGame.BlackJack;

import io.zipcoder.casino.CardGame.Card;
import io.zipcoder.casino.CardGame.Face;
import io.zipcoder.casino.Player;

import java.util.ArrayList;

public class BlackJackPlayer {

    private Player player;

    private int initialBet;
    private int handValue = 0;
    private ArrayList<Card> playerHand = new ArrayList<>();
    private ArrayList<Card> newHand;
    private int betPot = 0;

    public BlackJackPlayer(Player player) {
        this.player = player;
    }

    public void addToHand(Card card){
        this.playerHand.add(card);
    }

    public boolean hasAce() {
        ArrayList<Face> onlyFaces = new ArrayList<>();
        for (Card card : playerHand) {
            onlyFaces.add(card.getFace());
        }
        return onlyFaces.contains(Face.ACE);
    }

    public ArrayList<Card> getPlayerHand() {
        return this.playerHand;
    }

    public ArrayList<Card> createNewHand() {
        newHand = new ArrayList<>();
        return this.newHand;
    }

    public ArrayList<Card> getSecondHand() {
        return this.newHand;
    }

    public ArrayList<Card> getDealerHand() {
        if (this.player.getName().equals("Dealer")) {
            ArrayList<Card> dealerHandRemoveMystery = new ArrayList<>();

            for (int i = 1; i < this.playerHand.size(); i++) {
                dealerHandRemoveMystery.add(this.playerHand.get(i));
            }
            return dealerHandRemoveMystery;
        } else {
            throw new IllegalStateException("This player is not the dealer.");
        }
    }

    public ArrayList<Card> resetHand(){
        this.playerHand = new ArrayList<>();
        return this.playerHand;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getInitialBet() {
        return this.initialBet;
    }

    public void addToBetPot(int amount) {
        betPot += amount;
    }

    public int getBetPot() {
        return this.betPot;
    }

    public void setBetPot(int value) {this.betPot = value; }

    public void setInitialBet(int amount){
        this.initialBet = amount;
    }

    public int getHandValue() {
        return this.handValue;
    }

    public void setHandValue(int value) {
        this.handValue = value;
    }

}
