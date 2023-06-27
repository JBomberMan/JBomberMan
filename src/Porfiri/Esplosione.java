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
    int danno;// danno dell'esplosione

    //int indiceAnimazione; //indice dell'immagine corrente


    
    public Esplosione(int raggio, int danno, BufferedImage Sprite, int x, int y, Partita play) {
        super(x, y, Sprite, play );
        this.raggio = raggio;
        this.danno = danno;
        //this.indiceAnimazione = 0;
    }

   

    /*public Image nextSprite(){
        return Sprite[indiceAnimazione++ % Sprite.length];
    }*/


    @Override
    public void update() {

    }

    @Override
    public void disegna(Graphics2D g2) {
        // TODO Auto-generated method stub
    }


}

