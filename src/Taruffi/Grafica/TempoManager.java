package Taruffi.Grafica;

import java.awt.*;
import java.util.Timer;

public class TempoManager {

    private int minuti;
    private int secondi;
    private long countdown;

    private Tempo tempoDisegno = new Tempo();
    private int x;
    private int y;

    public TempoManager(){
        minuti = 10;
        secondi = 0;
        x = 600;
        y = 870;
        countdown = System.currentTimeMillis();

    }


    public int getMinuti(){
        return minuti;
    }

    public int getSecondi() {
        return secondi;
    }

    public void resetTempo(){
        minuti = 10;
        secondi = 0;
    }

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
        //addPunteggio(1);
        tempoDisegno.disegna(g, x, y, minuti, secondi); //fatto per rispettare MVC (credo)
    }
}
