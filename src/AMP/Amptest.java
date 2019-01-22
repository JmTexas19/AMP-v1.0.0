/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMP;

import java.io.IOException;
import java.util.ArrayList;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javazoom.jl.decoder.Equalizer;


/**
 *
 * @author Evan
 */
public class Amptest {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InterruptedException
     * @throws java.lang.IllegalAccessException
     * @throws javax.swing.UnsupportedLookAndFeelException
     * @throws java.io.IOException
     * @throws java.lang.InstantiationException
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException, InterruptedException {
        UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        ArrayList <GeneralPlaylist> playlistHolder = new ArrayList();
        GeneralPlaylist playlist = new GeneralPlaylist();
        playlist.setPlaylistName("Default");
        playlistHolder.add(playlist);
        Equalizer equ = new Equalizer();
        MP3Player music = new MP3Player(equ);
        
        MFrame gui = new MFrame(music, equ, playlistHolder);
        gui.setVisible(true);
        gui.setSize(700, 400);
        gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //gui.fr.playLoop();
      
    }

}
