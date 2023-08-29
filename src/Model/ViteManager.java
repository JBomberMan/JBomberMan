package Model;

import View.Vite;

import java.awt.*;

/**
 * Classe per gestire le vite del bomberman
 */
public class ViteManager {

    private int x, y;

    /**
     * costruttore
     */
    public ViteManager(){
        this.x = 245;
        this.y = 876;
    }

    /**
     * metodo per disegnare le vite
     * @param g il contesto grafico
     */
    public void disegna(Graphics2D g){
        Vite.disegna(g, Bomberman.getVite(), x, y);
    }
}
