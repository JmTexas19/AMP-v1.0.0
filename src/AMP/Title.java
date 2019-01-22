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
public class Title implements Serializable {

    String title;

    Title() {

    }

    //creates the song title
    Title(String incomingTitle) {
        title = incomingTitle;
    }

    public String getTitle() {
        return title;
    }
}
