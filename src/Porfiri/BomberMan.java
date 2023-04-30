package Porfiri;

import Taruffi.Disegnabile;

import java.awt.*;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BomberMan implements Disegnabile{

    private static int x,y; //posizione del bomberman
    private int vite; //punti vita del bomberman
    private static int velocita; //velocità del bomberman
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public String direction;

    public BomberMan(int x, int y, int puntiVita, int velocita, Image[] Sprite) {
        this.x = 100;
        this.y = 100; //posizioni di base
        this.vite = puntiVita;
        this.velocita = velocita;
        //this.Sprite = Sprite;
       // this.indiceAnimazione = 0;
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
        up2 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
        down1 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
        down2 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
        right1 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
        right2 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
        left1 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
        left2 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
        
    } catch(IOException e){
            e.printStackTrace();
        }
    }


    public void Muovi(){
        //TODO: implementare il movimento del bomberman
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
    public void disegna() {
        //TODO: implementare il disegno del bomberman
    }

    public static int getX(){
        return x;
    }
    public static int getY(){
        return y;
    }
}
