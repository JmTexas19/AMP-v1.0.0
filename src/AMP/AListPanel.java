/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMP;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Evan
 */
public class AListPanel extends JPanel {
    
    JScrollPane scroll = new JScrollPane();
    
    JPanel aList = new JPanel();
    SListPanel sList;
    public AListPanel(ArrayList <GeneralPlaylist> list, SListPanel sList) throws IOException{
        scroll.setPreferredSize(new Dimension(400,300)); 
        this.sList = sList;
       
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        aList.setLayout(new BoxLayout(aList, BoxLayout.Y_AXIS));
        this.add(new JLabel("List of Albums and Playlists"));
        
        scroll.setViewportView(aList);
        //JScrollPane Albums = new JScrollPane(sList);
        scroll.createVerticalScrollBar();
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       //Albums.set
        AlbumPanelL defaultword = new AlbumPanelL(list.get(0), sList);
       
        aList.add(defaultword);
        defaultword.indexs = 0;
        this.add(scroll);
        scroll.getVerticalScrollBar().setVisible(false); 
       
        
        
       this.addMouseListener(new MouseListener() {
                @Override
                public void mouseEntered(MouseEvent ce) {
                  scroll.getVerticalScrollBar().setVisible(true);
                    
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
                 scroll.getVerticalScrollBar().setVisible(false);               
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            });
    }

   public void createAlbumPanel( ArrayList <GeneralPlaylist> list) throws IOException{
       int x = list.size();
       GeneralPlaylist newPL = new GeneralPlaylist();
       
       newPL.id = x;
       newPL.setPlaylistName(JOptionPane.showInputDialog("Input the desired name of the new Playlist."));
       AlbumPanelL newPanel = new AlbumPanelL(newPL,sList);
       newPanel.indexs = x;
       list.add(newPL);
       aList.add(new AlbumPanelL(newPL, sList));
       repaint();
       
   }
}
