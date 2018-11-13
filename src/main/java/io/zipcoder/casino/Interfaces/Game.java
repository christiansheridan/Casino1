package io.zipcoder.casino.Interfaces;

import io.zipcoder.casino.Player;

public interface Game {
    void start();

    void end();

//    void takeATurn();

    void addPlayer(Player player);

    void removePlayer(Player player);
}
