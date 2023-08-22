package Taruffi.Grafica;

import java.awt.*;

public class PunteggioManager {

    private int punteggio;
    private Punteggio punteggioDisegno = new Punteggio();
    private int x;
    private int y;

    public PunteggioManager(){
        punteggio = 0;
        x = 10;
        y = 870;
    }

    public void addPunteggio(int p){
        punteggio += p;
    }

    public void disegna(Graphics2D g){
        //addPunteggio(1);
        punteggioDisegno.disegna(g, x, y, punteggio); //fatto per rispettare MVC (credo)
    }


}
