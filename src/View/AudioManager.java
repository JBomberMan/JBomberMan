package View;
import java.net.URL;
import javax.sound.sampled.*;

/***
 * Classe per gestire l'audio all'interno del gioco
 */
public class AudioManager {

    Clip clip;
    URL[] soundURL = new URL[2];

    /***
     * Costruttore, carica le diverse OST per poterle utilizzare
     */
    public AudioManager(){
        try {
            soundURL[0] = getClass().getResource("/Music/Bomberman_ost1.wav");
            soundURL[1] = getClass().getResource("/Music/bomberman_battle.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Setta l'OST desiderata
     * @param i l'indice dell'OST che si vuole riprodurre
     */
    public void setFile(int i){
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Fa iniziare l'OST
     */
    public void play(){
        clip.start();
    }

    /***
     * Fa fermare l'OST
     */
    public void stop(){
        clip.stop();
    }
}
