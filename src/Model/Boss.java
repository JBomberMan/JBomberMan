package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * classe astratta che rappresenta un boss
 */
public abstract class Boss extends MovingEntity implements Collidable {


    public static String direction = "left";


    /**
     * costruttore
     * @param x posizione x
     * @param y posizione y
     * @param image immagine
     * @param velocita velocità
     * @param vite vite
     * @param play riferimento alla partita
     */
    public Boss(int x, int y, BufferedImage image, int velocita, int vite, Partita play) {
        super(x, y, image, velocita, vite, play);}


    /**
     * metodo per contriollare se il boss é morto
     * @return booleano che indica se il boss é morto o meno
     */
    public abstract boolean isDead();

    /**
     * metodo per ritornare la hitbox del boss
     * @return la hitbox del boss
     */
    public abstract Polygon getHitboxSpecial();

}
