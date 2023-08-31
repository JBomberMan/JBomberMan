package View;

import Controller.EMouseHandler;
import Model.ETileManager;

import javax.swing.*;
import java.awt.*;

/***
 * Classe per visualizzare la bozza di come verrà il livello
 */
public class ELvlPanel extends JPanel implements Runnable{

    private static ELvlPanel istanza;

    private ETileManager tileM = new ETileManager(this);
    private EMouseHandler mouseHandler = new EMouseHandler();
    private Thread thread;

    /***
     * Costruttore della schermata
     */
    private ELvlPanel() {
        super();
        this.addMouseListener(mouseHandler);
        thread = new Thread(this);
        setFocusable(true);
        thread.start();

    }

    /***
     * Metodo per runnare il pannello
     */
    @Override
    public void run() {

        while (thread.isAlive()){
            repaint();
        }

    }

    /***
     * Per visualizzare a schermo il pannello
     * @param g il contesto grafico
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        tileM.disegna(g2d);

    }

    /***
     *
     * @return l'istanza corrente del pannello
     */
    public static ELvlPanel getIstanza(){
        istanza = new ELvlPanel();
        return istanza;
    }

}
