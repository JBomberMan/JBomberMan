package Taruffi.Grafica;
import Gobjects.Bomberman;
import Gobjects.Bomba;
import Porfiri.BomberMan;

import java.awt.*;
import java.util.ArrayList;

public class BombManager {

    public static ArrayList<Bomba> bombe = new ArrayList<Bomba>();
    private int numeroBombe;
    private int raggioBombe;
    private static int bombeAttive = 0;
    private KeyHandler keyHandler;
    Partita play;

    public BombManager(KeyHandler keyHandler, Partita play){
        numeroBombe = 3;
        raggioBombe = 1;
        this.keyHandler = keyHandler;
        this.play = play;

    }

    public void addBomba(){
        numeroBombe++;
    }

    public void addRaggio(){
        raggioBombe++;
    }

    public void disegna(Graphics2D g2){
        for(Bomba b : bombe){
            b.disegna(g2);
        }
    }

    public static void togliBomba(Bomba bomba){
        bombe.remove(bomba);
        bombeAttive--;
    }

    public void piazzaBomba(){
        if(bombeAttive < numeroBombe){
            bombe.add(new Bomba(Bomberman.getX(),Bomberman.getY(),null, this.play));
            bombeAttive++;
        }
    }
    public void detonaDistanza(){
        for(Bomba b : bombe){
            b.setTimer(0);
        }
    }


}
