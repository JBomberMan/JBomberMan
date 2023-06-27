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
            //verticale
            this.hitbox = new Rectangle(x+8, y+8, 1, play.tileSize * raggio); //il +8 dovrebbe centrare l'hitbox con la bomba
            Graphics2D g2 = (Graphics2D) play.getGraphics();
            g2.draw3DRect(x-(play.tileSize*raggio/2), y, 1, (play.tileSize-10) * raggio, true);
            System.out.println("hitbox: " + this.hitbox);
        } else {
            //orizzontale
            this.hitbox = new Rectangle(x+8, y+8, play.tileSize * raggio, 1);
            Graphics2D g2 = (Graphics2D) play.getGraphics();
            //g2.draw3DRect(x, y-(play.tileSize*raggio/2), (play.tileSize-10) * raggio, 1 * raggio, true);
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

