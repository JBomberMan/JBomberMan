package View;

import java.awt.*;

public class Gettoni {

    public static void disegna(Graphics2D g, int vite, int x, int y){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Gettoni: " + vite, x, y);
    }
}
