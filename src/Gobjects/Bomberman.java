package Gobjects;

import Porfiri.Esplosione;
import Taruffi.Disegnabile;
import Taruffi.Grafica.*;
import Tomassetti.Collidable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Bomberman implements Collidable {
        private int invTimer = 0;
        private int score = 0;
        private static int x = 0;
        private static int y= 0; //posizione del bomberman
        private static int vite; //punti vita del bomberman
        private static int velocita; //velocità del bomberman

        public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
        public static String direction;
        KeyHandler keyH;
        Partita play;
        public int spriteCounter = 0;
        public int spriteNum = 1;
        Rectangle hitbox;
        Map<PowerUp.Tipo, Integer> powerUps = new HashMap<PowerUp.Tipo, Integer>();

        private static Boolean movimentoMouse;
        private static ArrayList<Coordinate> path;
        private static Iterator<Coordinate> pathIterator;
        private static Coordinate posizioneAttuale;
        private static Coordinate prossimaPosizione;
        private  static Coordinate arrivo;

        public static String getDirezione(){
            return direction;
        }

        public Bomberman(int x, int y,BufferedImage image, int puntiVita, int velocita, KeyHandler keyH, Partita play) {
            Bomberman.x = x;
            Bomberman.y = y; //posizioni di base
            this.direction = "down";
            this.vite = puntiVita;
            this.velocita = velocita;
            //this.Sprite = Sprite;
            //this.indiceAnimazione = 0;
            this.keyH = keyH;
            this.play = play;
            this.hitbox = new Rectangle(x+5,y+5,play.tileSize -10,play.tileSize -10);
            getPlayerImage();
            powerUps.put(PowerUp.Tipo.SuperaBlocchi, 0);
            powerUps.put(PowerUp.Tipo.SuperaBombe, 0);
            movimentoMouse = false;
        }

    public static int getX() {
            return x;
    }
    public static int getY() {
            return y;
    }

    public static int getVite() {
            return vite;
    }

    public Rectangle getHitbox() {
            return hitbox;
    }

    public void setExtraTime(int extraTime) {
    }
    public void setRaggioEsplosione(int raggioEsplosione) {
    }

    public void getPlayerImage() {
            try {
                up1 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_up1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_up2.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_down1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_down2.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_right1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_right2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_left1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_left2.png"));

            } catch(IOException e){
                e.printStackTrace();
            }
        }

    public static void switchMovimentoMouse(ArrayList<Coordinate> pathh) {
        movimentoMouse = !movimentoMouse; //abilita o disabilita il movimento con mouse
        path = pathh; //copia il path calcolato dal mouse
        posizioneAttuale = path.get(0);
        arrivo = path.get(path.size()-1);
        x = (posizioneAttuale.getX()*64);
        y = (posizioneAttuale.getY()*64);
        pathIterator = path.iterator(); //inizializza l'iteratore
        prossimaPosizione = pathIterator.next();
    }



    public void muovi(){
        if(movimentoMouse){
            muoviConMouse();
        }
        else {

            if (keyH.up == true) {
                direction = "up";
                y -= velocita;
            }
            if (keyH.down == true) {
                direction = "down";
                y += velocita;
            }
            if (keyH.left == true) {
                direction = "left";
                x -= velocita;
            }
            if (keyH.right == true) {
                direction = "right";
                x += velocita;
            }
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void piazzaBomba(){
        //TODO  implementare il piazzamento della bomba
    }

    public void DetonaADistanza(){
        //TODO: implementare detonazione a distanza
        //Idea: metodo che restituisce ogni bomba presente sul campo da gioco
        //e per ognuna setta il suo timer a 0 in modo che al prossimo update esplodano tutte
    }

    public void disegna(Graphics2D g2) {
        //g2.setColor(Color.WHITE); //setta il colore di sfondo

        //g2.fillRect(x, y, play.tileSize, play.tileSize); //disegna qualcosa
        //sará sostituito con il bomebrman
        //con relative posizioni eccetera
        BufferedImage image = down1;

        switch(direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, play.tileSize, play.tileSize, null);

    }

    public void update(){
        this.hitbox.setBounds(x+5, y+5, play.tileSize-10, play.tileSize-10);
        muovi();
        if(invTimer > 0){
            invTimer--;
        }
        for(PowerUp.Tipo p : powerUps.keySet()){
            if(powerUps.get(p) > 0){
                powerUps.put(p, powerUps.get(p) - 1);
            }
        }
    }
    @Override
    public void handleCollision(StationaryEntity e) {
            if(powerUps.get(PowerUp.Tipo.SuperaBlocchi) > 0 && e.isDistruttibile){
            }
            else{
                this.solidCollision(e);
            }

    }

    public void handleCollision(PowerUp p){
            p.raccogli(this);
    }

    public void handleCollision(Bomba b){

            if(powerUps.get(PowerUp.Tipo.SuperaBombe) > 0){
            }
            else{
                this.solidCollision(b);
            }
    }
    public void setScore(int score){
            this.score += score;
    }
    public void handleCollision(Esplosione e){
            if(this.invTimer == 0){
                this.vite--;
                this.invTimer = 71;
                System.out.println("Vite rimaste: " + this.vite);
                if (this.vite <= 0){
                    System.out.println("Hai perso!");
                    SchermataSconfitta.getIstanza().setVisible(true);
                    Partita.stopGameThread();
                }
            }

    }
    public void addToMap(PowerUp.Tipo p){
            if(powerUps.containsKey(p)){
                powerUps.put(p ,600);
            }
            else{
                powerUps.put(p, 600);
            }
    }
    public void setVite(int vite){
        this.vite += vite;
    }
    void solidCollision(GameEntity obj) {
        Rectangle2D intersection = this.hitbox.createIntersection(obj.hitbox);

        // Vertical collision
        if (intersection.getWidth() >= intersection.getHeight()) {
            // From the top
            if (intersection.getMaxY() >= this.hitbox.getMaxY()) {
                this.y -= 5;
            }
            // From the bottom
            if (intersection.getMaxY() >= obj.hitbox.getMaxY()) {

                this.y += 5;
            }

            // Smoothing around corners
            if (intersection.getWidth() < 16) {
                if (intersection.getMaxX() >= this.hitbox.getMaxX()) {

                    this.x -= 0.5;
                }
                if (intersection.getMaxX() >= obj.hitbox.getMaxX()) {

                    this.x += 0.5;
                }
            }
        }

        // Horizontal collision
        if (intersection.getHeight() >= intersection.getWidth()) {
            // From the left
            if (intersection.getMaxX() >= this.hitbox.getMaxX()) {

                this.x -= 5;
            }
            // From the right
            if (intersection.getMaxX() >= obj.hitbox.getMaxX()) {

                this.x += 5;
            }

            // Smoothing around corners
            if (intersection.getHeight() < 16) {
                if (intersection.getMaxY() >= this.hitbox.getMaxY()) {

                    this.y -= 0.5;
                }
                if (intersection.getMaxY() >= obj.hitbox.getMaxY()) {
                    this.y += 0.5;
                }
            }
        }
    }
    public boolean getDetona(){
            return powerUps.get(PowerUp.Tipo.ControlloRemoto) > 0;
    }

    public static void muoviConMouse(){
        boolean finito = false;
        if(pathIterator.hasNext()) {
            if (posizioneAttuale.getX() == prossimaPosizione.getX() && posizioneAttuale.getY() == prossimaPosizione.getY()) {
                System.out.println("posizione attuale: " + posizioneAttuale.getX() + " " + posizioneAttuale.getY());
                prossimaPosizione = pathIterator.next();

            }
        }



        posizioneAttuale = new Coordinate((x)/64, (y) /64);
        System.out.println("posizione attuale: " + posizioneAttuale.getX() + " " + posizioneAttuale.getY());
        if (prossimaPosizione.getX() > posizioneAttuale.getX()) {
            direction = "right";
            x += velocita;

        }
        if (prossimaPosizione.getX() < posizioneAttuale.getX()) {
            direction = "left";
            x -= velocita;
        }
        if (prossimaPosizione.getY() > posizioneAttuale.getY()) {
            direction = "down";
            y += velocita;
        }
        if (prossimaPosizione.getY() < posizioneAttuale.getY()) {
            direction = "up";
            y -= velocita;
        }
        if(posizioneAttuale.getX() == arrivo.getX() && posizioneAttuale.getY() == arrivo.getY()) movimentoMouse = false;

    }



}
