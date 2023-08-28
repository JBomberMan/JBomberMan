package Model;

import java.awt.image.BufferedImage;

/**
 * classe astratta per rappresentare un entità statica
 */
public abstract class StationaryEntity extends GameEntity implements Collidable, Disegnabile {
    protected boolean isDistrutto;
    public boolean isDistruttibile;
    protected Partita play;

    /**
     * costruttore
     * @param x coordinata x
     * @param y coordinata y
     * @param image immagine
     * @param play riferimento alla partita
     */
    public StationaryEntity(int x, int y, BufferedImage image, Partita play){
        super(x,y,image);
        this.isDistrutto = false;
        this.play = play;
    }


    /**
     * metodo per controllare se l'entità è distruttibile
     * @return true se è distruttibile, false altrimenti
     */
    public boolean isDistruttibile() {
        return isDistruttibile;
    }


}
