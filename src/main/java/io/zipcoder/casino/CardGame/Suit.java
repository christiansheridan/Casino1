package io.zipcoder.casino.CardGame;

public enum Suit {
    HEARTS('H'),
    SPADES('S'),
    DIAMONDS('D'),
    CLUBS('C');

    private char primaryValue;

    private Suit(char primaryValue) {
        this.primaryValue = primaryValue;
    }

    public char getPrimaryValue(){
        return primaryValue;
    }

    public static Suit getSuitByValue(char primaryValue){
        Suit[] suits = Suit.values();
        for (Suit suit : suits)
            if (suit.primaryValue == primaryValue)
                return suit;
        return null;
    }
}
