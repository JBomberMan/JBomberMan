package Model;

import java.awt.Graphics2D;

/**
 * Interfaccia per disegnare a schermo
 */
public interface Disegnabile {
    /**
     * metodo per disegnare a schermo
     * @param g il contesto grafico
     */
    public void disegna(Graphics2D g);
}
