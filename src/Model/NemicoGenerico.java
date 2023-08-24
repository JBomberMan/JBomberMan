package Model;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class NemicoGenerico {

    private Image[] animazione; //immagini dell'animazione
    private int indiceAnimazione; //indice dell'immagine corrente
    private int x,y; //posizione del nemico sullo schermo
    private int puntiVita; //punti vita del nemico

    public String direction;

    public NemicoGenerico(Image[] animazione, int x, int y, int puntiVita) {
        this.animazione = animazione;
        this.x = x;
        this.y = y;
        this.puntiVita = puntiVita;
        this.indiceAnimazione = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
