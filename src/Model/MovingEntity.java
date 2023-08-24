package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class  MovingEntity extends GameEntity {
    protected int velocita;
    public int vite;
    protected Partita play;

    public MovingEntity(int x, int y, BufferedImage image, int velocita, int vite, Partita play){
        super(x,y,image);
        this.velocita = velocita;
        this.vite = vite;
        this.play = play;
    }
    public abstract void update();
    public abstract void muovi();
    public abstract void disegna(Graphics2D g2d);
}
