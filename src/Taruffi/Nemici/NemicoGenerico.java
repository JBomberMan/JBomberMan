package Taruffi.Nemici;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class NemicoGenerico {

    private Image[] animazione; //immagini dell'animazione
    private int indiceAnimazione; //indice dell'immagine corrente
    private int x,y; //posizione del nemico sullo schermo
    private int puntiVita; //punti vita del nemico
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public String direction;

    public NemicoGenerico(Image[] animazione, int x, int y, int puntiVita) {
        this.animazione = animazione;
        this.x = x;
        this.y = y;
        this.puntiVita = puntiVita;
        this.indiceAnimazione = 0;
    }

    public abstract void pattern(); //metodo astratto che definisce il pattern di movimento del nemico

    public boolean eMorto(){
        return puntiVita <= 0;
    }

    public Image nextSprite(){
        return animazione[indiceAnimazione++ % animazione.length];
    }

    /*
    *------------------------------
    *   GETTERS AND SETTERS
    * -----------------------------
    */
    public Image[] getAnimazione() {
        return animazione;
    }

    public int getIndiceAnimazione() {
        return indiceAnimazione;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public void setAnimazione(Image[] animazione) {
        this.animazione = animazione;
    }

    public void setIndiceAnimazione(int indiceAnimazione) {
        this.indiceAnimazione = indiceAnimazione;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPuntiVita(int puntiVita) {
        this.puntiVita = puntiVita;
    }
}
