package Model;


import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Classe per rappresentare un'entità di gioco
 */
public abstract class GameEntity implements Disegnabile, Collidable {
    protected int x,y;
    public Rectangle hitbox;
    public BufferedImage image;

    /**
     * costruttore
     * @param x posizione x
     * @param y posizione y
     * @param image immagine dell'entitá
     */
    public GameEntity(int x, int y, BufferedImage image){
        this.x = x;
        this.y = y;
        this.image = image;
        if(image != null) this.hitbox = new Rectangle(x+5, y+5, this.image.getWidth()-5, this.image.getHeight()-5);
    }

    /**
     * metodo per avere le x dell'entitá
     * @return coordinata x
     */
    public int getX(){
        return x;
    }

    /**
     * metodo per avere le y dell'entitá
     * @return coordinata y
     */
    public int getY(){
        return y;
    }

    /**
     * metodo per avere la hitbox dell'entitá
     * @return hitbox
     */
    public Rectangle getHitbox(){
        return hitbox;
    }

    /**
     * metodo per aggiornare l'entitá
     */
    public abstract void update();

    /**
     * metodo per settare l'immagine dell'entitá
     * @param image immagine dell'entitá
     */
    public void setImage(BufferedImage image){
        this.image = image;
    }

    /**
     * metodo per disegnare l'entitá
     * @param g2d il contesto grafico
     */
    public abstract void disegna(Graphics2D g2d);

    /**
     * metodo per avere l'immagine dell'entitá
     * @return immagine dell'entitá
     */
    public BufferedImage getImage(){
        return image;
    }


}
