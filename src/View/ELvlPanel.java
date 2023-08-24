package View;

import Controller.EMouseHandler;
import Model.ETileManager;

import javax.swing.*;
import java.awt.*;

public class ELvlPanel extends JPanel implements Runnable{

    private static ELvlPanel istanza;

    ETileManager tileM = new ETileManager(this);
    EMouseHandler mouseHandler = new EMouseHandler();
    Thread thread;
    private ELvlPanel() {
        super();
        this.addMouseListener(mouseHandler);
        thread = new Thread(this);
        setFocusable(true);
        thread.start();

    }

    @Override
    public void run() {

        while (thread.isAlive()){
            repaint();
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        tileM.disegna(g2d);

    }

    public static ELvlPanel getIstanza(){
        if(istanza == null){
            istanza = new ELvlPanel();
        }
        return istanza;
    }

}
