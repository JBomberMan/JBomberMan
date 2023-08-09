package Taruffi.Grafica;

import java.awt.*;
import javax.swing.*;

public class Partita extends JPanel implements Runnable{ //equivale a GamePanel del tizio dei video che sto freebootando


    private final int originalTileSize = 16; //ogni tile é un blocco 16x16 pixel
    private final int scale = 4; //scalata 4x in quanto le AI che fanno upscale sono 2x o 4x
    public final int tileSize =  originalTileSize * scale; //risoluzione troppo bassa senza scale
                                                            //si puó aumentare la scale se é ancora troppo piccolo
    public final int maxScreenCol = 17; //era 17
    public final int maxScreenRow = 14; //era 13
                                        //dimensioni dello schermo in tiles
                                        //si puó aumentare se si vuole vedere piú della mappa
                                        //il bomberman originale pare essere 13x17
                                        //esclusa la parte con score, timer eccetera
                                        //per includerla bisogna aumentare le dimensioni dello schermo


    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize; //dimensioni dello schermo in pixel

    static Thread gameThread; //thread che gestisce il gioco
                        //quando un Thread starta non si ferma finché nonglielo diciamo noi
                        //la classe implementa Runnable per usare i Threads
    KeyHandler keyHandler = new KeyHandler(this); //gestisce gli input da tastiera


    public TileManager tileM = new TileManager(this, keyHandler); //instanziamo il tileManager
                                                    //passandogli questa istanza di un gamepanel

    public BombManager bombM = new BombManager(keyHandler, this); //instanziamo il bombManager
                                                    //passandogli questa istanza di un gamepanel

    public PunteggioManager punteggioManager = new PunteggioManager(); //instanziamo il punteggioManager

    ViteManager viteManager = new ViteManager(); //instanziamo il viteManager
    private static Partita istanza;
    private MouseHandler mouseHandler = new MouseHandler(this); //gestisce gli input da mouse


    public Partita() {
        istanza = this;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //setta la grandezza del pannello
        this.setBackground(Color.BLACK); //gli dá un background nero di base
                                        //poi da sostituire con sfondo

        this.setDoubleBuffered(true);  //per evitare flickering e migliorare la performance

        this.addKeyListener(keyHandler); //aggiunge il keylistener al pannello
                                        //perché il pannello é il componente che ha il focus
                                        //e quindi riceve gli input da tastiera

        this.addMouseListener(mouseHandler); //aggiunge il mouselistener al pannello

        this.setFocusable(true); //perché il pannello possa ricevere gli input da tastiera
    }

    public void riavvia(){
        gameThread = null;
        BombManager.esplosioni.clear();
        tileM = new TileManager(this, keyHandler);
        bombM = new BombManager(keyHandler, this);
        punteggioManager = new PunteggioManager();
        viteManager = new ViteManager();
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        if(gameThread == null){
            gameThread = new Thread(this); //crea il thread
            gameThread.start(); //lo starta e chiama run() automaticamente
        }
    }

    public void chiudi(){
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof JFrame) {
            JFrame frame = (JFrame) window;
            frame.dispose(); //chiude la finestra
        }
        gameThread = null; //ferma il thread
        BombManager.esplosioni.clear(); //rimuove le esplosioni per evitare ci siano quando si riavvia la partita
    }

    public static Partita getIstanza(){
        return istanza;
    }


    @Override
    public void run() { //il metodo che viene eseguito dal thread
                        //implementa il game loop fintanto che il thread esiste
                        //utilizza sleep per limitare il gioco a 60fps

        double drawInterval = 1000000000 / 60.0; //intervallo di tempo tra un frame e l'altro
                                                //in nanosecondi
        double nextDrawTime = System.nanoTime() + drawInterval; //tempo in nanosecondi del prossimo frame

        while(gameThread != null){

            //1: update delle informazioni su ció che accade su schermo
            update();

            //2: disegna il frame
            repaint();

            //3: aspetta il tempo necessario per il prossimo frame
            double remainingTime = nextDrawTime - System.nanoTime();

            if (remainingTime < 0) remainingTime = 0; //se il tempo rimanente é negativo
                                                        //non aspettare
                                                        //perché il gioco é in ritardo
                                                        //e deve recuperare

            try{
                Thread.sleep((long) (remainingTime / 1000000)); //converte in millisecondi
                                                                //perché sleep accetta millisecondi
                nextDrawTime += drawInterval; //aggiorna il tempo del prossimo frame

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }


    public void update(){


       //bombManager.update(); //aggiorna le bombe

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g); //superclasse == Jpanel

        Graphics2D g2 = (Graphics2D) g; //casta il Graphics in Graphics2D
                                        //per poter usare le funzioni di Graphics2D
                                        //come setRenderingHints()

        tileM.draw(g2); //disegna i tiles
        punteggioManager.disegna(g2); //disegna il punteggio
        viteManager.disegna(g2); //disegna le vite

        g2.dispose(); //rilascia le risorse usate da g2, good practice

    }

    public static void stopGameThread(){
        gameThread.stop();
         //ferma il thread
    }

}
