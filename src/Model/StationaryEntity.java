package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class StationaryEntity extends GameEntity implements Collidable, Disegnabile {
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


}
