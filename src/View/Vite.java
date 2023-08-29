package View;

import java.awt.*;

/***
 * Classe che rappresenta le vite a schermo
 */
public class Vite {
    /***
     *
     * @param g il contesto grafico
     * @param vite il numero di vite che si hanno
     * @param x la posizione x in cui devono essere disegnate
     * @param y la posizione y in cui devono essere disegnate
     */
    public static void disegna(Graphics2D g, int vite, int x, int y){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString(""+vite, x, y);
    }
}
