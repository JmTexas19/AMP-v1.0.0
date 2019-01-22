/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMP;

import java.io.Serializable;

/**
 *
 * @author Connor
 */
public class Artist implements Serializable {

    /**
     * artist1, 2, and 3 are there for now. If we find that songs commonly have
     * more than three artists, we will either make this an array, or create and
     * artist4, artist5, ect. Worst comes to worst, we make a String "and
     * Others" for the last artist it its like 50 people.
     */
    String artist1;
    String artist2;
    String artist3;

    Artist() {

    }

    Artist(String incomingArt1) {
        artist1 = incomingArt1;
    }

    Artist(String incomingArt1, String incomingArt2) {
        artist1 = incomingArt1;
        artist2 = incomingArt2;
    }

    Artist(String incomingArt1, String incomingArt2, String incomingArt3) {
        artist1 = incomingArt1;
        artist2 = incomingArt2;
        artist3 = incomingArt3;

    }

}
