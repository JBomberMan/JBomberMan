package View;

import java.awt.*;

/***
 *Classe per rappresentare il tempo che scorre sullo schermo
 */
public class Tempo {
    /***
     *
     * @param g il contesto grafico
     * @param x la posizione x in cui deve essere disegnato il counter
     * @param y la posizione y in cui deve essere disegnato il counter
     * @param minuti i minuti nel counter
     * @param secondi i secondi nel counter
     */
    public void disegna(Graphics2D g, int x, int y, int minuti, int secondi){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Timer: " + minuti + ":" + secondi, x, y);


    }
}
