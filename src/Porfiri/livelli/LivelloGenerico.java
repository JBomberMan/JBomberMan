package Porfiri.livelli;

import java.awt.*;

import Taruffi.Disegnabile;
 
public abstract class LivelloGenerico {

    private Image[] animazione; //immagini dell'animazione
    private int indiceAnimazione; //indice dell'immagine corrente
    private Disegnabile[] livello; 

    public LivelloGenerico(Image[] animazione, Disegnabile[] livello) {
        this.animazione = animazione;
        this.livello = livello;
        this.indiceAnimazione = 0;
    }

    public  void livello(){
        //chiamam i blocchi non distruttibili e chiama anche costruttori blocchi distruttibili che vengono passatti dal livello specifico
    } 

    

    public Image nextSprite(){
        return animazione[indiceAnimazione++ % animazione.length];
    }

    //GETTER

    public Image[] getAnimazione() {
        return animazione;
    }

    public int getIndiceAnimazione() {
        return indiceAnimazione;
    }

    //SETTER
    public void setAnimazione(Image[] animazione) {
        this.animazione = animazione;
    }

    public void setIndiceAnimazione(int indiceAnimazione) {
        this.indiceAnimazione = indiceAnimazione;
    }

}

