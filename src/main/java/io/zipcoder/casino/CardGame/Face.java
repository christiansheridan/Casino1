package io.zipcoder.casino.CardGame;

public enum Face {

    ACE(1, 11,'A'),
    TWO(2,2,'2'),
    THREE(3,3,'3'),
    FOUR(4,4,'4'),
    FIVE(5,5,'5'),
    SIX(6,6,'6'),
    SEVEN(7,7,'7'),
    EIGHT(8,8,'8'),
    NINE(9,9,'9'),
    TEN(10,10,'T'),
    JACK(11, 10,'J'),
    QUEEN(12, 10,'Q'),
    KING(13, 10,'K');

    private int primaryValue;
    private int secondaryValue;
    private char tertiaryValue;

    Face(int primaryValue, int secondaryValue, char tertiaryValue) {
        this.primaryValue = primaryValue;
        this.secondaryValue = secondaryValue;
        this.tertiaryValue = tertiaryValue;
    }

    Face(int primaryValue) {
        this.primaryValue = primaryValue;
        this.secondaryValue = primaryValue;     // even though they're the same, you wanna set it bc if you accdidentally call getSecondary on something w/o an initialized secondary then it'l return null
    }

    public int getPrimaryValue() {
        return primaryValue;
    }

    public int getSecondaryValue() {
        return secondaryValue;
    }

    public char getTertiaryValue() { return tertiaryValue; }

    public static Face getFaceByValue(char tertiaryValue){
        Face[] faces = Face.values();
        for (Face face : faces)
            if (face.tertiaryValue == tertiaryValue)
                return face;
        return null;
    }
}
