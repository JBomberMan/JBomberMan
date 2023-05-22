package Porfiri;

import Taruffi.Disegnabile;
import Taruffi.Grafica.KeyHandler;
import Taruffi.Grafica.Partita;

import java.awt.*;
 import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class BomberMan implements Disegnabile{

    private static int x,y; //posizione del bomberman
    private int vite; //punti vita del bomberman
    private static int velocita; //velocità del bomberman
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public String direction;
    KeyHandler keyH;
    Partita play;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    

    public BomberMan(int x, int y, int puntiVita, int velocita, Image[] Sprite,  KeyHandler keyH, Partita play) {
        this.x = 100;
        this.y = 100; //posizioni di base
        this.vite = puntiVita;
        this.velocita = velocita;
        this.direction = "down";
        //this.Sprite = Sprite;
       //this.indiceAnimazione = 0;
       this.keyH = keyH;
       this.play = play;
        getPlayerImage();
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


    public void Muovi(){
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



    @Override
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

    public static int getX(){
        return x;
    }
    public static int getY(){
        return y;
    }

}
