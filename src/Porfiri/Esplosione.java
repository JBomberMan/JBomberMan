package Porfiri;

import Taruffi.Disegnabile;

import java.awt.*;

public class Esplosione implements Disegnabile{

    int raggio; //raggio dell'esplosione
    int danno;// danno dell'esplosione
    Image[] Sprite; //immagini dell'animazione
    int indiceAnimazione; //indice dell'immagine corrente

    
    public Esplosione(int raggio, int danno, Image[] Sprite) {
        this.raggio = raggio;
        this.danno = danno;
        this.Sprite = Sprite;
        this.indiceAnimazione = 0;
    }

   

    public Image nextSprite(){
        return Sprite[indiceAnimazione++ % Sprite.length];
    }



    @Override
    public void disegna(Graphics2D g2) {
        // TODO Auto-generated method stub
    }

 

}

