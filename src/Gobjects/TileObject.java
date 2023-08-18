package Gobjects;

import Taruffi.Grafica.Partita;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileObject extends GameEntity{
    Partita play;
    public TileObject(int x, int y, BufferedImage image, Partita play){
        super(x,y,image);
        this.play = play;
    }

    public TileObject(int x, int y, BufferedImage image){
        super(x,y,image);
        play = null;
    }

    public void update(){}


    public void disegna(Graphics2D g2d){
        if(play == null){g2d.drawImage(image,x,y,32,32,null);}
        else{g2d.drawImage(image, x, y,play.tileSize, play.tileSize, null);}

    }
}
