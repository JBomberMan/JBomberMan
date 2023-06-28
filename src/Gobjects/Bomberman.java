package Gobjects;

import Taruffi.Disegnabile;
import Taruffi.Grafica.KeyHandler;
import Taruffi.Grafica.Partita;
import Taruffi.Grafica.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bomberman{
        private static int x = 0;
        private static int y= 0; //posizione del bomberman
        private int vite; //punti vita del bomberman
        private int velocita; //velocità del bomberman

        public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
        public String direction;
        KeyHandler keyH;
        Partita play;
        public int spriteCounter = 0;
        public int spriteNum = 1;
        Rectangle hitbox;


        public Bomberman(int x, int y,BufferedImage image, int puntiVita, int velocita, KeyHandler keyH, Partita play) {
            Bomberman.x = x;
            Bomberman.y = y; //posizioni di base
            this.direction = "down";
            this.vite = puntiVita;
            this.velocita = velocita;
            //this.Sprite = Sprite;
            //this.indiceAnimazione = 0;
            this.keyH = keyH;
            this.play = play;
            this.hitbox = new Rectangle(x,y,play.tileSize -10,play.tileSize -10);
            getPlayerImage();
        }

    public static int getX() {
            return x;
    }
    public static int getY() {
            return y;
    }

    public Rectangle getHitbox() {
            return hitbox;
    }

    public void getPlayerImage() {
            try {
                up1 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_up1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_up2.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_down1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_down2.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_right1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_right2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_left1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_left2.png"));

            } catch(IOException e){
                e.printStackTrace();
            }
        }


        public void muovi(){
            if(keyH.up ==  true){
                direction = "up";
                y -= velocita;
            }
            if(keyH.down ==  true){
                direction = "down";
                y += velocita;
            }
            if(keyH.left ==  true){
                direction = "left";
                x -= velocita;
            }
            if(keyH.right ==  true){
                direction = "right";
                x += velocita;
            }
            spriteCounter++;
            if(spriteCounter > 10) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        public void piazzaBomba(){
            //TODO  implementare il piazzamento della bomba
        }

        public void DetonaADistanza(){
            //TODO: implementare detonazione a distanza
            //Idea: metodo che restituisce ogni bomba presente sul campo da gioco
            //e per ognuna setta il suo timer a 0 in modo che al prossimo update esplodano tutte
        }
    /*
    public Image nextSprite(){
        return Sprite[indiceAnimazione++ % Sprite.length];
    }*/

        public void setScore(int score){
            //perché avevamo detto di metterlo qua?
            // non ricordo ma effettivamente non potremmo soltanto farlo aggiornare dal vivew?
        }




        public void disegna(Graphics2D g2) {
            //g2.setColor(Color.WHITE); //setta il colore di sfondo

            //g2.fillRect(x, y, play.tileSize, play.tileSize); //disegna qualcosa
            //sará sostituito con il bomebrman
            //con relative posizioni eccetera
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
            g2.drawImage(image, x, y, play.tileSize, play.tileSize, null);

        }

        public void update(){
            this.hitbox.setBounds(x, y, play.tileSize-15, play.tileSize-15);
            muovi();
        }

    public void handleCollision(StationaryEntity e) {
            this.solidCollision(e);
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
