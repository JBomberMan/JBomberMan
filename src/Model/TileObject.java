package Model;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * Classe per rappresentare un oggetto che può essere posizionato sulla mappa
 */
public class TileObject extends GameEntity {
    Partita play;

    /**
     * Costruttore
     * @param x coordinata x
     * @param y coordinata y
     * @param image immagine iniziale
     * @param play partita in cui è contenuto
     */
    public TileObject(int x, int y, BufferedImage image, Partita play){
        super(x,y,image);
        this.play = play;
    }

    /**
     * Costruttore per editor dei livelli
     * @param x coordinata x
     * @param y coordinata y
     * @param image immagine iniziale
     */

    public TileObject(int x, int y, BufferedImage image){
        super(x,y,image);
        play = null;
    }

    /**
     * Metodo per aggiornare lo stato dell'oggetto
     */
    public void update(){}


    /**
     * Metodo per disegnare l'oggetto
     * @param g2d il contesto grafico
     */
    public void disegna(Graphics2D g2d){
        if(play == null){g2d.drawImage(image,x,y,32,32,null);}
        else{g2d.drawImage(image, x, y,play.tileSize, play.tileSize, null);}

    }
}
