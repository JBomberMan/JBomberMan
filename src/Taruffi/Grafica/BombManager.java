package Taruffi.Grafica;
import Porfiri.Bomba;
import Porfiri.BomberMan;

import java.awt.*;
import java.util.ArrayList;

public class BombManager {

    private static ArrayList<Bomba> bombe = new ArrayList<Bomba>();
    private static int numeroBombe;
    private int raggioBombe;
    private static int bombeAttive = 0;
    private static KeyHandler keyHandler;

    public BombManager(KeyHandler keyHandler){
        numeroBombe = 3;
        raggioBombe = 1;
        this.keyHandler = keyHandler;
        //bombe.add(new Bomba(100,100));
    }

    public void addBomba(){
        numeroBombe++;
    }

    public void addRaggio(){
        raggioBombe++;
    }

    public static void disegna(Graphics2D g2){
        for(Bomba b : bombe){
            b.disegna(g2);
        }
    }

    public static void piazzaBomba(){
        if(bombeAttive < numeroBombe){
            bombe.add(new Bomba(BomberMan.getX(),BomberMan.getY()));
            bombeAttive++;
        }
    }
    public static void detonaDistanza(){
        for(Bomba b : bombe){
            b.setTimer(0);
        }
    }

}
