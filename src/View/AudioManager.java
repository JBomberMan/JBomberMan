package View;
import java.net.URL;
import javax.sound.sampled.*;

/***
 * Classe per gestire l'audio all'interno del gioco
 */
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * Classe per gestire l'audio all'interno del gioco
 */
public class AudioManager {

    private static AudioManager instance;

    private Clip clip;

    /**
     * Restituisce l'istanza di AudioManager
     * @return l'istanza di AudioManager
     */
    public static AudioManager getInstance() {
        if (instance == null)
            instance = new AudioManager();
        return instance;
    }

    /**
     * Costruttore privato di AudioManager
     */
    private AudioManager() {

    }

    /**
     * Riproduce un file audio
     * @param filename il path del file audio da riprodurre
     */
    public void play(String filename) {
        //stop(); // Ferma la riproduzione corrente (se presente)

        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filename));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Ferma la riproduzione corrente
     */
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
