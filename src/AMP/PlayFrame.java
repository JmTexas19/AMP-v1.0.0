/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolBar;

/**
 *
 * @author Evan
 */
public class PlayFrame extends JToolBar {

    Border blackline = BorderFactory.createLineBorder(Color.black);
    Dimension play = new Dimension(200, 100);
    //JButton hide = new JButton("!");
    ButtonPanel funbags;
   // JPanel center = new JPanel();
    // JButton options = new JButton("+");
    SongPanel current;
    GeneralPlaylist playlist;
   
    public PlayFrame(MP3Player player, ArrayList <GeneralPlaylist> list) throws IOException, InterruptedException {
        funbags = new ButtonPanel(player, list,this);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
       // this.center.setLayout(new BorderLayout());
        Image image = ImageIO.read(new File("disk.jpg"));
        image = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        Song s = new Song();
        s.songArtist = "Artist";
        s.songTitle = "Song";
        playlist = list.get(0);
        
        current = new SongPanel(s);
      
      
        this.add(current);
        this.add(funbags);
       //this.add(center, BorderLayout.CENTER);
        // this.center.add(options, BorderLayout.EAST);

        //his.options.setBackground(Color.yellow);
        this.setSize(play);
        this.setBorder(blackline);
        this.funbags.setBorder(blackline);
        // this.funbags.setBackground(Color.cyan);
        //this.current.setBackground(Color.cyan);
        // this.slider.setBackground(Color.gray);
        repaint();

        //   options.addActionListener(new ActionListener(){
        //     @Override
        //    public void actionPerformed(ActionEvent e){
        // go backwards in playlist using whatever
        //    }
        // });
    }

    public void playLoop() throws InterruptedException {
        do {
            int x = 0;
        } while (true);
    }
    public void resetCurrent(ArrayList <GeneralPlaylist> list) throws IOException{
        int activePL = playlist.getActivePL();
        playlist = list.get(activePL);
        Song song1 = playlist.getCurrent();
        
          current.artistL.setText(song1.songArtist);
          current.titleL.setText(song1.songTitle);
          current.artL.setIcon(song1.ico);
        
}}
