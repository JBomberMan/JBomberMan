package Model;

import View.Punteggio;

import java.awt.*;

/**
 * Classe per gestire il punteggio
 */
public class PunteggioManager {

    private int punteggio;
    private Punteggio punteggioDisegno = new Punteggio();
    private int x;
    private int y;

    /**
     * Costruttore della classe
     */
    public PunteggioManager(){
        punteggio = 0;
        x = 10;
        y = 870;
    }

    /**
     * metodo per aggiungere punti
     * @param p quantitá di punti da aggiungere
     */
    public void addPunteggio(int p){
        punteggio += p;
    }

    /**
     * metodo per disegnare il punteggio
     * @param g il contesto grafico
     */
    public void disegna(Graphics2D g){
        punteggioDisegno.disegna(g, x, y, punteggio); //fatto per rispettare MVC (credo)
    }


}
