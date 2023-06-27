package Gobjects;

import Taruffi.Grafica.Partita;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bomba extends GameEntity{
    private int raggio; // Raggio della bomba
    private int timer; // Timer della bomba
    private BufferedImage[] animationFrames; // Array delle immagini dell'animazione
    private int frameIndex; // Indice dell'immagine corrente dell'animazione
    private int timing = 0;
    Partita play;

    public Bomba(int x, int y,BufferedImage image, Partita play) {
        super(x, y, image);
        this.play = play;
        this.timer = 0;
        this.frameIndex = 0;
        this.hitbox = new Rectangle(x,y,play.tileSize,play.tileSize);
        loadAnimationFrames();
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

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

    @Override
    public void disegna(Graphics2D g2) {
        if(timing == 20){
            // Increment the frame index and wrap around if necessary
            frameIndex = (frameIndex + 1) % 2;
            timing = 0;
        }
        else {
            timing++;
        }
        // Draw the current frame of the animation
        BufferedImage currentFrame = animationFrames[frameIndex];
        g2.drawImage(currentFrame, x, y, null);
    }

    @Override
    public void update() {

    }
}
