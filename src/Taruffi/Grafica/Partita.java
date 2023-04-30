package Taruffi.Grafica;
import Porfiri.BomberMan;

import java.awt.*;
import javax.swing.JPanel;

public class Partita extends JPanel implements Runnable{ //equivale a GamePanel del tizio dei video che sto freebootando


    private final int originalTileSize = 16; //ogni tile é un blocco 16x16 pixel
    private final int scale = 4; //scalata 4x in quanto le AI che fanno upscale sono 2x o 4x
    private final int tileSize =  originalTileSize * scale; //risoluzione troppo bassa senza scale
                                                            //si puó aumentare la scale se é ancora troppo piccolo
    private final int maxScreenCol = 17;
    private final int maxScreenRow = 13; //dimensioni dello schermo in tiles
                                        //si puó aumentare se si vuole vedere piú della mappa
                                        //il bomberman originale pare essere 13x17
                                        //esclusa la parte con score, timer eccetera
                                        //per includerla bisogna aumentare le dimensioni dello schermo


    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize; //dimensioni dello schermo in pixel

    Thread gameThread; //thread che gestisce il gioco
                        //quando un Thread starta non si ferma finché nonglielo diciamo noi
                        //la classe implementa Runnable per usare i Threads
    KeyHandler keyHandler = new KeyHandler(); //gestisce gli input da tastiera


    public Partita() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //setta la grandezza del pannello
        this.setBackground(Color.BLACK); //gli dá un background nero di base
                                        //poi da sostituire con sfondo

        this.setDoubleBuffered(true);  //per evitare flickering e migliorare la performance

        this.addKeyListener(keyHandler); //aggiunge il keylistener al pannello
                                        //perché il pannello é il componente che ha il focus
                                        //e quindi riceve gli input da tastiera
        this.setFocusable(true); //perché il pannello possa ricevere gli input da tastiera
    }

    public void startGameThread(){
        if(gameThread == null){
            gameThread = new Thread(this); //crea il thread
            gameThread.start(); //lo starta e chiama run() automaticamente
        }
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

        if(keyHandler.up == true){
            //TODO: implementare movimento verso l'alto
            System.out.println("up");
        }
        if(keyHandler.down == true){
            //TODO: implementare movimento verso il basso
            System.out.println("down");
        }
        if(keyHandler.left == true){
            //TODO: implementare movimento a sx
            System.out.println("left");
        }
        if(keyHandler.right == true){
            //TODO: implementare movimento a dx
            System.out.println("right");
        }

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g); //superclasse == Jpanel

        Graphics2D g2 = (Graphics2D) g; //casta il Graphics in Graphics2D
                                        //per poter usare le funzioni di Graphics2D
                                        //come setRenderingHints()
        g2.setColor(Color.WHITE); //setta il colore di sfondo

        g2.fillRect(BomberMan.getX(), BomberMan.getY(), tileSize, tileSize); //disegna qualcosa
                                                            //sará sostituito con il bomebrman
                                                            //con relative posizioni eccetera

        g2.dispose(); //rilascia le risorse usate da g2, good practice

    }

}
