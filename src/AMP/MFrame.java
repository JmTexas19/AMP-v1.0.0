/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMP;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.UnsupportedLookAndFeelException;
import javazoom.jl.decoder.Equalizer;

/**
 *
 * @author Evan
 */
public class MFrame extends JFrame {

    GridBagConstraints c = new GridBagConstraints();
    GridBagLayout gridBag = new GridBagLayout();
    JPanel[] complist = new JPanel[10];
    GeneralPlaylist playlist;
    ListHold hold;

    public MFrame(MP3Player player, Equalizer equ, ArrayList <GeneralPlaylist> list ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException, InterruptedException {
        playlist = list.get(0);
        PlayFrame fr = new PlayFrame(player,list);
      
        this.setLayout(new BorderLayout());
        this.setTitle("AMP_GUI V 6.8");
        //  c.gridwidth = 5;
        // c.gridheight = 5;
        // c.fill = GridBagConstraints.HORIZONTAL; 
        // c.gridx = 3;
        // c.gridy = 5;
        hold = new ListHold(list);
        this.add(fr, BorderLayout.PAGE_END);
        this.add(hold, BorderLayout.CENTER);
        ToolBar tool = new ToolBar(player,list,equ,hold.songListPanel,fr,hold.albumListPanel);
        this.add(tool, BorderLayout.NORTH);
      //  this.add(complist[2], BorderLayout.EAST);
        // complist[1] = (new PlayFrame());
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

    }
}
