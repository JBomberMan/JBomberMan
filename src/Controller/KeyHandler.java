package Controller;

import Model.Partita;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Classe che gestisce gli input da tastiera
 */
public class KeyHandler implements KeyListener {

    public Boolean up, down, left, right, space, e;
    private int spaceTiming = 0;
    Partita play;

    /**
     * Costruttore
     * @param play riferimento alla partita
     */
    public KeyHandler(Partita play){
        up = false;
        down = false;
        left = false;
        right = false;
        space = false;
        e = false;
        this.play = play;
    }

    /**
     * Pulisce gli input
     */
    public void pulisci(){
        up = false;
        down = false;
        left = false;
        right = false;
        space = false;
        e = false;
    }


    /**
     * Metodo invocato quando un tasto viene premuto
     * @param e evento generato
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Metodo invocato quando un tasto viene rilasciato
     * @param event evento generato
     */
    @Override
    public void keyPressed(KeyEvent event) {

        int code = event.getKeyCode();
        if(code == KeyEvent.VK_UP)
            up = true;
        if(code == KeyEvent.VK_DOWN)
            down = true;
        if(code == KeyEvent.VK_LEFT)
            left = true;
        if(code == KeyEvent.VK_RIGHT)
            right = true;
        if(code == KeyEvent.VK_SPACE){
            space = true;
            play.bombM.piazzaBomba();

        }
        if(code == KeyEvent.VK_E){
            e = true;
            if(play.tileM.bomber.getDetona()) {

                play.bombM.detonaDistanza();
            };
        }





    }

    /**
     * Metodo invocato quando un tasto viene rilasciato
     * @param e evento generato
     */
    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP)
            up = false;
        if(code == KeyEvent.VK_DOWN)
            down = false;
        if(code == KeyEvent.VK_LEFT)
            left = false;
        if(code == KeyEvent.VK_RIGHT)
            right = false;
        if(code == KeyEvent.VK_SPACE)
            space = false;

    }
}
