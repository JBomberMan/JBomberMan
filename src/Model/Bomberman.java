package Model;

import Controller.*;
import View.SchermataSconfitta;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Classe per rappresentare il bomberman
 */
public class Bomberman implements Collidable {
    private int invTimer = 0;
    private int score = 0;
    private static int x = 0;
    private static int y= 0; //posizione del bomberman
    private static int vite; //punti vita del bomberman
    private static int velocita; //velocità del bomberman

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2, damaged1, damaged2, dead1, dead2;
    public static String direction;
    KeyHandler keyH;
    Partita play;
    public static int spriteCounter = 0;
    public static int spriteNum = 1;
    Rectangle hitbox;
    Map<PowerUp.Tipo, Integer> powerUps = new HashMap<PowerUp.Tipo, Integer>();

    private static Boolean movimentoMouse;
    private static ArrayList<Coordinate> path;
    private static Iterator<Coordinate> pathIterator;
    private static Coordinate posizioneAttuale;
    private static Coordinate prossimaPosizione;
    private  static Coordinate arrivo;

    public static boolean dead = false;
    public int dtimer = 50;
    public static int gettoni = 1;

    public static String getDirezione(){
        return direction;
    }
    public static int getGettoni(){
        return gettoni;
    }

    /**
     * Costruttore del bomberman
     * @param x posizione x
     * @param y posizione y
     * @param image immagine del bomberman
     * @param puntiVita punti vita
     * @param velocita velocità
     * @param keyH riferimento a keyhandler
     * @param play riferimento a partita
     */
    public Bomberman(int x, int y,BufferedImage image, int puntiVita, int velocita, KeyHandler keyH, Partita play) {
        Bomberman.x = x;
        Bomberman.y = y;
        this.direction = "down";
        this.vite = puntiVita;
        this.velocita = velocita;
        this.keyH = keyH;
        this.play = play;
        this.hitbox = new Rectangle(x+5,y+5,play.tileSize -10,play.tileSize -10);
        getPlayerImage();
        powerUps.put(PowerUp.Tipo.SuperaBlocchi, 0);
        powerUps.put(PowerUp.Tipo.SuperaBombe, 0);
        movimentoMouse = false;
    }

    /**
     * Metodo per avere la posizione x del bomberman
     * @return posizione x
     */
    public static int getX() {
            return x;
    }

    /**
     * Metodo per avere la posizione y del bomberman
     * @return posizione y
     */
    public static int getY() {
            return y;
    }

    /**
     * Metodo per settare il bomberman come morto
     * @param death booleano che indica se il bomberman è morto
     */
    public static void setDead(Boolean death){
        dead = death;
    }

    /**
     * Metodo per avere le vite del bomberman
     * @return vite
     */
    public static int getVite() {
            return vite;
    }

    /**
     * metodo per resettare i getoni del bomberman
     */
    public static void resetVite() {
            gettoni = 1;
    }

    /**
     * metodo per avere la hitbox del bomberman
     * @return hitbox
     */
    public Rectangle getHitbox() {
            return hitbox;
    }

    /**
     * metodo per aggiungere tempo di vita al bomberman
     * @param extraTime il tempo da aggiungere
     */
    public void setExtraTime(int extraTime) {
        play.tempoManager.addSec(extraTime);
    }

    /**
     * metodo per settare il raggio delle eslosioni
     * @param raggioEsplosione il raggio delle esplosioni
     */
    public void setRaggioEsplosione(int raggioEsplosione) {
            play.bombM.setRaggioBombe(raggioEsplosione);
    }

