package Taruffi.Grafica;

import Gobjects.*;
import Porfiri.Esplosione;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TileManager {

    Partita partita; //equivale al suo gamepanel
    public Tile[] tile;
    KeyHandler keyH;
    int mapTileNum[][];

    private int numeroBombe = 3;
    private int raggioBombe;
    private int bombeAttive = 0;
    static ArrayList<MovingEntity> movingEntities = new ArrayList<>();
    public static ArrayList<StationaryEntity> stationaryEntities = new ArrayList<>();
    ArrayList<TileObject> tiles = new ArrayList<>();
    static ArrayList<PowerUp> powerUps = new ArrayList<>();

    Bomberman bomber;
    private int indexBomberman;

    public void addEntities(MovingEntity entity){
        movingEntities.add(entity);
    }
    public void addEntities(StationaryEntity entity){
        stationaryEntities.add(entity);
    }

    public TileManager(Partita partita, KeyHandler keyH){
        this.partita = partita;
        tile = new Tile[10]; //rappresenta il numero di tile diverse che abbiamo a disposizione
        mapTileNum = new int[partita.maxScreenCol][partita.maxScreenRow];
        this.keyH = keyH;
        getTileImage();
        loadMap();

    }


    public TileManager(){
        tile = new Tile[10]; //rappresenta il numero di tile diverse che abbiamo a disposizione
        getTileImage();

    }
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/grass.jpg"));
            tile[1] = new Tile();
            tile[1].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/muroUp.png"));
            tile[2] = new Tile();
            tile[2].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/muroDown.png"));
            tile[3] = new Tile();
            tile[3].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/muroLeft.png"));
            tile[4] = new Tile();
            tile[4].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/muroRight.png"));
            tile[5] = new Tile();
            tile[5].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/blocco.jpg"));
            tile[6] = new Tile();
            tile[6].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/bloccoDistruttibile.jpg"));

            //possiamo aggiungere le altre dopo
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/FileLivelli/livello1.txt");
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < partita.maxScreenCol && row < partita.maxScreenRow) {
                String line = br.readLine();
                while (col < partita.maxScreenCol) {

                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;

                    tiles.add(new TileObject(col*64, row*64, tile[0].immagine, partita));
                    switch (num) {
                        case 1:
                            stationaryEntities.add(new Muro(col*64, row*64, tile[1].immagine, false, partita));
                            break;
                        case 2:
                            stationaryEntities.add(new Muro(col*64, row*64, tile[2].immagine, false, partita));
                            break;
                        case 3:
                            stationaryEntities.add(new Muro(col*64, row*64, tile[3].immagine, false, partita));
                            break;
                        case 4:
                            stationaryEntities.add(new Muro(col*64, row*64, tile[4].immagine, false, partita));
                            break;
                        case 5:
                            stationaryEntities.add(new Muro(col*64, row*64, tile[5].immagine, false, partita));
                            break;
                        case 6:
                            stationaryEntities.add(new Muro(col*64, row*64, tile[6].immagine, true, partita));
                            break;
                        case 7:
                            bomber = new Bomberman(col*64, row*64,tile[6].immagine, 4, 4, keyH, partita);
                            indexBomberman = movingEntities.size()-1;
                            break;
                    }

                    col++;
                }
                if (col == partita.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }

            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        /*
        g2.drawImage(tile[0].immagine, 0, 0, partita.tileSize, partita.tileSize, null);
        g2.drawImage(tile[1].immagine, 64, 0, partita.tileSize, partita.tileSize, null);
        */

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        for(TileObject tile : tiles){
            tile.disegna(g2);
        }

        for(StationaryEntity entity : stationaryEntities){
            entity.disegna(g2);
            entity.update();
        }
        for(MovingEntity entity : movingEntities){
            entity.disegna(g2);

            entity.update();
        }
        for(Bomba b : partita.bombM.bombe){
            b.disegna(g2);
            b.update();
        }
        for(Esplosione e : partita.bombM.esplosioni){
            e.disegna(g2);
            e.update();
        }
        for(PowerUp p : powerUps){
            p.disegna(g2);
            p.update();
        }
        bomber.update();
        bomber.disegna(g2);
        checkCollision();

    }

    public void detonaDistanza(){
        for(Bomba b : partita.bombM.bombe){
            b.setTimer(0);
        }
    }
    public void checkCollision(){
        for(StationaryEntity entity : stationaryEntities){ //controlla le collisioni tra bomberman e muri
            if(bomber.getHitbox().intersects(entity.getHitbox())) {
                bomber.handleCollision(entity);
            }
        }
        for(Bomba b: partita.bombM.bombe){ //controlla le collisioni tra bomberman e bombe
            if(bomber.getHitbox().intersects(b.getHitbox())){
                bomber.handleCollision(b);
            }
        }

        for(Esplosione e : partita.bombM.esplosioni){ //controlla le collisioni tra bombe e blocchi distruttibili
            for(StationaryEntity entity : stationaryEntities){
                if(e.getHitbox().intersects(entity.getHitbox())){
                    entity.handleCollision(e);
                }
            }
            if(bomber.getHitbox().intersects(e.getHitbox())){
                bomber.handleCollision(e);
            }
        }
        for(PowerUp p : powerUps){
            if(bomber.getHitbox().intersects(p.getHitbox())){
                bomber.handleCollision(p);
            }
        }
    }

    public static void removeEntity(GameEntity entity){
        if(entity instanceof MovingEntity){
            movingEntities.remove(entity);
        }
        else if(entity instanceof PowerUp){
            powerUps.remove(entity);
        }
        else if(entity instanceof StationaryEntity){
            stationaryEntities.remove(entity);
        }

    }

    public static void addEntity(GameEntity entity){
        if(entity instanceof MovingEntity){
            movingEntities.add((MovingEntity)entity);
        }
        else if(entity instanceof PowerUp){
            powerUps.add((PowerUp)entity);
        }
        else if(entity instanceof StationaryEntity){
            stationaryEntities.add((StationaryEntity)entity);
        }

    }




}