package Taruffi.Grafica;

import javax.swing.*;
import java.awt.*;

public class ELvlPanel extends JPanel implements Runnable{

    ETileManager tileM = new ETileManager(this);
    Thread thread;
    public ELvlPanel() {
        super();
        thread = new Thread(this);
        setFocusable(true);
        thread.start();

    }

    @Override
    public void run() {

        repaint();


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        tileM.disegna(g2d);

    }


}
