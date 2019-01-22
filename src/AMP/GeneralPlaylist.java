/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMP;//Change package to amp First Blagh

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Connor
 * @author Joe Barnes
 */
public class GeneralPlaylist {
    //Constructor 
    
   Image image; 
    public GeneralPlaylist() throws IOException {
        Name = "MainPlaylist";
        myFile = new File("MainPlaylist.obj");
        songList = new ArrayList<Song>();
        active = 0;
     image  = ImageIO.read(new File("disk.jpg"));
   image = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
    }

    public GeneralPlaylist(String URL, String Name1) throws IOException {
        Name = Name1;
        myFile = new File(URL +".obj");
        songList = new ArrayList<Song>();
        active = 0;
        image  = ImageIO.read(new File("deadkoala.jpg"));
   image = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
    }
    
    
    private int SL;         
    public int active;         //Currently active song 
    static public int activePlaylist; // fuck conventions
    public int id;
    //Playlist name to display when selecting playlist, this is decided inconstructor or when making the file, 
    String Name;  
    
    //Location of song list
    File myFile;
    Mp3File file;
    ArrayList<Song> songList;
    ArrayList<ArrayList> playlistList;      //Array List for Playlist
    
    private String playlistName;
    
    public void setPlaylistName(String s)
    {
        playlistName = s;
    }
    
    public String getPlaylistName()
    {
        return playlistName;
    }

    public void removeSong(String T)// Removes first song that matches Title
    {
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).songTitle == null ? T == null : songList.get(i).songTitle.equals(T)) {
                songList.remove(i);
                i = 10000;
            }
            //update size and active position, if active position would be negative reset it
            
            if (active > 0) {
                active--;
            }
        }
    }

    public void removeSong()// Removes song at active position assuming there are songs in the playlist
    {
        if (active > 0) {
            //remove command
        }
        //update size and active position, if active position would be negative reset it
        if (active > 0) {
            active--;
        }
    }

    /// List Traversal Methods 
    public int getActive() //returns the Index of the currently active song
    {
        return active;
    }

    int nextSong(int i, int l, boolean s) {
        if (s) {
            int a = getActive();
            Random rand = new Random();
            i = rand.nextInt(l);
            while (a == i) {
                rand = new Random();
                i = rand.nextInt(l);
            }
            active = i;
        } else {
            if (i < (l - 1)) {
                i++;
                active++;
            } else {
                i = 0;
                active = 0;
            }
        }
        return i;
    }

    int previousSong(int i, int l) {
        if (i > 0) {
            i--;
            active--;
        } else {
            i = l - 1;
            active = i;
        }

        return i;
    }

    public Song getCurrent()//returns the currently active song
    {
        return songList.get(active);
    }

    public void addSong(Song S) {
        songList.add(S);
        System.out.print(songList.size());
    }

    //File Manipulation Methods meant for saving and loading the song
    //I would change this slightly. 
    /*Have it call on JFileChooser, and save the arrayList line by line. Then have 
    load read in line by line, and BOOM*/
    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream(myFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(songList);
        } catch (FileNotFoundException ex) {
            try {
                myFile.createNewFile();
            } catch (IOException ex1) {
                System.out.println("System can not create file, and there is no file there already notify developers pls");
                Logger.getLogger(GeneralPlaylist.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(GeneralPlaylist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void load() throws ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(myFile);
            try {
                ObjectInputStream ois = new ObjectInputStream(fis);
                songList = (ArrayList<Song>) ois.readObject();
                active = 0;
            } catch (IOException ex) {
                Logger.getLogger(GeneralPlaylist.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneralPlaylist.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //Extract MP3 Information from file
    public Song extractStuff(String path){
        try {
            file = new Mp3File(path);
            byte[] imageBytes;
            String title = ""; 
            String artist = "";
            String album = "";
            ImageIcon icon = null;
            int genre = 0;
            SL = (int) file.getLengthInSeconds();
            String year = "";
            long SLB = file.getLength();
            
            //ID3v1
            if (file.hasId3v2Tag()) {
                ID3v2 v2 = file.getId3v2Tag();
                title = v2.getTitle();
                artist = v2.getArtist();
                album = v2.getAlbum();
                genre = v2.getGenre();
                year = v2.getYear();
                imageBytes = v2.getAlbumImage();
                Image img;
                
                try {
                    img = ImageIO.read(new ByteArrayInputStream(imageBytes));
                    SLB = SLB - imageBytes.length;
                    img = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
                    icon = new ImageIcon(img);
                }
                catch (NullPointerException e){
                    img  = ImageIO.read(new File("deadkoala.jpg"));
                    img = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
                    icon = new ImageIcon(img);
                }
            }
            else if (file.hasId3v1Tag()) {
                ID3v1 v1 = file.getId3v1Tag();
                title = v1.getTitle();
                artist = v1.getArtist();
                album = v1.getAlbum();
                genre = v1.getGenre();
                year = v1.getYear();
                Image img;
                img  = ImageIO.read(new File("deadkoala.jpg"));
                img = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
                icon = new ImageIcon(img);
                System.out.print("BARLEY");
                
            } //ID3v2
            
            //Create new song object with all information
            Song S = new Song(path, title, artist, album, genre, year, SL, SLB, icon);
            return S;

        }
        catch (IOException | UnsupportedTagException | InvalidDataException ex) {
            return null;
        }
    }
    
    void setActivePL(){
        activePlaylist = id;
        
    }
    int getActivePL(){
        return activePlaylist;
        
    }
}


//                imageBytes = v2.getAlbumImage();
//                OutputStream out = null;
//
//                try {
//                    out = new BufferedOutputStream(new FileOutputStream(path));
//                    out.write(imageBytes);
//                }               
//                finally {
//                    if (out != null) out.close();
//                }
//                BufferedInputStream bis = 
//                Image img = ImageIO.read(null);
//                        // BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
//                icon = new ImageIcon(img);   