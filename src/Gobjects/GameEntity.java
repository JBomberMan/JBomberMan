package Gobjects;

import Taruffi.Disegnabile;
import Tomassetti.Collidable;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class GameEntity implements Disegnabile, Collidable {
    protected int x,y;
    protected Rectangle hitbox;
    BufferedImage image;

    public GameEntity(int x, int y, BufferedImage image){
        this.x = x;
        this.y = y;
        this.image = image;
        if(image != null) this.hitbox = new Rectangle(x+5, y+5, this.image.getWidth()-5, this.image.getHeight()-5);
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Rectangle getHitbox(){
        return hitbox;
    }
    public abstract void update();
    public void setImage(BufferedImage image){
        this.image = image;
    }
    public abstract void disegna(Graphics2D g2d);
    public BufferedImage getImage(){
        return image;
    }


}
