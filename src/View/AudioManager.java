package View;
import java.net.URL;
import javax.sound.sampled.*;

/***
 * Classe per gestire l'audio all'interno del gioco
 */
public class AudioManager {

    private Clip clip;
    URL[] soundURL = new URL[10];
    static AudioManager instance = null;

    /***
     * Costruttore, carica le diverse OST per poterle utilizzare
     */
    private AudioManager(){
        try {
            soundURL[0] = getClass().getResource("/Music/Bomberman_ost1.wav"); //menu
            soundURL[1] = getClass().getResource("/Music/bomberman_battle.wav"); //partita
            soundURL[2] = getClass().getResource("/Music/hammer.wav"); //martello boss
            soundURL[3] = getClass().getResource("/Music/placeBombNuovo.wav"); //piazxza bomba
            soundURL[4] = getClass().getResource("/Music/bombExplosion.wav"); //esplosione bomba
            soundURL[5] = getClass().getResource("/Music/powerup.wav"); //powerup
            soundURL[6] = getClass().getResource("/Music/StageClear.wav"); //vittoria
            soundURL[7] = getClass().getResource("/Music/BombermanDies.wav"); //morte bomberman
            soundURL[8] = getClass().getResource("/Music/bomberman_battleMeme.wav"); //rickroll
            soundURL[9] = getClass().getResource("/Music/Bomberman-Just-Died-OST.wav"); //gameover
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Setta l'OST desiderata
     * @param i l'indice dell'OST che si vuole riprodurre
     */
    public void play(int i) {
        stop(); // Ferma la riproduzione corrente (se presente)

        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(sound);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Fa iniziare l'OST
     */


    /***
     * Fa fermare l'OST
     */
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    public static AudioManager getInstance(){
        if(instance == null){
            instance = new AudioManager();
        }
        return instance;
    }
}
