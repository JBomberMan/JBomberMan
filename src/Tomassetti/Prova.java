package Tomassetti;
import Porfiri.BomberMan;
import Taruffi.Disegnabile;
import Tomassetti.PowerUp;
public class Prova {
    public static void main(String[] args) {
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
