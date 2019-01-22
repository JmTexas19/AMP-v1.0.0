/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author Evan
 */
public class SongPanelL extends JButton {

    //JButton play = new JButton("PLAY");
    Dimension space = new Dimension(40, 40);
    Dimension space2 = new Dimension(9000,60);
    Dimension space3 = new Dimension(100,100);
    public SongPanelL(Song song) throws IOException {
        //this.setBackground(Color.decode("#dddfd4"));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        Image image = ImageIO.read(new File("deadkoala.jpg"));
         image = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        // play.setBackground(Color.code("#3fb0ac"));
        JLabel artistLabel = new JLabel(song.songArtist);
        JLabel titleLabel = new JLabel(song.songTitle);
        JLabel artLabel = new JLabel(song.ico);
        // artistLabel.setFont(new Font("Serif",Font.PLAIN, 15));
        //artistL.setForeground(Color.ORANGE);
        //titleL.setFont(new Font("Serif",Font.PLAIN, 15));
        // titleLabel.setForeground(Color.ORANGE);
        titleLabel.setMaximumSize(space3);
        titleLabel.setMinimumSize(space3);
        artistLabel.setMaximumSize(space3);
        artistLabel.setMinimumSize(space3);
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(artLabel);
        this.add(new Box.Filler(space, space, space));
        this.add(titleLabel);
        this.add(new Box.Filler(space, space, space));
        this.add(artistLabel);
        this.add(new Box.Filler(space, space, space));
      //  this.add(play);
      //  play.setVisible(false);
        this.add(new Box.Filler(space, space, space));
       // this.setLayout(new FlowLayout());
        //this.setAlignmentX(LEFT_ALIGNMENT);
        this.setMaximumSize(space2);
        this.setBorder(blackline);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent ce) {
             //   play.setVisible(true);

            }

            @Override
            public void mouseClicked(MouseEvent me) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent me) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent me) {
           //     play.setVisible(false);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
       

    }

}
