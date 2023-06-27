package Gobjects;

import Taruffi.Disegnabile;
import Tomassetti.Collidable;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class GameEntity implements Disegnabile, Collidable {
    int x,y;
    Rectangle hitbox;
    BufferedImage image;

    public GameEntity(int x, int y, BufferedImage image){
        this.x = x;
        this.y = y;
        this.image = image;
        if(image != null) this.hitbox = new Rectangle(x, y, this.image.getWidth()-15, this.image.getHeight()-15);
    }

    public static int getX(){
        return x;
    }
    public static int getY(){
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

    void solidCollision(GameEntity obj) {
        Rectangle2D intersection = this.hitbox.createIntersection(obj.hitbox);
        System.out.println(intersection.getWidth() + " " + intersection.getHeight());
        // Vertical collision
        if (intersection.getWidth() >= intersection.getHeight()) {
            // From the top
            if (intersection.getMaxY() >= this.hitbox.getMaxY()) {
                this.y -= 5;
            }
            // From the bottom
            if (intersection.getMaxY() >= obj.hitbox.getMaxY()) {

                this.y += 5;
            }

            // Smoothing around corners
            if (intersection.getWidth() < 16) {
                if (intersection.getMaxX() >= this.hitbox.getMaxX()) {

                    this.x -= 0.5;
                }
                if (intersection.getMaxX() >= obj.hitbox.getMaxX()) {

                    this.x += 0.5;
                }
            }
        }

        // Horizontal collision
        if (intersection.getHeight() >= intersection.getWidth()) {
            // From the left
            if (intersection.getMaxX() >= this.hitbox.getMaxX()) {

                this.x -= 5;
            }
            // From the right
            if (intersection.getMaxX() >= obj.hitbox.getMaxX()) {

                this.x += 5;
            }

            // Smoothing around corners
            if (intersection.getHeight() < 16) {
                if (intersection.getMaxY() >= this.hitbox.getMaxY()) {

                    this.y -= 0.5;
                }
                if (intersection.getMaxY() >= obj.hitbox.getMaxY()) {
                    this.y += 0.5;
                }
            }
        }
    }
}
