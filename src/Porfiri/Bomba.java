package Porfiri;

import Gobjects.GameEntity;
import Taruffi.Disegnabile;
import Taruffi.Grafica.BombManager;
import Taruffi.Grafica.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
public class Bomba extends Tile implements Disegnabile{

    int x,y; //posizione della bomba
    int raggio; //raggio della bomba
    int timer; //timer della bomba
    public BufferedImage immagine;
    private int frameIndex = 0;


    public Bomba(int x, int y) {
        this.x = x;
        this.y = y;
        this.timer = 0;
        getBombImage();
    }

    public void getBombImage() {
        try {
            File imageFile = new File("src/Images/Bomb.png");
            immagine = ImageIO.read(imageFile);
    } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void Esplodi(){
        //TODO: implementare l'esplodere della bomba
    }


    @Override
    public void disegna(Graphics2D g2) {
        System.out.println("Disegno la bomba");
        g2.drawImage(immagine, BomberMan.getX(), BomberMan.getY(), null);
    }
}
 */

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Bomba extends Tile implements Disegnabile {
    private int x; // Posizione x della bomba
    private int y; // Posizione y della bomba
    private int raggio; // Raggio della bomba
    private int timer; // Timer della bomba
    private BufferedImage[] animationFrames; // Array delle immagini dell'animazione
    private int frameIndex; // Indice dell'immagine corrente dell'animazione
    private int timing = 0;

    public Bomba(int x, int y) {
        this.x = x;
        this.y = y;
        this.timer = 0;
        this.frameIndex = 0;
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
}

