package Model;

import Controller.KeyHandler;
import View.AudioManager;

import java.awt.*;
import java.util.ArrayList;

/**
 * classe che gestisce le bombe
 */
public class BombManager {

    public static ArrayList<Bomba> bombe = new ArrayList<Bomba>();
    private static int numeroBombe;
    private static int raggioBombe;
    private static int bombeAttive = 0;
    private KeyHandler keyHandler;
    Partita play;
    public static ArrayList<Esplosione> esplosioni = new ArrayList<Esplosione>();
    public static ArrayList<Bomba> bombeR = new ArrayList<Bomba>();
    public static ArrayList<Esplosione> esplosioniR = new ArrayList<Esplosione>();


    /**
     * costruttore
     * @param keyHandler gestore dei tasti
     * @param play riferimento alla partita
     */
    public BombManager(KeyHandler keyHandler, Partita play){
        numeroBombe = 3;
        raggioBombe = 1;
        this.keyHandler = keyHandler;
        this.play = play;
    }

    /**
     * metodo che disegna le bombe e le esplosioni
     * @param g2 il contesto grafico
     */
    public void disegna(Graphics2D g2){
        bombe.forEach(b -> b.disegna(g2));
        esplosioni.forEach(e -> e.disegna(g2));

    }

    /**
     * metodo per resettare il bombmanager
     */
    public void pulisci(){
        bombe.clear();
        esplosioni.clear();
        bombeR.clear();
        esplosioniR.clear();
        bombeAttive = 0;
        numeroBombe = 3;
        raggioBombe = 1;
        System.out.println(getBombeAttive());
    }

    /**
     * metodo per rimuovere una bomba esplosa
     * @param bomba la bomba da rimuovere
     */
    public static void togliBomba(Bomba bomba){
        bombeR.add(bomba);
        bombeAttive--;
    }

    /**
     * metodo per piazzare una bomba sempre centrata nella tile di fronte al bomberman
     */
    public void piazzaBomba(){

        if(bombeAttive < numeroBombe){
            String direzione = Bomberman.getDirezione();
            int tileOrizzontale = 0;
            int tileVerticale = 0;
            switch (direzione){
                case("up"):
                    tileOrizzontale = (Bomberman.getX() + (play.tileSize/2)) / play.tileSize;
                    tileVerticale = (Bomberman.getY()-64 + (play.tileSize/2)) / play.tileSize;
                    break;
                case("down"):
                    tileOrizzontale = (Bomberman.getX() + (play.tileSize/2)) / play.tileSize;
                    tileVerticale = (Bomberman.getY()+64 + (play.tileSize/2)) / play.tileSize;
                    break;
                case("left"):
                    tileOrizzontale = (Bomberman.getX()-64 + (play.tileSize/2)) / play.tileSize;
                    tileVerticale = (Bomberman.getY() + (play.tileSize/2)) / play.tileSize;
                    break;
                case("right"):
                    tileOrizzontale = (Bomberman.getX()+64 + (play.tileSize/2)) / play.tileSize;
                    tileVerticale = (Bomberman.getY() + (play.tileSize/2)) / play.tileSize;
                    break;
            }
            for(StationaryEntity e: TileManager.stationaryEntities){
                if(e.getX() == tileOrizzontale* play.tileSize && e.getY() == tileVerticale* play.tileSize){
                    return;
                }
            }

            bombe.add(new Bomba(tileOrizzontale* play.tileSize,tileVerticale* play.tileSize,null, this.play, raggioBombe));
            bombeAttive++;
        }
        AudioManager.getInstance().play(3);
    }

    /**
     * metodo per piazzare una bomba in una posizione specifica
     * @param x la posizione x
     * @param y la posizione y
     */
    public void piazzaBomba(int x, int y){
        int tileOrizzontale = (x / play.tileSize) * play.tileSize;
        int tileVerticale = (y / play.tileSize) * play.tileSize;
        bombe.add(new Bomba(tileOrizzontale,tileVerticale,null, this.play, raggioBombe));

    }


    /**
     * metodo per far detonare a distanza le bombe
     */
    public void detonaDistanza(){
        bombe.forEach(b -> {
            b.esplodi();
            BombManager.togliBomba(b);
        });
    }

    /**
     * metodo per ritornare il numero di bombe attive
     * @return numero di bombe attive
     */
    public int getBombeAttive() {
        return bombeAttive;
    }

    /**
     * metodo per aggiungere un'esplosione
     * @param esplosione esplosione da aggiungere
     */
    public static void addEsplosione(Esplosione esplosione){
        esplosioni.add(esplosione);
        AudioManager.getInstance().play(4);
    }

    /**
     * metodo per rimuovere un'esplosione
     * @param esplosione esplosione da rimuovere
     */
    public static void removeEsplosione(Esplosione esplosione){
        esplosioni.remove(esplosione);
    }

    /**
     * metodo per aggiungere un segmento di una esplosione
     * @param esplosione segmento da aggiungere
     */
    public static void addEsplosioniR(Esplosione esplosione){
        esplosioniR.add(esplosione);
    }

    /**
     * metodo per rimuovere una bomba
     * @param bomba bomba da rimuovere
     */
    public static void removeBomba(Bomba bomba){
        bombe.remove(bomba);
    }

    /**
     * metodo per impostare il raggiop delle bomba
     * @param raggioBombe raggio delle bombe
     */
    public void setRaggioBombe(int raggioBombe) {
        BombManager.raggioBombe += raggioBombe;
    }
}
