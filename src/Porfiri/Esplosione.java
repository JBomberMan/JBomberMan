package Porfiri;

import Gobjects.MovingEntity;
import Gobjects.StationaryEntity;
import Taruffi.Disegnabile;
import Taruffi.Grafica.Partita;
import Tomassetti.Collidable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Esplosione extends StationaryEntity implements Disegnabile {

    int x,y;
    int raggio; //raggio dell'esplosione



    //int indiceAnimazione; //indice dell'immagine corrente


    
    public Esplosione(int raggio, BufferedImage Sprite, int x, int y, Partita play, Boolean direzione) {
        super(x, y, Sprite, play );
        this.raggio = raggio;
        if (direzione) {
            this.hitbox = new Rectangle(x, y, 1, play.tileSize * raggio);
            System.out.println("hitbox: " + this.hitbox);
        } else {
            this.hitbox = new Rectangle(x, y, play.tileSize * raggio, 1);
            System.out.println("hitbox: " + this.hitbox);
        }

    }


    @Override
    public void update() {

    }

    @Override
    public void disegna(Graphics2D g2) {
        // TODO Auto-generated method stub
    }


}

