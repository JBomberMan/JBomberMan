package Gobjects;
import Porfiri.BomberMan;
import Taruffi.Disegnabile;
import Taruffi.Grafica.Partita;
import Tomassetti.Collidable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

//classe che rappresenta powerup del gioco bomberman
//implementa l'interfaccia Disegnabile, dispone di due campi X e Y che rappresentano le coordinate del powerup che si può trovare anche dentro ad un blocco
//un metodo che permette di eseguire l'effetto del powerup
public class PowerUp extends StationaryEntity{
    private enum Tipo {

        EsplosioneRange(){

            public void eseguiEffetto(BomberMan b) {
                //TODO implementare il metodo
            }
        }
        ,ViteExtra(){
            public void eseguiEffetto(BomberMan b) {
                //TODO implementare il metodo
            }
        }
        ,SuperaBlocchi(){
            public void eseguiEffetto(BomberMan b) {
                //TODO implementare il metodo
            }
        }
        ,SuperaBombe(){
            public void eseguiEffetto(BomberMan b) {
                //TODO implementare il metodo
            }
        }
        ,TempoExtra(){
            public void eseguiEffetto(BomberMan b) {
                //TODO implementare il metodo
            }
        }
        /***,ControlloRemoto(){
            public void eseguiEffetto(BomberMan b) {
                //TODO implementare il metodo
            }
        }
        ,CupAndBall(){
            public void eseguiEffetto(BomberMan b) {
                //TODO implementare il metodo
            }
        }
        ,IceCream(){
            public void eseguiEffetto(BomberMan b) {

                //TODO implementare il metodo
            }
        }**/
    }

    private Tipo tipo;


    public void eseguiEffetto(BomberMan b) {}
    public void loadSprite() {
        try
            {
                this.image = ImageIO.read(getClass().getResourceAsStream("/Images/"+tipo+".png"));
                this.hitbox = new Rectangle(this.x, this.y, image.getWidth(), image.getHeight());
            }
        catch(Exception e)
            {
                e.printStackTrace();
            }
    }

    private static PowerUp.Tipo getRandomTipo()  {
        Random random = new Random();
        return PowerUp.Tipo.values()[random.nextInt(PowerUp.Tipo.values().length)];
    }
    public PowerUp(int x, int y, Partita partita) {
        super(x, y, null, partita);
        this.tipo = getRandomTipo();
        this.isDistruttibile = false;
        this.loadSprite();
    }
    public PowerUp(int x, int y, BufferedImage image, Partita partita) {
        super(x, y, image, partita);
        this.tipo = getRandomTipo();
    }



    public void update() {
        //TODO implementare il metodo

    }

    public void handleCollision(BomberMan b) {
        this.eseguiEffetto(b);
    }

    public String toString() {
        return "PowerUp{" +
                "tipo=" + tipo +
                ", X=" + y +
                ", Y=" + x +
                '}';
    }

    public void disegna(Graphics2D g2d) {
        g2d.drawImage(image, x, y, null);
    }
}
