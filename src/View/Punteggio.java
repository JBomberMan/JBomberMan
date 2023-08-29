package View;

import javax.imageio.ImageIO;
import java.awt.*;

/***
 * Classe per rappresentare a schermo il punteggio attuale
 */
public class Punteggio {
    /***
     *
     * @param g il contesto grafico
     * @param x la posizione x in cui deve essere disegnato il punteggio
     * @param y la posizione y in cui devve essere disegnato il punteggio
     * @param punteggio il valore attuale del punteggio
     */
    public void disegna(Graphics2D g, int x, int y, int punteggio){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        try{
            g.drawImage(ImageIO.read(getClass().getResource("/Images/punteggio.png")), 0, 832, 1088, 64, null);
        }catch (Exception e){
            e.printStackTrace();
        }
        g.drawString("" + punteggio, x, y);


    }
}
