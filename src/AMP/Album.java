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

/**Album takes care of holding information about a specific songs album. 
 In other words, Album should only be associated with one song. 
 Later developemnt can create a playlist based off of songs with the same Album.
 That playlist will be the album that would have been released to consumers. 
 the track is the track number that is associated with that song in the Album.*/
public class Album implements Serializable{
    String album;
    int songTrack;
    
    Album()
    {
        
    }
    //creates an Album when the track number is unknown
    Album(String incomingAlbum)
    {
        album = incomingAlbum;
    }
    //ideal constructor, where the track number and album name are known
    Album(String incomingAlbum, int incomingTrack)
    {
        album = incomingAlbum;
        songTrack = incomingTrack;
    }
    public String getMyAlbum()
    {
        return album;
    }
}
