package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Classe per rappresentare un'entità che si muove
 */
public abstract class  MovingEntity extends GameEntity {
    protected int velocita;
    public int vite;
    protected Partita play;

    /**
     * Costruttore
     * @param x coordinata x
     * @param y coordinata y
     * @param image immagine iniziale
     * @param velocita velocitá
     * @param vite vite
     * @param play riferimento a partita
     */
    public MovingEntity(int x, int y, BufferedImage image, int velocita, int vite, Partita play){
        super(x,y,image);
        this.velocita = velocita;
        this.vite = vite;
        this.play = play;
    }

    /**
     * metodo per aggiornare l'entitá
     */
    public abstract void update();

    /**
     * metodo per muovere l'entitá
     */
    public abstract void muovi();

    /**
     * metodo per disegnare l'entitá
     * @param g2d il contesto grafico
     */
    public abstract void disegna(Graphics2D g2d);
}
