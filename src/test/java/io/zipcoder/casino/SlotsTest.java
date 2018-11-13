package io.zipcoder.casino;

import org.junit.Test;

import static org.junit.Assert.*;

public class SlotsTest {

    String[] reels = new String[5];
//    String[] names = {"Christian","Demetrius", "Jen",
//            "Kate", "Nate", "WIN", "Christian","Demetrius",
//            "Jen",
//            "Kate", "Nate" };


    @Test
    public void randomlyFillReels(){
        Slots slot = new Slots();

        slot.FillReels(5);


    }




}