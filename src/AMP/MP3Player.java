package AMP;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Decoder;
import com.mpatric.mp3agic.*;
import static java.lang.Thread.sleep;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.Equalizer;
import javazoom.jl.decoder.Header;
//These two require JavaZoom
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.player.*;

public class MP3Player {

    //Create Objects
    InputStream IS;
    Player player;
    Decoder dec;
    Bitstream bstream;
    Equalizer equ = new Equalizer();
    AudioDevice audio = new JavaSoundAudioDevice();
    boolean paused = false;
    boolean stopped = false;
    String path;
    Mp3File file;
    SampleBuffer output;
    ButtonPanel button;
    int writecount;
    int SL;

    //Global Variables
    public long pauseLocation;
    public long songLength = 0;
    public String resumeLocation;

    public MP3Player(Equalizer equ){
        this.equ = equ;
    }
    //Play Song
    public void playSong(Song s, ButtonPanel b) throws JavaLayerException, IOException {
        //try{ 
        stopped = false;
        paused = false;
        dec = new Decoder();
        dec.setEqualizer(equ);
        FactoryRegistry r = FactoryRegistry.systemRegistry();
        audio = r.createAudioDevice();
        audio.open(dec);
        path = s.path;
        if (path == null) {
            JOptionPane.showMessageDialog(button.frame, "Couldn't find song");
        } else {
            try {
                IS = new FileInputStream(path);        //Path of file in filestream
                songLength = IS.available();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
            }
            bstream = new Bitstream(IS);
        }

        new Thread() {
            public void run() {
                try {
                    boolean ret = true;
                    while (ret && !stopped) {
                        ret = decodeFrame();  
                        if(ret == false){
                            b.forward();
                        }
                        while(paused && !stopped)
                        {
                            Thread.sleep(1);
                        }                   
                    }
                } catch (JavaLayerException ex) {
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }
    //catch(IOException | JavaLayerException e){
    public void resumeSong(){
        paused = false;
    }
    //Stop Song (Not Pause)
    public void stopSong() {
       //Close
        //songLength = 0;
        stopped = true;
    }

    public void pauseSong(Song s, ButtonPanel b) {
        stopped = true;
        try {
            sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        int seek;
        double temp;
        audio.close();
        try {
            
            temp = (((double)IS.available() + 25000)/ (double)songLength) * 1000.0;
            seek = 1000 - (int) temp;
            seekSong(seek, false, s, b); 
        } catch (IOException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    //User chooses song
    public String getFile() {
        try {
            JFileChooser fc = new JFileChooser();

            //Filter
            FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 File", "mp3");
            fc.setFileFilter(filter);
            File file = fc.getSelectedFile();

            int returnVal = fc.showOpenDialog(null);    //Open Window

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File songFile = fc.getSelectedFile();       //Get File
                String song = songFile + "";         //File to string path
                return song;
            }
            else{
                JOptionPane.showMessageDialog(null, "No Song Selected");
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Invalid Song");
        }

        return null;
    }

    public void seekSong(int seekPosition, boolean playing, Song s, ButtonPanel b) {     //Seeks position in song   (0 - 100) 
        stopSong();
        audio.close();
        try {
            sleep(20);
        } catch (InterruptedException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        dec = new Decoder();
        dec.setEqualizer(equ);
        FactoryRegistry r = FactoryRegistry.systemRegistry();
        try {
            audio = r.createAudioDevice();
            audio.open(dec);
        } catch (JavaLayerException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(playing){
            paused = false;
        }
        else{
            paused = true;
        }
        stopped = false;
        if(path == null){
            path = s.path;
        }
        try {
            IS = new FileInputStream(path);        //Path of file in filestream
            songLength = IS.available();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        double seekPercent = (seekPosition * .1) / 100;        //Convert seekPosition to double
        int seek = (int) (seekPercent * songLength);            //Mulitiply by songLength (bytes) and convert back to int
        
        try {
            IS.skip(seek);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        }     
        bstream = new Bitstream(IS);
        new Thread() {
            public void run() {
                try {
                    boolean ret = true;
                    while (ret && !stopped) {
                        while(paused && !stopped){
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }       
                        ret = decodeFrame();
                        if(ret == false){
                            b.forward();
                        }
                    }
                } catch (JavaLayerException ex) {
                   
                }
            }
        }.start();               
    }
    
    public void setEqualizer(float dbShift1, float dbShift2, float dbShift3, float dbShift4, float dbShift5, float dbShift6, float dbShift7, float dbShift8) {
        equ.setBand(1, dbShift1);
        equ.setBand(2, dbShift1);
        equ.setBand(3, dbShift1);
        equ.setBand(4, dbShift1);
        equ.setBand(5, dbShift2);
        equ.setBand(6, dbShift2);
        equ.setBand(7, dbShift2);
        equ.setBand(8, dbShift2);
        equ.setBand(9, dbShift3);
        equ.setBand(10, dbShift3);
        equ.setBand(11, dbShift3);
        equ.setBand(12, dbShift3);
        equ.setBand(13, dbShift4);
        equ.setBand(14, dbShift4);
        equ.setBand(15, dbShift4);
        equ.setBand(16, dbShift4);
        equ.setBand(17, dbShift5);
        equ.setBand(18, dbShift5);
        equ.setBand(19, dbShift5);
        equ.setBand(20, dbShift5);
        equ.setBand(21, dbShift6);
        equ.setBand(22, dbShift6);
        equ.setBand(23, dbShift6);
        equ.setBand(24, dbShift6);
        equ.setBand(25, dbShift7);
        equ.setBand(26, dbShift7);
        equ.setBand(27, dbShift7);
        equ.setBand(28, dbShift7);
        equ.setBand(29, dbShift8);
        equ.setBand(30, dbShift8);
        equ.setBand(31, dbShift8);
        equ.setBand(32, dbShift8);
        if(dec != null)
            dec.setEqualizer(equ);
        try {
            if(audio != null)
            audio.open(dec);
        } catch (JavaLayerException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resetEqualizer() {
        equ.reset();
        dec.setEqualizer(equ);
        try {
            audio.open(dec);
        } catch (JavaLayerException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected boolean decodeFrame() throws JavaLayerException {
        try {
            if(!audio.isOpen()){
            FactoryRegistry r = FactoryRegistry.systemRegistry();
            audio = r.createAudioDevice();
            }
            audio.open(dec);
            audio.open(dec);
            dec.setEqualizer(equ);
            if (audio == null) {
                return false; 
            }
            Header h;
            h = bstream.readFrame();

            if (h == null) {
                return false;
            }
            // sample buffer set when decoder constructed
            output = (SampleBuffer) dec.decodeFrame(h, bstream);
            synchronized (this) {
                audio.open(dec);
                if (audio != null) {
                    audio.write(output.getBuffer(), 0, output.getBufferLength());
                }
            }

            bstream.closeFrame();
        } catch (RuntimeException ex) {
            throw new JavaLayerException("Exception decoding audio frame", ex);
        }
        
        return true;
        
    }
    
    public int getPosition(long slength){
        int position = 0;
        if(songLength != 0){
            try {
                double temp;
                temp = ((double) IS.available() / (double) slength) * 1000.0;
                
                position = 1000 - (int) temp;
            } catch (IOException ex) {
                Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return position;
    }
}