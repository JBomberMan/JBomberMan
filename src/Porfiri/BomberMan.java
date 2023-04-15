package Porfiri;

import Taruffi.Disegnabile;

import java.awt.*;

public class BomberMan implements Disegnabile{

    int x,y; //posizione del bomberman
    int vite; //punti vita del bomberman
    int velocita; //velocità del bomberman
    Image[] Sprite; //immagini dell'animazione
    int indiceAnimazione; //indice dell'immagine corrente

    public BomberMan(int x, int y, int puntiVita, int velocita, Image[] Sprite) {
        this.x = x;
        this.y = y;
        this.vite = puntiVita;
        this.velocita = velocita;
        this.Sprite = Sprite;
        this.indiceAnimazione = 0;
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

    public Image nextSprite(){
        return Sprite[indiceAnimazione++ % Sprite.length];
    }

    public void setScore(int score){
        //perché avevamo detto di metterlo qua?
    }


}
