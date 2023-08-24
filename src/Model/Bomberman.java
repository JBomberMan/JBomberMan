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

    public static int getX() {
            return x;
    }
    public static int getY() {
            return y;
    }

    public static void setDead(Boolean death){
        dead = death;
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
            play.bombM.setRaggioBombe(raggioEsplosione);
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
                damaged1 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_damaged1.png"));
                damaged2 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_damaged2.png"));
                dead1 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_dead1.png"));
                dead2 = ImageIO.read(getClass().getResourceAsStream("/Images/BomberMan_dead2.png")); 

            } catch(IOException e){
                e.printStackTrace();
            }
        }

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
            this.play.punteggioManager.addPunteggio(score);
    }

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