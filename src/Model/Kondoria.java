package Model;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

import Controller.Gobjects.*;

public class Kondoria  extends MovingEntity implements Collidable{

        private static int velocita;
        public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2, damaged1, damaged2, dead1, dead2;
        public static String direction = "down";
        private int probabilitàDirezione ;
        private int attesaMovimento = 30;
        public int spriteCounter = 0;
        public int spriteNum = 1;
        private int invTimer;
        public boolean dead = false;
        public int dtimer = 50;
        public int trovaTimer = 0;
        private Iterator<Coordinate> pathIterator;
        Coordinate prossimaPosizione;
        
        

    public Kondoria(int x, int y,BufferedImage image, int puntiVita, int velocita, Partita play) {
        super(x, y, image, velocita, puntiVita, play);
        this.velocita = 1;//velocita

        getEnemiesImage();
    }


    public void getEnemiesImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Images/Kondoria/Kondoria (1).png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Images/Kondoria/Kondoria (2).png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Images/Kondoria/Kondoria (3).png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Images/Kondoria/Kondoria (4).png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Images/Kondoria/Kondoria (5).png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Images/Kondoria/Kondoria (6).png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Images/Kondoria/Kondoria (7).png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Images/Kondoria/Kondoria (8).png"));
            damaged1 = ImageIO.read(getClass().getResourceAsStream("/Images/Kondoria/Kondoria (9).png"));
            damaged2 = ImageIO.read(getClass().getResourceAsStream("/Images/Kondoria/Kondoria (10).png"));
            dead1 = ImageIO.read(getClass().getResourceAsStream("/Images/Kondoria/Kondoria (11).png"));
            dead2 = ImageIO.read(getClass().getResourceAsStream("/Images/Kondoria/Kondoria (13).png"));
        
    } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        if(trovaTimer == 0){
            pathIterator = trovaBomberman().iterator();
            try{
                prossimaPosizione = pathIterator.next();
            }catch(NoSuchElementException e){
                prossimaPosizione = prossimaPosizione;
            }
            //prossimaPosizione = pathIterator.next();
            trovaTimer = 64;
        }
        trovaTimer--;
        this.hitbox.setBounds(x+5, y+5, play.tileSize-10, play.tileSize-10);
        muovi();
        if(dead){
            this.dtimer--;
            if(dtimer == 0) this.vite--;
        }
        else if(invTimer > 0){
            invTimer--;
        }
        
    }


    public void muovi(){

        //Coordinate prossimaPosizione = pathIterator.next();
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
    }
        
    

    

    @Override
    public void disegna(Graphics2D g2d) {
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
            }
        }
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
        g2d.drawImage(image, x, y, play.tileSize, play.tileSize, null);
    }


    public void handleCollision(Esplosione e){
        if(this.invTimer == 0){
            this.vite--;
            this.invTimer = 50;
            System.out.println("Vite rimaste Nemico: " + this.vite);

            if (this.vite <= 0){
                dead = true;
                System.out.println("Nemico Sconfitto!");
            }
        }
    }

    public void handleCollision(StationaryEntity se){
        if(!se.isDistruttibile()){
            this.solidCollision(se);
        }
        
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

    public ArrayList<Coordinate> trovaBomberman(){


        //trova la casella dove si é cliccato
        int bombx = Bomberman.getX()/64;
        int bomby = Bomberman.getY()/64;


        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < 17; j++) {
                row.add(0);
            }
            map.add(row);
        }
        ArrayList<StationaryEntity> tiles = TileManager.getStationaryEntities();
        for(StationaryEntity tile : tiles){
            if(!tile.isDistruttibile()){
                int xx = tile.getX()/64;
                int yy = tile.getY()/64;
                map.get(yy).set(xx, 1);
            }

        }

        map.get(this.getY()/64).set(this.getX()/64, 2);
        //map.get(Bomberman.getY()/64).set(Bomberman.getX()/64, 3);

        //a questo punto 1=blocco 0=libero 2=nemico 3=bomberman
        ArrayList<Coordinate> path = findShortestPath(this.getX()/64, this.getY()/64, Bomberman.getX()/64, Bomberman.getY()/64, map);

        // Stampa il percorso

        return path;

    }
    public static ArrayList<Coordinate> findShortestPath(int bomberx, int bombery, int mousex, int mousey, ArrayList<ArrayList<Integer>> map) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Direzioni possibili: sinistra, destra, alto, basso
        Queue<Coordinate> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.size()][map.get(0).size()];
        Map<Coordinate, Coordinate> parentMap = new HashMap<>();

        if (bomberx < 0 || bomberx >= map.get(0).size() || bombery < 0 || bombery >= map.size() || map.get(bombery).get(bomberx) == 1) {
            System.out.println("Coordinate del giocatore non valide o posizione bloccata.");
            map.get(mousey).set(mousex,0);

        }

        if (mousex < 0 || mousex >= map.get(0).size() || mousey < 0 || mousey >= map.size() || map.get(mousey).get(mousex) == 1) {
            System.out.println("Coordinate della casella premuta non valide o posizione bloccata.");
            map.get(mousey).set(mousex,0);

        }

        Coordinate start = new Coordinate(bomberx, bombery);
        Coordinate end = new Coordinate(mousex, mousey);

        queue.add(start);
        visited[start.getY()][start.getX()] = true;

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if (current.equals(end)) {
                break; // Abbiamo trovato la casella premuta, interrompiamo l'esplorazione
            }

            for (int[] direction : directions) {
                int newX = current.getX() + direction[0];
                int newY = current.getY() + direction[1];

                if (isValid(newX, newY, map) && !visited[newY][newX] && map.get(newY).get(newX) == 0) {
                    Coordinate neighbor = new Coordinate(newX, newY);
                    queue.add(neighbor);
                    visited[newY][newX] = true;
                    parentMap.put(neighbor, current);
                }
            }
        }

        // Costruisci il percorso dalla casella premuta fino alla casella del giocatore
        ArrayList<Coordinate> path = new ArrayList<>();
        if (!visited[mousey][mousex]) {
            return path; // Nessun percorso disponibile
        }

        Coordinate current = new Coordinate(mousex, mousey);
        while (!current.equals(start)) {
            path.add(current);
            current = parentMap.get(current);

            if (current == null) {
                // Gestisci il caso in cui non esista un percorso
                System.out.println("Percorso non trovato.");
                return new ArrayList<>();
            }
        }
        path.add(start); // Aggiungi la casella di partenza al percorso
        Collections.reverse(path); // Inverti il percorso per ottenere il giusto ordine

        return path;
    }




    private static boolean isValid(int x, int y, ArrayList<ArrayList<Integer>> map) {
        return x >= 0 && x < map.get(0).size() && y >= 0 && y < map.size();
    }
    
}
