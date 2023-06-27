package Gobjects;

import Taruffi.Grafica.Partita;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Muro extends StationaryEntity{
    public Muro(int x, int y, BufferedImage image, boolean isDistruttibile, Partita play){
        super(x,y,image, play);
        this.isDistruttibile = isDistruttibile;
    }

    public void update(){
        if(this.checkEsplosione()){
            this.setDistrutto();
            this.afterDistruzione();
        }
    }

    public void afterDistruzione(){
        float random = (float) Math.random();
        if(random < 0.5){
            PowerUp powerUp = new PowerUp(this.x,this.y,play);
        }
    }
    public void disegna(Graphics2D g2d){
        g2d.drawImage(image, x, y,play.tileSize, play.tileSize, null);
    }
}
