package Gobjects;

import Taruffi.Grafica.Partita;
import Tomassetti.Collidable;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class StationaryEntity extends GameEntity implements Collidable {
    protected boolean isDistrutto;
    public boolean isDistruttibile;
    protected Partita play;


    public StationaryEntity(int x, int y, BufferedImage image, Partita play){
        super(x,y,image);
        this.isDistrutto = false;
        this.play = play;
    }


    public boolean isDistruttibile() {
        return isDistruttibile;
    }

    public abstract void disegna(Graphics2D g2d);



}
