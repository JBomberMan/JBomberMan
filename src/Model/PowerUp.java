package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class PowerUp extends StationaryEntity {
    private boolean isRaccolto = false;



    public enum Tipo {

        EsplosioneRange(){

            public void eseguiEffetto(Bomberman b) {
                b.setRaggioEsplosione(1);

            }
        }
        ,ViteExtra(){
            public void eseguiEffetto(Bomberman b) {
                b.setVite(1);

            }
        }
        ,SuperaBlocchi(){
            public void eseguiEffetto(Bomberman b) {

                b.addToMap(this);
            }
        }
        ,SuperaBombe(){
            public void eseguiEffetto(Bomberman b) {
                b.addToMap(this);

            }
        }
        ,TempoExtra(){
            public void eseguiEffetto(Bomberman b) {
                b.setExtraTime(30);

            }
        }
        ,ControlloRemoto(){
            public void eseguiEffetto(Bomberman b) {
                b.addToMap(this);
            }
        }
        ,Crepe(){
            public void eseguiEffetto(Bomberman b) {
                b.setScore(50);
            }
        }
        ,IceCream(){
            public void eseguiEffetto(Bomberman b) {
                b.setScore(100);
            }
        };
        public abstract void eseguiEffetto(Bomberman b);

    }

    private Tipo tipo;

    public void loadSprite() {
        try
            {
                this.image = ImageIO.read(getClass().getResourceAsStream("/Images/"+tipo+".png"));
                this.hitbox = new Rectangle(this.x+16, this.y+16, image.getWidth()-16, image.getHeight()-16);
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
        this.tipo = Tipo.TempoExtra;
        this.isDistruttibile = false;
        this.loadSprite();
    }




    public void update() {
        //TODO implementare il metodo

    }
    public void raccogli(Bomberman b){
        TileManager.addEntityR(this);
        this.tipo.eseguiEffetto(b);
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
