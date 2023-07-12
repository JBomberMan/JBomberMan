package Taruffi.Grafica;

import javax.imageio.ImageIO;
import java.awt.*;

public class Vite {

    public static void disegna(Graphics2D g, int vite, int x, int y){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Vite: " + vite, x, y);
    }
}
