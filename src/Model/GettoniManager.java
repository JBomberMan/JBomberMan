package Model;

import View.Gettoni;

import java.awt.*;

/**
 * Classe per gestire i gettoni
 */
public class GettoniManager {

    private int x, y;

    /**
     * Costruttore che inizializza la posizione in cui deve essere disegnato
     */
    public GettoniManager(){
        this.x = 400;
        this.y = 876;
    }

    /**
     * Metodo per disegnare i gettoni
     * @param g il contesto grafico
     */
    public void disegna(Graphics2D g){
        Gettoni.disegna(g, Bomberman.getGettoni(), x, y);
    }
}
