/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMP;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author emv5148
 */
public class ListHold extends JToolBar {
    CardLayout cl = new CardLayout();
    JButton album = new JButton("PLAYLISTS");
    JButton songs = new JButton("SONGS");
    JPanel bhold = new JPanel();
    JPanel inHold = new JPanel();
    SListPanel songListPanel;
    AListPanel albumListPanel;
    public ListHold(ArrayList <GeneralPlaylist> list) throws IOException{
        inHold.setLayout(cl);
        bhold.add(album);
        bhold.add(songs);
        songListPanel= new SListPanel(list.get(0));
        albumListPanel = new AListPanel(list, songListPanel);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        inHold.add(songListPanel, "1");
        inHold.add(albumListPanel, "2");
        Color back = songs.getBackground();
        this.add(bhold);
        this.add(inHold);
        
        cl.show(inHold, "1");
        songs.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cl.show(inHold, "1");
                songs.setBackground(new Color(252, 143, 27));
                album.setBackground(back);
                songs.setForeground(Color.BLACK);
                album.setForeground(Color.WHITE);
            }
            
        });
           album.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cl.show(inHold, "2");
                album.setBackground(new Color(252, 143, 27));
                songs.setBackground(back);
                album.setForeground(Color.BLACK);
                songs.setForeground(Color.WHITE);
                
            }
            
        });
    }
    
    
}