    /**
     * metodo per caricare le immagini del bomberman
     */
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
                damaged1 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_damaged1.png"));
                damaged2 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_damaged2.png"));
                dead1 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_dead1.png"));
                dead2 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_dead2.png")); 

            } catch(IOException e){
                e.printStackTrace();
            }
        }

    /**
     * metodo per gestire il movimento con mouse del bomberman
     * @param pathh Percorso di coordinate da far intraprendere al bomberman
     */
    public static void switchMovimentoMouse(ArrayList<Coordinate> pathh) {
        movimentoMouse = !movimentoMouse;
        path = pathh;
        try{
            posizioneAttuale = path.get(0);
        }catch(IndexOutOfBoundsException e){
            movimentoMouse = false;
            return;
        }

        arrivo = path.get(path.size()-1);
        x = (posizioneAttuale.getX()*64);
        y = (posizioneAttuale.getY()*64);
        pathIterator = path.iterator();
        prossimaPosizione = pathIterator.next();
    }


    /**
     * metodo per gestire il movimento del bomberman
     */
    public void muovi(){
        if(!dead){
            if(movimentoMouse){
                if(!(keyH.down || keyH.up || keyH.left || keyH.right)){
                    muoviConMouse();
                }else{
                    movimentoMouse = false;
                }
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
    }


    /**
     * metodo per disegnare il bomberman
     * @param g2 il contesto grafico
     */
    public void disegna(Graphics2D g2) {

        BufferedImage image = down1;
        if(invTimer == 0 && !dead){
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
        }}
        else if (dead) {
            if(spriteNum == 1){
                    image = dead1;
                }
                if(spriteNum == 2){
                    image = dead2;
                }
        }
        else {
            if(spriteNum == 1){
                    image = damaged1;
                }
                if(spriteNum == 2){
                    image = damaged2;
                }
        }
        g2.drawImage(image, x, y, play.tileSize, play.tileSize, null);

    }

    /**
     * metodo per aggiornare lo stato del bomberman
     */
    public void update(){
        this.hitbox.setBounds(x+10, y+10, play.tileSize-10, play.tileSize-10);
        muovi();
        if(dead){
            this.dtimer--;
            if(dtimer == 0) {
                System.out.println("Hai perso!");
                SchermataSconfitta.getIstanza().setVisible(true);
                Partita.stopGameThread();
            }
        }
        else if(invTimer > 0){
            invTimer--;
        }
        for(PowerUp.Tipo p : powerUps.keySet()){
            if(powerUps.get(p) > 0){
                powerUps.put(p, powerUps.get(p) - 1);
            }
        }
    }

    /**
     * metodo per gestire la collisione con un'entità stazionaria
     * @param e l'entità stazionaria con cui si è verificata la collisione
     */
    @Override
    public void handleCollision(StationaryEntity e) {
            if(powerUps.get(PowerUp.Tipo.SuperaBlocchi) > 0 && e.isDistruttibile){
            }
            else{

                this.solidCollision(e);

            }
    }

    /**
     * metodo per gestire la collisione con un powerup
     * @param p il powerup con cui si è verificata la collisione
     */
    public void handleCollision(PowerUp p){
            p.raccogli(this);
    }

    /**
     * metodo per gestire la collisione con una bomba
     * @param b la bomba con cui si è verificata la collisione
     */
    public void handleCollision(Bomba b){

            if(powerUps.get(PowerUp.Tipo.SuperaBombe) > 0){
            }
            else{
                this.solidCollision(b);
            }
    }

    /**
     * metodo per settare lo score del bomberman
     * @param score incremento
     */
    public void setScore(int score){
            this.play.punteggioManager.addPunteggio(score);
    }

    /**
     * metodo per gestire la collisione con un'esplosione
     * @param e l'esplosione con cui si è verificata la collisione
     */
    public void handleCollision(Esplosione e){
            if(this.invTimer == 0){
                this.vite--;
                this.invTimer = 71;
                System.out.println("Vite rimaste: " + this.vite);
                if (this.vite <= 0){
                    dead = true;
                }
            }

    }

    /**
     * metodo per gestire la collisione con un nemico
     * @param n il nemico con cui si è verificata la collisione
     */
    public void handleCollision(MovingEntity n){

        if(this.invTimer == 0){
            this.vite--;
            this.invTimer = 71;
        
            System.out.println("Vite rimaste: " + this.vite);
            if (this.vite <= 0){
                dead = true;
                
            }
        }

    }

    /**
     * metodo per aggiungere un powerup alla mappa
     * @param p il tipo di powerup da aggiungere
     */
    public void addToMap(PowerUp.Tipo p){
            if(powerUps.containsKey(p)){
                powerUps.put(p ,600);
            }
            else{
                powerUps.put(p, 600);
            }
    }

    /**
     * metodo per settare le vite del bomberman
     * @param vite il numero di vite da settare
     */
    public void setVite(int vite){
        this.vite += vite;
    }


    /**
     * Metodo che implementa la collisione solida tra due entità
     * @param obj l'entità con cui si è verificata la collisione
     */
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

    /**
     * metodo per controllare che ilbomberman abbuia il detona a distanza
     * @return booleano che indica se il bomberman ha il detona a distanza o no
     */
    public boolean getDetona(){
            return powerUps.get(PowerUp.Tipo.ControlloRemoto) > 0;
    }

    /**
     * metodo per far mouvere il bomberman col mouse
     */
    public void muoviConMouse(){
        if(!dead){
            boolean finito = false;
            if(pathIterator.hasNext()) {
                if (x == prossimaPosizione.getX()*64 && y == prossimaPosizione.getY()*64) {
                    prossimaPosizione = pathIterator.next();
                }
            }

            if (prossimaPosizione.getX()*64 > x) {
                direction = "right";
                x += velocita;

            }
            if (prossimaPosizione.getX()*64 < x) {
                direction = "left";
                x -= velocita;
            }
            if (prossimaPosizione.getY()*64 > y) {
                direction = "down";
                y += velocita;
            }
            if (prossimaPosizione.getY()*64 < y) {
                direction = "up";
                y -= velocita;
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
            if(x == arrivo.getX()*64 && y == arrivo.getY() * 64) movimentoMouse = false;

        }
    }

}
