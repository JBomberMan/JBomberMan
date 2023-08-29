package Model;
import View.Tempo;

import java.awt.*;

/**
 * Classe per gestire il tempo di gioco
 */
public class TempoManager {

    private int minuti;
    private int secondi;
    private long countdown;

    private Tempo tempoDisegno = new Tempo();
    private int x;
    private int y;

    /**
     * Costruttore della classe TempoManager
     */
    public TempoManager(){
        minuti = 5;
        secondi = 0;
        x = 606;
        y = 873;
        countdown = System.currentTimeMillis();

    }


    /**
     * metodo per resettare il tempo
     */
    public void resetTempo(){
        minuti = 5;
        secondi = 0;
    }

    /**
     * metodo per disegnare il tempo
     * @param g il contesto grafico
     */
    public void disegna(Graphics2D g){
        if(System.currentTimeMillis() - countdown >= 1000){
            countdown = System.currentTimeMillis();
            if(secondi == 0){
                minuti--;
                secondi = 59;
            }else{
                secondi--;
            }

        }
        tempoDisegno.disegna(g, x, y, minuti, secondi);
        if (minuti == 0 && secondi == 0){
            Bomberman.setDead(true);

        }
    }

    /**
     * metodo per aggiungere secondi al tempo
     * @param s i secondi da aggiungere
     */
    public void addSec(int s){
        if(secondi+s > 59){
            minuti+=1;
            secondi+= s - 59;
        }
        else secondi += s;
    }
}
