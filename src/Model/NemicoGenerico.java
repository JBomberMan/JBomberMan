package Model;
import java.awt.*;
import java.awt.image.BufferedImage;



    /**
     * 
     * Classe che Gestisce le informazioni comuni a tutti i nemici
     * 
     */
public abstract class NemicoGenerico {

    private Image[] animazione; //immagini dell'animazione
    private int indiceAnimazione; //indice dell'immagine corrente
    private int x,y; //posizione del nemico sullo schermo
    private int puntiVita; //punti vita del nemico

    public String direction;

    /**
     * Costruttore della classe Nemico Generico
     * @param image immagine del nemico
     * @param x coordinata x iniziale
     * @param y coordinata y iniziale
     * @param puntiVita numero di vite del nemico
     */
    public NemicoGenerico(Image[] animazione, int x, int y, int puntiVita) {
        this.animazione = animazione;
        this.x = x;
        this.y = y;
        this.puntiVita = puntiVita;
        this.indiceAnimazione = 0;
    }

    /**
     * Metodo per avere la posizione x del nemico
     * @return posizione x
     */
    public int getX() {
        return x;
    }


    /**
     * Metodo per avere la posizione y del nemico
     * @return posizione y
     */
    public int getY() {
        return y;
    }


    /**
     * metodo per settare la cordinata x del nemico
     * @param x cordinata x del nemico
     */
    public void setX(int x) {
        this.x = x;
    }


    /**
     * metodo per settare la cordinata y del nemico
     * @param y cordinata y del nemico
     */
    public void setY(int y) {
        this.y = y;
    }

}
