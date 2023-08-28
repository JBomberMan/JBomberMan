package View;

import java.awt.*;

/***
 * Classe per visualizzare a schermo la quantità di gettoni riprova disponibili
 */
public class Gettoni {
    /***
     *
     * @param g il contesto grafico
     * @param vite il numero di gettoni disponibili
     * @param x la posizione x in cui deve essere disegnato
     * @param y lla posizione y in cui deve essere disegnato
     */
    public static void disegna(Graphics2D g, int vite, int x, int y){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Gettoni: " + vite, x, y);
    }
}
