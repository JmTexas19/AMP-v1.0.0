/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import static java.lang.Thread.sleep;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javazoom.jl.decoder.JavaLayerException;

public class ButtonPanel extends JPanel {
    JPanel  playfunc = new JPanel();
    JPanel slider = new JPanel();
    JButton playButton = new JButton("PLAY");
    JButton foward = new JButton(">");
    JButton back = new JButton("<");
    JCheckBox shuffle = new JCheckBox("shuffle");
    JButton addSong = new JButton("Add Song");
    Border blackline = BorderFactory.createLineBorder(Color.black);
    //MP3Player music = new MP3Player();
   

   // @Override
   // public void setMaximumSize(Dimension maximumSize) {
   //     super.setMaximumSize(maximumSize); //To change body of generated methods, choose Tools | Templates.
   // }
    int check;
    MP3Player music;
    JProgressBar progressBar = new JProgressBar();
    boolean playing = false;
    JButton equButton = new JButton("equalizer");
    //GeneralPlaylist playlist = new GeneralPlaylist();
    //Timer t = new Timer();
    GeneralPlaylist playlist;
    int time;
    public int progressBarValue;
    int percent = 0;
    int secondsSong;
    PlayFrame frame;
    ArrayList<GeneralPlaylist> aList;
    
    Dimension size = new Dimension(400,100);
    
    public ButtonPanel(MP3Player player, ArrayList <GeneralPlaylist> list, PlayFrame frame) {
        this.setLayout(new BorderLayout());
        playfunc.add(progressBar);
       // this.add(slider,BorderLayout.WEST);
        this.setBorder(blackline);
        playfunc.add(back);
        playfunc.add(playButton);
        playfunc.add(foward);
        playfunc.add(shuffle);
        this.add(playfunc, BorderLayout.CENTER);
        this.frame = frame;
        this.setMaximumSize(size);
        this.setMinimumSize(size);
        aList = list;
       // this.add(equButton);       
        music = player;
        playlist = list.get(0);
        int activePL = playlist.getActivePL();
        playlist = list.get(activePL);
        new Thread() {
            public void run() {
                while(true){
                    while(playlist.songList.size() == 0){
                        try {
                            sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try {
                        sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String songLength = String.valueOf(playlist.getCurrent().songLength);
                    int position = music.getPosition((playlist.getCurrent().songLengthBytes));
                    progressBar.setValue(position);
                    progressBar.setStringPainted(true);
                    secondsSong = (int)(((double)position/100.0) * (double)(playlist.getCurrent().songLength/10));
                    progressBar.setString(getSongLengthString(secondsSong) + "/" + getSongLengthString());
                }
            }
        }.start();
        
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 int activePL = playlist.getActivePL();
                 playlist = list.get(activePL);
                //Play Song
                if (check == 0) {
                    playing = true;
                    if (playlist.songList.size() != 0) {
                        if (music != null) {                            
                            try {
                            //Check if player is playing
                            //String songLength = String.valueOf(music.file.getLengthInSeconds());
                            music.playSong(playlist.getCurrent(), getThis());
                            progressBar.setMaximum(1000);
                            progressBar.setValue(0);               
                            progressBar.setStringPainted(true);
                            //progressBar.setString(songLength);                           
                                                            
                            } catch (JavaLayerException ex) {
                                Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            check = 1;
                            playButton.setText("PAUSE");
                        } else {
                            JOptionPane.showMessageDialog(null, "No Song Selected\n");
                        }
                    } else {
                        //Do Nothing
                    }
                } //Pause Song
                else if (check == 1) {
                    playButton.setText("PLAY");
                    playing = false;
                    music.pauseSong(playlist.getCurrent(), getThis());
                    check = 2;
                } //Resume Song
                else if (check == 2) {
                    playing = true;
                    music.resumeSong();
                    check = 1;
                    playButton.setText("PAUSE");
                }
            }
        });

        foward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 int activePL = playlist.getActivePL();
                 playlist = list.get(activePL);
                // go backwards in playlist using whatever
                forward();
                try {
                    frame.resetCurrent(aList);
                } catch (IOException ex) {
                    Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 int activePL = playlist.getActivePL();
                 playlist = list.get(activePL);
                // go fowards in playlist using whatever
                if (playlist.songList.size() != 0) {
                        int active = playlist.getActive();
                        int size = playlist.songList.size();
                        music.stopSong();
                        progressBar.setValue(0);
                        playButton.setText("PAUSE");
                    try {
                        sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        playlist.previousSong(active, size);
                        if(playing){
                            playButton.setText("PAUSE");
                            check = 1;
                            try {
                                music.playSong(playlist.getCurrent(), getThis());
                            } catch (JavaLayerException ex) {
                                Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else{
                            playButton.setText("PLAY");
                            check = 0;
                        }
                try {
                    frame.resetCurrent(aList);
                } catch (IOException ex) {
                    Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                } 
                    }
                
                 else {
                    //Do Nothing
                }
            }
        });

        
        
	progressBar.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                
                 int activePL = playlist.getActivePL();
                 playlist = list.get(activePL);
                if(playing){
                if (playlist.songList.size() != 0) {
                    int mouseX = e.getX();
                    progressBarValue = (int)Math.round(((double)mouseX * 100) / ((double)progressBar.getWidth()) * 10);
                    secondsSong = (int)(((double)progressBarValue / 100) * ((double)playlist.getCurrent().songLength));
                    progressBar.setValue(progressBarValue);  
                    percent = progressBarValue;
                    music.seekSong((progressBarValue), playing, playlist.getCurrent(), getThis());
                    //progressBar.setString(String.valueOf(secondsSong/10) + ":" + String.valueOf(music.file.getLengthInSeconds()));
                } else {
                    progressBar.setValue(0);
                }
                try {
                    sleep(50);
                            } catch (InterruptedException ex) {
                    Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    
	}
    public void forward(){
                 int activePL = playlist.getActivePL();
                   playlist = aList.get(activePL);
                if (playlist.songList.size() != 0) {
                    int active = playlist.getActive();
            	    progressBar.setValue(0);
                    int size = playlist.songList.size();
                    boolean s = shuffle.isSelected();
                    //Stop Current Song and Play Next Song
                    music.stopSong();
                    try {
                        sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    playlist.nextSong(active, size, s);
                    if(playing){
                            try {
                                music.playSong(playlist.getCurrent(), getThis());
                            } catch (JavaLayerException ex) {
                                Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            playButton.setText("PAUSE");
                            check = 1;
                        }
                        else{
                            playButton.setText("PLAY");
                            check = 0;
                        }

                } else {
                    //Do Nothing
                }
    }
    private ButtonPanel getThis(){
        return this;
    }
    public String getSongLengthString(){
        
        int activePL = playlist.getActivePL();
        playlist = aList.get(activePL);
        if(!playlist.songList.isEmpty()){
            int s1 = playlist.getCurrent().songLength;
            int s2 = s1 / 60;
            s1 = s1 % 60;
            String songLengthString = String.valueOf(s2) + ":" + String.valueOf(s1);
            return songLengthString;
        }
        else{
            return "0:00";
        } 
    }
    public String getSongLengthString(int secondsPassed){
        int s1 = secondsPassed;
        int s2 = s1/60;
        String songLengthString;
        s1 = s1 % 60;
        if(s1 < 10){
            songLengthString = String.valueOf(s2) + ":" + "0" + String.valueOf(s1);
        }
        else{
            songLengthString = String.valueOf(s2) + ":" + String.valueOf(s1);
        }
        return songLengthString;
    }
}
