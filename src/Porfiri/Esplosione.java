package Porfiri;

import Gobjects.MovingEntity;
import Gobjects.Muro;
import Gobjects.StationaryEntity;
import Taruffi.Disegnabile;
import Taruffi.Grafica.BombManager;
import Taruffi.Grafica.Partita;
import Taruffi.Grafica.TileManager;
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
    private String direzione;
    private boolean espaso = false;


    
    public Esplosione(int raggio, BufferedImage Sprite, int x, int y, Partita play, String direzione){
        super(x, y, Sprite, play);
        this.raggio = raggio;
        this.direzione = direzione;

        try{
            if(direzione.equals("centrale")) {
                sprite = ImageIO.read(new File("src/Images/centraleProva.png")); //si crea la centrale
            }
            else if(direzione.equals("destra")){
                this.x = x + 64;
                if(raggio == 0) sprite = ImageIO.read(new File("src/Images/orizzontalFineDx.png"));
                else sprite = ImageIO.read(new File("src/Images/lateraleProva.png"));

            }
            else if(direzione.equals("sinistra")){
                this.x = x - 64;
                if(raggio == 0) sprite = ImageIO.read(new File("src/Images/orizzontaleFineSx.png"));
                else sprite = ImageIO.read(new File("src/Images/lateraleProva.png"));
            }
            else if(direzione.equals("sopra")){
                this.y = y - 64;
                if (raggio == 0) sprite = ImageIO.read(new File("src/Images/verticaleFineUp.png"));
                else sprite = ImageIO.read(new File("src/Images/verticaleProva.png"));
            }
            else if(direzione.equals("sotto")){
                this.y = y + 64;
                if (raggio == 0) sprite = ImageIO.read(new File("src/Images/verticaleFineDown.png"));
                else sprite = ImageIO.read(new File("src/Images/verticaleProva.png"));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        this.hitbox = new Rectangle(this.x,this.y,sprite.getWidth(), sprite.getHeight());

    }

    public void espandi() {
        //espande l'esplosione
        if (!espaso && this.direzione.equals("centrale")) {
            TileManager.AggiungiACoda(new Esplosione(raggio - 1, sprite, x, y, play, "destra"));
            TileManager.AggiungiACoda(new Esplosione(raggio - 1, sprite, x, y, play, "sinistra"));
            TileManager.AggiungiACoda(new Esplosione(raggio - 1, sprite, x, y, play, "sopra"));
            TileManager.AggiungiACoda(new Esplosione(raggio - 1, sprite, x, y, play, "sotto"));
            espaso = true;
        }
        if(!espaso) {
            TileManager.AggiungiACoda(new Esplosione(raggio-1, sprite, x, y, play, this.direzione));
            espaso = true;}

    }
    @Override
    public void update() {

        counter++;
        if(raggio >= 1) espandi();
        if(counter == 70){
            BombManager.addEsplosioniR(this);
        }

    }

    @Override
    public void disegna(Graphics2D g2) {
        update();
        g2.drawImage(sprite, x, y, null);
        //g2.draw(this.hitbox);
    }


}

