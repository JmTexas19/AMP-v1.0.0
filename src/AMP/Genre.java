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
public class Genre implements Serializable {

    /**
     * Main Genre with three sub Genres. If more than three subGenres are
     * present, use the three most important ones.
     */
    int mainGenre;

    Genre() {

    }

    Genre(int incomingMain) {
        mainGenre = incomingMain;
    }

}
