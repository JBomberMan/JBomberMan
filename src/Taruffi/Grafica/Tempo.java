package Taruffi.Grafica;

import javax.imageio.ImageIO;
import java.awt.*;

public class Tempo {

    public void disegna(Graphics2D g, int x, int y, int minuti, int secondi){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Timer: " + minuti + ":" + secondi, x, y);


    }
}
