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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AlbumPanelL extends JButton {
    //JButton play = new JButton("SELECT");
    //JButton shuffle = new JButton("SHUFFLE");
    Dimension space = new Dimension(40,40);
    Dimension space2 = new Dimension(9000,60);
     Dimension space3 = new Dimension(100,100);
     int indexs;
     public AlbumPanelL(GeneralPlaylist list, SListPanel sList) throws IOException{
           Image image = ImageIO.read(new File("disk.jpg"));
         image = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
           Border blackline = BorderFactory.createLineBorder(Color.black);
           JLabel artL = new JLabel(new ImageIcon(image));
           JLabel albumL = new JLabel(list.getPlaylistName());
          albumL.setMaximumSize(space3);
          albumL.setMinimumSize(space3);
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(artL);
        this.add(new Box.Filler(space,space,space));
        this.add(albumL);
        this.add(new Box.Filler(space,space,space));
      //  this.add(play);
        this.add(new Box.Filler(space,space,space));
      //  this.add(shuffle);
     //   play.setVisible(false);
      //  shuffle.setVisible(false);
        this.add(new Box.Filler(space,space,space));
        //this.setLayout(new FlowLayout());
        //this.setAlignmentX(LEFT_ALIGNMENT);
        //this.setSize(100,100);
        this.setMaximumSize(space2);
        this.setBorder(blackline);
           this.addMouseListener(new MouseListener() {
                @Override
                public void mouseEntered(MouseEvent ce) {
                //    shuffle.setVisible(true);
               //     play.setVisible(true);
                    
                }

            @Override
            public void mouseClicked(MouseEvent me) {
                
                list.setActivePL();
                    try {
                        sList.resetPanel(list);
                        sList.title.setText(list.getPlaylistName());
                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    } catch (IOException ex) {
                        Logger.getLogger(AlbumPanelL.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
            //    shuffle.setVisible(false); 
             //   play.setVisible(false);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            });
     
       /*shuffle.addMouseListener(new MouseListener() {
                @Override
                public void mouseEntered(MouseEvent ce) {
              //      shuffle.setVisible(true);
                     play.setVisible(true);
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
                shuffle.setVisible(false); 
                 play.setVisible(false);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            });*/
     }           

}
