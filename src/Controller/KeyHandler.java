package Controller;

import Model.Partita;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public Boolean up, down, left, right, space, e;
    private int spaceTiming = 0;
    Partita play;

    public KeyHandler(Partita play){
        up = false;
        down = false;
        left = false;
        right = false;
        space = false;
        e = false;
        this.play = play;
    }

    public void pulisci(){
        up = false;
        down = false;
        left = false;
        right = false;
        space = false;
        e = false;
    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

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
                System.out.println("kaboom");
                play.bombM.detonaDistanza();
            };
        }





    }

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
