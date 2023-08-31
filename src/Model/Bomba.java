package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Classe per rappresentare la bomba
 */
public class Bomba extends GameEntity {
    private int raggio; // Raggio della bomba
    private int timer; // Timer della bomba
    private BufferedImage[] animationFrames; // Array delle immagini dell'animazione
    private int frameIndex; // Indice dell'immagine corrente dell'animazione
    private int timing = 0;
    protected Partita play;
    protected Boolean esplosa = false;

    /**
     * Costruttore della bomba
     * @param x coordinate x della bomba
     * @param y coordinate y della bomba
     * @param image immagine della bomba
     * @param play partita in cui si trova la bomba
     * @param raggio raggio della bomba
     */
    public Bomba(int x, int y,BufferedImage image, Partita play, int raggio) {
        super(x, y, image);
        this.play = play;
        this.timer = 0;
        this.frameIndex = 0;
        this.hitbox = new Rectangle(x,y,play.tileSize,play.tileSize);
        loadAnimationFrames();
        this.raggio = raggio; //per debug, da modificare andando a vedere quanto é il raggio effettivo della bomba
    }

    /**
     * Metodo per caricare le immagini dell'animazione
     */
    private void loadAnimationFrames() {
        try {
            // Load the animation frames
            animationFrames = new BufferedImage[2];

            for (int i = 0; i < 2; i++) {
                String filePath = "src/Images/Bomb" + i + ".png";
                File imageFile = new File(filePath);
                animationFrames[i] = ImageIO.read(imageFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo per disegnare la bomba
     * @param g2 il contesto grafico
     */
    @Override
    public void disegna(Graphics2D g2) {
        update();
        // Draw the current frame of the animation
        BufferedImage currentFrame = animationFrames[frameIndex];
        if(!esplosa){
            g2.drawImage(currentFrame, x, y, null);
        }

    }

    /**
     * Metodo che aggiorna l'animazione della bomba
     */
    @Override
    public void update() {
        if(timing%20 == 0){
            // Increment the frame index and wrap around if necessary
            frameIndex = (frameIndex + 1) % 2;
        }
        timing++;
        if(timing == 201){
            esplosa = true;
            BombManager.togliBomba(this);
            esplodi();
        }
    }

    /**
     * Metodo che fa esplodere la bomba
     */
    public void esplodi(){
        BombManager.addEsplosione(new Esplosione(raggio,null,x,y,play,"centrale"));
    }
}
