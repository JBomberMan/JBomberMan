package Taruffi.Nemici;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Gobjects.Bomba;
import Gobjects.GameEntity;
import Gobjects.MovingEntity;
import Gobjects.PowerUp;
import Gobjects.StationaryEntity;
import Porfiri.Esplosione;
import Taruffi.Grafica.Partita;
import Taruffi.Grafica.SchermataSconfitta;
import Taruffi.Grafica.TileManager;
import Tomassetti.Collidable;

public class Nemico1  extends MovingEntity implements Collidable{

        private static int velocita;

        public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
        public static String direction = "down";
        private int probabilitàDirezione ;
        private int attesaMovimento = 30;
        public static int spriteCounter = 0;
        public static int spriteNum = 1;
        private int invTimer;
        

    public Nemico1(int x, int y,BufferedImage image, int puntiVita, int velocita, Partita play) {
        super(x, y, image, velocita, puntiVita, play);
        this.velocita = velocita;
        getEnemiesImage();
    }


    public void getEnemiesImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Images/nmc1/BomberMan_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Images/nmc1/BomberMan_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Images/nmc1/BomberMan_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Images/nmc1/BomberMan_down2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Images/nmc1/BomberMan_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Images/nmc1/BomberMan_right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Images/nmc1/BomberMan_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Images/nmc1/BomberMan_left2.png"));
        
    } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        this.hitbox.setBounds(x+5, y+5, play.tileSize-10, play.tileSize-10);
        muovi();
        if(invTimer > 0){
            invTimer--;
        }
    }

    @Override
    public void muovi() {
        if(attesaMovimento > 20){
            probabilitàDirezione = (int) (Math.random() * 4);
            attesaMovimento = 0;
        }
        attesaMovimento++;
        if (probabilitàDirezione == 0) {
            direction = "up";
            y -= velocita;
        }
        else if (probabilitàDirezione == 1) {
            direction = "down";
            y += velocita;
        }
        else if (probabilitàDirezione == 2) {
            direction = "left";
            x -= velocita;
        }
        else if (probabilitàDirezione == 3) {
            direction = "right";
            x += velocita;
        }
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
        
    

    

    @Override
    public void disegna(Graphics2D g2d) {
        BufferedImage image = down1;

        switch(direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2d.drawImage(image, x, y, play.tileSize, play.tileSize, null);
    }


    public void handleCollision(Esplosione e){
        if(this.invTimer == 0){
            this.vite--;
            this.invTimer = 36;
            System.out.println("Vite rimaste Nemico: " + this.vite);

            if (this.vite <= 0){
                System.out.println("Nemico Sconfitto!");
            }
        }
    }

    public void handleCollision(StationaryEntity se){
        this.solidCollision(se);
    }

    public void handleCollision(Bomba b){
        this.solidCollision(b);
            
    }

    

    void solidCollision(GameEntity obj) {
        Rectangle2D intersection = this.hitbox.createIntersection(obj.hitbox);

        // Vertical collision
        if (intersection.getWidth() >= intersection.getHeight()) {
            // From the top
            if (intersection.getMaxY() >= this.hitbox.getMaxY()) {
                this.y -= 5;
            }
            // From the bottom
            if (intersection.getMaxY() >= obj.hitbox.getMaxY()) {

                this.y += 5;
            }

            // Smoothing around corners
            if (intersection.getWidth() < 16) {
                if (intersection.getMaxX() >= this.hitbox.getMaxX()) {

                    this.x -= 0.5;
                }
                if (intersection.getMaxX() >= obj.hitbox.getMaxX()) {

                    this.x += 0.5;
                }
            }
        }

        // Horizontal collision
        if (intersection.getHeight() >= intersection.getWidth()) {
            // From the left
            if (intersection.getMaxX() >= this.hitbox.getMaxX()) {

                this.x -= 5;
            }
            // From the right
            if (intersection.getMaxX() >= obj.hitbox.getMaxX()) {

                this.x += 5;
            }

            // Smoothing around corners
            if (intersection.getHeight() < 16) {
                if (intersection.getMaxY() >= this.hitbox.getMaxY()) {

                    this.y -= 0.5;
                }
                if (intersection.getMaxY() >= obj.hitbox.getMaxY()) {
                    this.y += 0.5;
                }
            }
        }
    }
    
}
