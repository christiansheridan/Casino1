package io.zipcoder.casino.CardGame;

import java.util.Objects;

import static io.zipcoder.casino.CardGame.Face.getFaceByValue;
import static io.zipcoder.casino.CardGame.Suit.getSuitByValue;

public class Card {
//9D on 10S didn't work...
    private Suit suit;
    private boolean isBlack;
    private Face face;
    private boolean covered;

    public Card(Suit suit, Face face){
        this.suit = suit;
        this.face = face;
        setBlack();
    }

//    public static Card toCard(String suit, String face){
//        return new Card(Suit.valueOf(suit.toUpperCase()), Face.valueOf(face.toUpperCase()));
//    }

    public static Card toCard(char face, char suit){
        return new Card(getSuitByValue(suit), getFaceByValue(face));
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public boolean isBlack() {
        return isBlack;
    }

    private void setBlack(){
        if(this.getSuit().toString().equals("HEARTS") || this.getSuit().toString().equals("DIAMONDS")) isBlack = false;
        else isBlack = true;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public boolean isCovered() {
        return covered;
    }

    public void setCovered(boolean covered) {
        this.covered = covered;
    }

    public Card drawCard() {
        return null;
    }

    public void shuffleDeck() {
    }

    @Override
    public String toString(){
        return suit + "-" + face;
    }

    public String toString2(){
        if (this.isCovered()) return "--";
        else return "" + face.getTertiaryValue() + suit.getPrimaryValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit &&
                face == card.face;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, face);
    }
}
