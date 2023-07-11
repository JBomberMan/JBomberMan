package Taruffi.Grafica;

import java.awt.*;

public class Punteggio {

    public void disegna(Graphics2D g, int x, int y, int punteggio){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Punteggio: " + punteggio, x, y);
    }
}
