package io.zipcoder.casino.CardGame.BlackJack;

import io.zipcoder.casino.Player;

public class Dealer extends Player {

    private final String name;

    public Dealer() {
        name = "Dealer";
    }

    public String getName() {
        return name;
    }

}
