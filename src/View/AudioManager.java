package View;


/***
 * Classe per gestire l'audio all'interno del gioco
 */
import java.io.BufferedInputStream;
import java.io.FileInputStream;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



/**
 * Classe per gestire l'audio all'interno del gioco
 */
public class AudioManager {

    private static AudioManager instance;
    final AudioInputStream[] in = new AudioInputStream[10];
    final Clip clips[] = new Clip[10];


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
        try {
            in[0] = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("src/Music/Bomberman-Just-Died-OST.wav")));
            in[1] = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("src/Music/StageClear.wav")));
            in[2] = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("src/Music/bomberman_battle.wav")));
            in[3] = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("src/Music/placeBombNuovo.wav")));
            in[4] = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("src/Music/bombExplosion.wav")));
            in[5] = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("src/Music/powerup.wav")));
            in[6] = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("src/Music/bomberman_battleMeme.wav")));
            in[7] = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("src/Music/Bomberman_ost1.wav")));

            for(int i = 0; i < 8; i++) {
                clips[i] = AudioSystem.getClip();
                clips[i].open(in[i]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Riproduce un file audio
     * @param i indice del file da riprodurre
     */
    public void play(int i) {
        if(clips[i].isRunning()) clips[i].stop(); // Ferma la riproduzione corrente (se presente)
        clips[i].setFramePosition(0); // Riavvolge il file audio

        clips[i].start();
    }

    /**
     * Ferma la riproduzione corrente
     */
    public void stop(int i) {
        if (clips[i] != null && clips[i].isRunning()) {
            clips[i].stop();
            clips[i].setFramePosition(0);
        }
    }
}