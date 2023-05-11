package Porfiri;

import Taruffi.Disegnabile;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bomba implements Disegnabile{

    int x,y; //posizione della bomba
    int raggio; //raggio della bomba
    int timer; //timer della bomba
    public BufferedImage time1, time2, time3, time4;


    public Bomba(int x, int y, int timer, Image[] Sprite) {
        this.x = x;
        this.y = y;
        this.timer = timer;
        getBombImage();
    }

    public void getBombImage() {
        try {
            time1 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
            time2 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
            time3 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
            time4 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
        
    } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void Esplodi(){
        //TODO: implementare l'esplodere della bomba
    }

   /*  public Image nextSprite(){
        return Sprite[indiceAnimazione++ % Sprite.length];
    }**/


    @Override
    public void disegna(Graphics2D g2) {
        //TODO implementare il disegno della bomba
    }
}
