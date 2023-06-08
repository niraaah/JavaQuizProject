package MainManager;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class BGMManager implements Runnable{

    private String MusicPath;
    private Clip clip;

    public BGMManager(String MusicPath){
        this.MusicPath = MusicPath;
    }

    public void run(){
        try {
            clip = AudioSystem.getClip();
            File audioFile = new File(MusicPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip.open(audioStream);
        }
        catch (LineUnavailableException e) { e.printStackTrace(); }
        catch (UnsupportedAudioFileException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }

        clip.start();

        do {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (clip.isActive());
    }
}