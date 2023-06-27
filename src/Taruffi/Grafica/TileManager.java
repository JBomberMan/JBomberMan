package Taruffi.Grafica;

import Gobjects.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TileManager {

    Partita partita; //equivale al suo gamepanel
    public Tile[] tile;
    KeyHandler keyH;
    int mapTileNum[][];
    ArrayList<Bomba> bombs = new ArrayList<>();
    private ArrayList<Bomba> bombe = new ArrayList<Bomba>();
    private int numeroBombe = 3;
    private int raggioBombe;
    private int bombeAttive = 0;
    ArrayList<MovingEntity> movingEntities = new ArrayList<>();
    ArrayList<StationaryEntity> stationaryEntities = new ArrayList<>();
    ArrayList<TileObject> tiles = new ArrayList<>();
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
                            movingEntities.add(new Bomberman(col*64, row*64,tile[6].immagine, 4, 4, keyH, partita));
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

        }
        for(MovingEntity entity : movingEntities){
            entity.disegna(g2);

            entity.update();
        }
        for(Bomba b : bombe){
            b.disegna(g2);
            b.update();
        }
        checkCollision();

    }
    public void piazzaBomba(){
        if(bombeAttive < numeroBombe){
            bombe.add(new Bomba(movingEntities.get(indexBomberman).getX(),movingEntities.get(indexBomberman).getY(),null, this.partita));
            bombeAttive++;
        }
    }
    public void detonaDistanza(){
        for(Bomba b : bombe){
            b.setTimer(0);
        }
    }
    public void checkCollision(){
        for(MovingEntity entity : movingEntities){ //controlla le collisioni tra bomberman e muri
            for(StationaryEntity entity2 : stationaryEntities){
                if(entity.getHitbox().intersects(entity2.getHitbox())) {
                    entity.handleCollision(entity2);
                }
            }
        }
        for(StationaryEntity entity : stationaryEntities){ //controlla le collisioni tra bombe e blocchi distruttibili
            for(Bomba b : bombe){
                if(entity.getHitbox().intersects(b.getHitbox())) {
                    b.handleCollision(entity);
                }
            }
        }
    }
}