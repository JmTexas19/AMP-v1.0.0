/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMP;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
//import javax.
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Evan
 */
public class SListPanel extends JPanel {
   // SongPanelL[] list = new SongPanelL[10];
    JScrollPane scroll = new JScrollPane();
       JPanel sList = new JPanel();
     JLabel title = new JLabel("Default");
    public SListPanel(GeneralPlaylist list) throws IOException{
        scroll.setPreferredSize(new Dimension(400,300)); 
        
        for(Song x:list.songList){
            this.add(new SongPanelL(x));
            
        }
        
        Image image = ImageIO.read(new File("disk.jpg"));
         image = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            sList.setLayout(new BoxLayout(sList, BoxLayout.Y_AXIS));
     
            
            this.add(title);
        
        
        
        scroll.setViewportView(sList);
        //JScrollPane Songs = new JScrollPane(sList);
        scroll.createVerticalScrollBar();
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       //Songs.set
        this.add(scroll);
        
        
      
        
    }
    
    public void addSongPanel(Song x) throws IOException{
        sList.add(new SongPanelL(x));
    }
    
    public void resetPanel(GeneralPlaylist list) throws IOException{
       sList.removeAll();
         for(Song x:list.songList){
            sList.add(new SongPanelL(x));
            
        }
         repaint();
    }
    
    
}
