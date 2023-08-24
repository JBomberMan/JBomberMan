package View;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class AudioManager {
    //audio manager to manage music on panels
    Clip clip;
    URL[] soundURL = new URL[2];

    public AudioManager(){
        try {
            soundURL[0] = getClass().getResource("/Music/Bomberman_ost1.wav");
            soundURL[1] = getClass().getResource("/Music/bomberman_battle.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFile(int i){
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
