package Taruffi.Grafica;
import Gobjects.Bomberman;
import Gobjects.Bomba;
import Porfiri.BomberMan;
import Porfiri.Esplosione;

import java.awt.*;
import java.util.ArrayList;

public class BombManager {

    public static ArrayList<Bomba> bombe = new ArrayList<Bomba>();
    private static int numeroBombe;
    private static int raggioBombe;
    private static int bombeAttive = 0;
    private KeyHandler keyHandler;
    Partita play;
    public static ArrayList<Esplosione> esplosioni = new ArrayList<Esplosione>();

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
        for(Esplosione e : esplosioni){
            e.disegna(g2);
        }
    }

    public static void togliBomba(Bomba bomba){
        bombe.remove(bomba);
        bombeAttive--;
    }

    public void piazzaBomba(){
        if(bombeAttive < numeroBombe){
            //spaghetti code per piazzarle sempre centrate nella tile dove si trova il bomberman
            int tileOrizzontale = (Bomberman.getX() + (play.tileSize/2)) / play.tileSize;
            int tileVerticale = (Bomberman.getY() + (play.tileSize/2)) / play.tileSize;
            bombe.add(new Bomba(tileOrizzontale* play.tileSize,tileVerticale* play.tileSize,null, this.play));
            bombeAttive++;
        }
    }
    public void detonaDistanza(){
        for(Bomba b : bombe){
            b.setTimer(0);
        }
    }

    public static int getRaggioBombe(){
        return raggioBombe;
    }

    public static int getNumeroBombe(){
        return numeroBombe;
    }

    public static int getBombeAttive() {
        return bombeAttive;
    }

    public static void addEsplosione(Esplosione esplosione){
        esplosioni.add(esplosione);
    }

    public static void removeEsplosione(Esplosione esplosione){
        esplosioni.remove(esplosione);
    }


}
