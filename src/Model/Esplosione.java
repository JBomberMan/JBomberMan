package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Classe per modellare l'esplosione
 */
public class Esplosione extends StationaryEntity implements Disegnabile {

    int raggio;

    BufferedImage sprite;
    private int counter = 0;
    private String direzione;
    private boolean espaso = false;


    /**
     * Costruttore dell'esplosione
     * @param raggio raggio dell'esplosione
     * @param Sprite sprite dell'esplosione
     * @param x posizione x dell'esplosione
     * @param y posizione y dell'esplosione
     * @param play partita in cui si trova l'esplosione
     * @param direzione direzione dell'esplosione
     */
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

    /**
     * Metodo per espandere l'esplosione
     */
    public void espandi() {
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

    /**
     * Metodo per aggiornare l'esplosione
     */
    @Override
    public void update() {

        counter++;
        if(raggio >= 1) espandi();
        if(counter == 70){
            BombManager.addEsplosioniR(this);
        }

    }

    /**
     * Metodo per disegnare l'esplosione
     * @param g2 il contesto grafico
     */
    @Override
    public void disegna(Graphics2D g2) {
        update();
        g2.drawImage(sprite, x, y, null);
    }


}

