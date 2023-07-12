package Taruffi.Grafica;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Punteggio {

    public void disegna(Graphics2D g, int x, int y, int punteggio){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        try{
            g.drawImage(ImageIO.read(getClass().getResource("/Images/punteggio.png")), 0, 832, 1088, 64, null);
        }catch (Exception e){
            e.printStackTrace();
        }
        g.drawString("Punteggio: " + punteggio, x, y);


    }
}
