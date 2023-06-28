package Porfiri;

import Gobjects.MovingEntity;
import Gobjects.StationaryEntity;
import Taruffi.Disegnabile;
import Taruffi.Grafica.BombManager;
import Taruffi.Grafica.Partita;
import Tomassetti.Collidable;

import javax.imageio.ImageIO;
import java.awt.*;
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
        this.hitbox = new Rectangle(x,y,sprite.getWidth(),sprite.getHeight());

    }


    @Override
    public void update() {
        counter++;
        if(counter == 70){
            BombManager.removeEsplosione(this);
        }

    }

    @Override
    public void disegna(Graphics2D g2) {
        update();
        g2.drawImage(sprite, x, y, null);
    }


}

