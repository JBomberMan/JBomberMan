package Tomassetti;

import Taruffi.Disegnabile;


import java.awt.*;
import java.awt.image.BufferedImage;
public abstract class GameEntity implements Disegnabile, Collidable {
    int x;
    int y;
    BufferedImage image;
    Rectangle hitbox;
    int speed = 1;

    public GameEntity(int x, int y, BufferedImage image){
        this.x = x;
        this.y = y;
        this.image = image;
        this.hitbox = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public abstract void update();
}
