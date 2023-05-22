package Gobjects;
import Porfiri.BomberMan;
import Taruffi.Disegnabile;
import Tomassetti.Collidable;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

//classe che rappresenta powerup del gioco bomberman
//implementa l'interfaccia Disegnabile, dispone di due campi X e Y che rappresentano le coordinate del powerup che si può trovare anche dentro ad un blocco
//un metodo che permette di eseguire l'effetto del powerup
public class PowerUp extends StationaryEntity{
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

    private BufferedImage sprite;
    public void eseguiEffetto(BomberMan b) {}


    private static PowerUp.Tipo getRandomTipo()  {
        Random random = new Random();
        return PowerUp.Tipo.values()[random.nextInt(PowerUp.Tipo.values().length)];
    }
    public PowerUp(int x, int y) {
        super(x, y, null);
        this.tipo = getRandomTipo();
    }
    public PowerUp(int x, int y, BufferedImage image) {
        super(x, y, image);
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
}
