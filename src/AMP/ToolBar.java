/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMP;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.Equalizer;
/**
 *
 * @author emv5148
 */
public class ToolBar extends JToolBar {
    JButton eq = new JButton("Equalizer");
    JButton create = new JButton("Create Playlist");
    JButton addSong = new JButton("Add Song");
    equFrame equf;
    GeneralPlaylist playlist;
    ArrayList list;
    SListPanel slist;
    AListPanel alist;
    MP3Player music;
    int index = 0;
    public ToolBar(MP3Player player, ArrayList <GeneralPlaylist> list, Equalizer equ, SListPanel slist,PlayFrame frame,AListPanel alist){
        this.setAlignmentY(CENTER_ALIGNMENT);
       // this.setOrientation(JToolBar.VERTICAL);
        this.add(eq);
        this.addSeparator();
        this.add(create);
        this.addSeparator();
        this.list = list;
        this.add(addSong);
        playlist = list.get(0);
        
        music = player;
        this.slist = slist;
        this.alist = alist;
        create.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                
                try {
                    
                    alist.createAlbumPanel(list);
                   
                    repaint();
                    
                } catch (IOException ex) {
                    Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        eq.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            if(equf == null){
                equf = new equFrame(music);
            }
            equf.setVisible(true);
        }
    });
        
        addSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int activePL = playlist.getActivePL();
                playlist = list.get(activePL);
                String path = music.getFile();
                if(path != null){
                Song s = playlist.extractStuff(path);
                    try {
                        addPanel(s);
                    } catch (IOException ex) {
                        Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                if (s != null) {
                    playlist.addSong(s);
                    //if(playlist.size == 1){
                        try {
                            frame.resetCurrent(list);
                        } catch (IOException ex) {
                            Logger.getLogger(ToolBar.class.getName()).log(Level.SEVERE, null, ex);
                   //     }
                    }
                }
                }
                revalidate();

            }
        });
    
        
    }
    public void addPanel(Song s) throws IOException{
        slist.addSongPanel(s);
        
    }
}
