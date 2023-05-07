package Tomassetti;
import Porfiri.BomberMan;
import Taruffi.Disegnabile;
import Tomassetti.PowerUp;
import Tomassetti.AudioManager;

import static java.lang.Thread.sleep;

public class Prova {
    public static void main(String[] args) {

        try {
            sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // crea un array di 10 powerup
        PowerUp[] powerups = new PowerUp[10];
        // riempi l'array con powerup casuali
        for (int i = 0; i < powerups.length; i++) {
            powerups[i] = new PowerUp(0, 0);
        }

        for(PowerUp p : powerups){
            System.out.println(p);
        }
    }
}
