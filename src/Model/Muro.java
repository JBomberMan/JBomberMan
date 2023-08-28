package Model;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Muro extends StationaryEntity {

    /**
     * Costruttore della tile Muro
     * @param x coordinata x iniziale
     * @param y coordinata y iniziale
     * @param image immagine della tile
     * @param isDistruttibile valore booleano per indicare se il blocco può essere distrutto o no
     * @param play riferimento alla partita
     */
    public Muro(int x, int y, BufferedImage image, boolean isDistruttibile, Partita play){
        super(x,y,image, play);
        this.isDistruttibile = isDistruttibile;
    }

    /**
     * Metodo che gestisce il disegno della tile Muro
     * @param g2d il contesto grafico
     */
    public void disegna(Graphics2D g2d){
        g2d.drawImage(image, x, y,play.tileSize, play.tileSize, null);
    }

    /**
     * Metodo che setta quando un blocco viene distrutto
     * 
     */
    public void setDistrutto(){
        this.isDistrutto = true;
    }

    /**
     * Metodo che gestisce le collisioni del Muro con le esplosioni
     * @param e Esplosione con cui si è verificata la collisione
     */
    @Override
    public void handleCollision(Esplosione e) {
        if(this.isDistruttibile){
            this.setDistrutto();
        }
    }

    /**
     *Metodo che gestisce il rilascio di power up quando viene distrutto il muro
     */
    @Override
    public void update() {
        if(this.isDistrutto){
            double random = Math.random();
            if(random < 0.3){
                TileManager.addEntity(new PowerUp(this.x,this.y,play));
            }
            TileManager.addEntityR(this);
        }
    }
}
