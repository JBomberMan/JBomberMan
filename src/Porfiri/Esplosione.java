package Porfiri;

import Gobjects.MovingEntity;
import Gobjects.StationaryEntity;
import Taruffi.Disegnabile;
import Taruffi.Grafica.BombManager;
import Taruffi.Grafica.Partita;
import Tomassetti.Collidable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Esplosione extends StationaryEntity implements Disegnabile {

    int raggio; //raggio dell'esplosione

    BufferedImage sprite;
    private int counter = 0;


    
    public Esplosione(int raggio, BufferedImage Sprite, int x, int y, Partita play, Boolean direzione) {
        super(x, y, Sprite, play);
        this.raggio = raggio;
        try{
            if(direzione) {
                this.x = x - (raggio * play.tileSize);
                sprite = ImageIO.read(new File("src/Images/Orizzontale" + raggio + ".png")); //se direzione é true creiamo l'orizzontale
            }
            else{
                this.y = y - (raggio * play.tileSize);
                sprite = ImageIO.read(new File("src/Images/Verticale" + raggio + ".png"));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        this.hitbox = new Rectangle(this.x+10,this.y+10,sprite.getWidth()-10, sprite.getHeight()-10);

    }


    @Override
    public void update() {
        counter++;
        if(counter == 70){
            BombManager.addEsplosioniR(this);
        }

    }

    @Override
    public void disegna(Graphics2D g2) {
        update();
        g2.drawImage(sprite, x, y, null);
    }


}

