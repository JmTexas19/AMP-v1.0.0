/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//change package before use
package AMP;

import java.io.File;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * @author Joseph
 * @author Connor
 */
public class Song implements Serializable {

    /*Note: The block below this comment are all objects for when sorting/searching 
     are to be done. */
    String songTitle;
    Album songAlbum;
    File f;

    /*For now, the Artist object has three different places for Artist, artist1,
     artist2, and artist3. If more are needed, this could be made into an array, 
     or more artist fields can be introduced.*/
    String songArtist;
    int songGenre; //Has one Main Genre, and three sub Genres
    String songYear;
    //These can be primitave types, as they will be Read-Only most likely.
    String songComment;
    int songLength;
    long songLengthBytes;
    ImageIcon ico;
    
    //Initializes all Subclasses  ALL OF THEM

    String path;

    Song() {
        songTitle = "";
        songAlbum = new Album();
        songArtist = "";
        songGenre = 0;
        songYear = "";
    }

    Song(String Inpath, String inTitle, String inArtist, String inAlbum, int inGenre, String inYear, int sL, long SLB, ImageIcon img) {
        path = Inpath;
        songTitle = inTitle;
        songAlbum = new Album(inAlbum);
        songArtist = inArtist;
        songGenre = inGenre;
        songYear = inYear;
        songLength = sL;
        songLengthBytes = SLB;
        ico = img;
    }

    public String getAlbum() {
        return songAlbum.getMyAlbum();
    }
    
    
}
