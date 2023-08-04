package Taruffi.Nemici;

import Gobjects.*;
import Porfiri.Esplosione;
import Taruffi.Grafica.Partita;
import Taruffi.Grafica.TileManager;
import Tomassetti.Collidable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Proiettile extends MovingEntity implements Collidable {

    private int velocita;
    private BufferedImage up, down, left, right;
    private int spriteCounter = 0;
    private int spriteNum = 0;
    private Boolean dead = false;
    private int direzione;



    public Proiettile(int x, int y, BufferedImage image, int velocita, int vite, Partita play, int direzione) {
        super(x, y, image, velocita, vite, play);
        this.velocita = velocita;
        this.spriteCounter = 0;
        this.direzione = direzione;

        getEnemiesImage();
    }

    public void getEnemiesImage(){
        try{
            up = ImageIO.read(getClass().getResourceAsStream("/Images/proiettile/proiettileUp.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/Images/proiettile/proiettileDown.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/Images/proiettile/proiettileLeft.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/Images/proiettile/proiettileRight.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        this.hitbox = new Rectangle(x, y, up.getWidth(), up.getHeight());
    }

    @Override
    public void update() {
        this.hitbox.setBounds(x, y, up.getWidth(), up.getHeight());
        muovi();


    }

    @Override
    public void muovi() {
        switch(direzione){
            case 0: //orizzontale dx
                x += velocita;
                break;
            case 1: //orizzontale sx
                x -= velocita;
                break;
            case 2: //verticale alto
                y -= velocita;
                break;
            case 3: //verticale basso
                y += velocita;
                break;
            case 4: //diagonale alto sx
                y -= velocita;
                x -= velocita;
                break;
            case 5: //diagonale alto dx
                y -= velocita;
                x += velocita;
                break;
            case 6: //diagonale basso sx
                y += velocita;
                x -= velocita;
                break;
            case 7: //diagonale basso dx
                y += velocita;
                x += velocita;
                break;
        }
        spriteCounter++;
        if(spriteCounter == 10){
            spriteNum = (spriteNum + 1) % 4; //ogni 10 frame cambia sprite
            spriteCounter = 0;
        }



    }

    @Override
    public void disegna(Graphics2D g2d) {
        if (!dead) {
            BufferedImage image = null;
            switch(spriteNum){
                case 0:
                    image = up;
                    break;
                case 1:
                    image = right;
                    break;
                case 2:
                    image = down;
                    break;
                case 3:
                    image = left;
                    break;
            }
            g2d.drawImage(image, x, y, null);
        }


    }

}
