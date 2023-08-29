package Model;

import View.AudioManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

    /**
     * 
     * Classe che definisce il funzionamento dei powerUp (Superpoteri)
     */ 
public class PowerUp extends StationaryEntity {



    /**
     * 
     * Enumerazione che elenca i vari tipi di superpoteri che esistono 
     * 
     */

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

    /**
     * 
     * Metodo per caricare le img del superpotere che deve apparire a schermo
     * 
     */ 
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


    /**
     * 
     * Metodo per generare un tipo di superpotere casuale
     * @return il valore del powerUp che e' stato scelto randomicamente
     */ 
    private static PowerUp.Tipo getRandomTipo()  {
        Random random = new Random();
        return PowerUp.Tipo.values()[random.nextInt(PowerUp.Tipo.values().length)];
    }

    /**
     * 
     * Costruttore della classe powerUp
     * @param x coordinata x 
     * @param y coordinata y 
     * @param partita istanza della partita su cui si sta agendo
     */ 
    public PowerUp(int x, int y, Partita partita) {
        super(x, y, null, partita);
        this.tipo = getRandomTipo();
        this.isDistruttibile = false;
        this.loadSprite();
    }




    public void update() {

    }

    /**
     * 
     * Metodo per far raccogliere il superpoter dal bomberman
     * @param b istanza del bomberman
     * 
     */ 
    public void raccogli(Bomberman b){
        TileManager.addEntityR(this);
        this.tipo.eseguiEffetto(b);
    }


    /**
     * 
     * Metodo che stampa le informazioni sul powerUp rilasciato
     * @return una stringa con le informazioni del powerUp
     * 
     */ 
    public String toString() {
        return "PowerUp{" +
                "tipo=" + tipo +
                ", X=" + y +
                ", Y=" + x +
                '}';
    }

    /**
     * Metodo che gestisce il disegno del powerUp
     * @param g2d il contesto grafico
     */
    public void disegna(Graphics2D g2d) {
        g2d.drawImage(image, x, y, null);
    }
}
