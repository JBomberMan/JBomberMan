package Porfiri;

import Taruffi.Disegnabile;
import java.awt.*;

public class Bomba implements Disegnabile{

    int x,y; //posizione della bomba
    int raggio; //raggio della bomba
    int timer; //timer della bomba
    Image[] Sprite; //immagini dell'animazione
    int indiceAnimazione; //indice dell'immagine corrente

    public Bomba(int x, int y, int raggio, int timer, Image[] Sprite) {
        this.x = x;
        this.y = y;
        this.raggio = raggio;
        this.timer = timer;
        this.Sprite = Sprite;
        this.indiceAnimazione = 0;
    }

    public void Esplodi(){
        //TODO: implementare l'esplodere della bomba
    }

    public Image nextSprite(){
        return Sprite[indiceAnimazione++ % Sprite.length];
    }


    @Override
    public void disegna() {
        //TODO implementare il disegno della bomba
    }
}
