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
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Evan
 */
public class SongPanel extends JPanel {
    Dimension size = new Dimension(200,100);
    Dimension size2 = new Dimension(100,100);
   Dimension skip = new Dimension(40,40);
    JPanel info = new JPanel();
    JLabel artistL;
    JLabel titleL;
    JLabel artL;
    
    public SongPanel(Song song) throws IOException {
Image image = ImageIO.read(new File("disk.jpg"));
         image = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        artistL = new JLabel(song.songArtist);
         titleL = new JLabel(song.songTitle);
        artL = new JLabel(new ImageIcon(image));
       
        this.add(artL);
        info.add(titleL);
        info.add(artistL);
        this.add(new Box.Filler(skip,skip,skip));
        this.add(info);
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        titleL.setMaximumSize(size);
        titleL.setMinimumSize(size);
        artistL.setMaximumSize(size);
        artistL.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setMaximumSize(size);
        this.setAlignmentX(LEFT_ALIGNMENT);
       // this.setResizeable(false);
        //this.setMaximumSize(size);
        
        this.setBorder(blackline);

    }
}
