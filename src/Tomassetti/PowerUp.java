package Tomassetti;
import Porfiri.BomberMan;
import Taruffi.Disegnabile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

//classe che rappresenta powerup del gioco bomberman
//implementa l'interfaccia Disegnabile, dispone di due campi X e Y che rappresentano le coordinate del powerup che si può trovare anche dentro ad un blocco
//un metodo che permette di eseguire l'effetto del powerup
public class PowerUp implements Disegnabile, Collidable{
    private enum Tipo {
        EsplosioneMassima(){
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
        ,ControlloRemoto(){
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
        }
    }
    private Tipo tipo;
    private int X;
    private int Y;

    private BufferedImage sprite;
    public void eseguiEffetto(BomberMan b) {}
    public PowerUp(int X, int Y) {
        this.X = X;
        this.Y = Y;
        this.tipo = getRandomTipo();
    }

    private final static PowerUp.Tipo[] tipi = PowerUp.Tipo.values();
    private final static Random random = new Random();
    private static PowerUp.Tipo getRandomTipo()  {
        return tipi[random.nextInt(tipi.length)];
    }


    public void disegna(Graphics2D g2) {
        //TODO  implementare il metodo
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
                ", X=" + X +
                ", Y=" + Y +
                '}';
    }
}
