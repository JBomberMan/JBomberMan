package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Boss extends MovingEntity implements Collidable {


    public static String direction = "left";
    




    public Boss(int x, int y, BufferedImage image, int velocita, int vite, Partita play) {
        super(x, y, image, velocita, vite, play);}



    public abstract boolean isDead();
    public abstract Polygon getHitboxPorcata();

}
