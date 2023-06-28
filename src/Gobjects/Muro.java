package Gobjects;

import Porfiri.Esplosione;
import Taruffi.Grafica.Partita;
import Taruffi.Grafica.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Muro extends StationaryEntity{
    public Muro(int x, int y, BufferedImage image, boolean isDistruttibile, Partita play){
        super(x,y,image, play);
        this.isDistruttibile = isDistruttibile;
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
    public boolean checkEsplosione(){
        return this.isDistrutto; //rimane così per adesso, una volta che ci sarà la classe esplosione si modificherà
    }
    public void setDistrutto(){
        this.isDistrutto = true;
    }
    @Override
    public void handleCollision(Esplosione e) {
        if(this.isDistruttibile){
            this.setDistrutto();
        }
    }

    @Override
    public void update() {
        if(this.isDistrutto){
            PowerUp prova = new PowerUp(this.x,this.y,play);
            System.out.println(prova.toString());
            TileManager.addEntity(prova);
            TileManager.removeEntity(this);
        }
}}
