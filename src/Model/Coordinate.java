package Model;

import java.util.Objects;

/**
 * Classe per rappresentare le coordinate di un oggetto
 */
public class Coordinate {
    public int x;
    public int y;

    /**
     * Costruttore della classe
     * @param x coordinate x
     * @param y coordinate y
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Metodo per confrontare due oggetti di tipo Coordinate
     * @param obj oggetto da confrontare
     * @return true se le coordinate sono uguali, false altrimenti
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinate other = (Coordinate) obj;
        return x == other.x && y == other.y;
    }

    /**
     * Metodo per ottenere l'hashcode di un oggetto di tipo Coordinate
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * metodo per ottenere la coordinata x
     * @return coordinata x
     */
    public int getX() {
        return x;
    }

    /**
     * metodo per ottenere la coordinata y
     * @return coordinata y
     */
    public int getY() {
        return y;
    }


}
