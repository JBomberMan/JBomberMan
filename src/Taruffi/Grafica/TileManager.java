package Taruffi.Grafica;

import Gobjects.*;

import Taruffi.Nemici.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TileManager {

    Partita partita; //equivale al suo gamepanel
    public Tile[] tile;
    KeyHandler keyH;
    int mapTileNum[][];

    static ArrayList<MovingEntity> movingEntities = new ArrayList<>();
    public static ArrayList<StationaryEntity> stationaryEntities = new ArrayList<>();
    ArrayList<TileObject> tiles = new ArrayList<>();
    static ArrayList<PowerUp> powerUps = new ArrayList<>();

     static ArrayList<MovingEntity> movingEntitiesR = new ArrayList<>();
     static ArrayList<StationaryEntity> stationaryEntitiesR = new ArrayList<>();
     static ArrayList<PowerUp> powerUpsR = new ArrayList<>();
    static ArrayList<Esplosione> codaAggiunte = new ArrayList<>();
    static ArrayList<Esplosione> codaRimozioni = new ArrayList<>();
    private Boss2 boss;
    Bomberman bomber;
    private int indexBomberman;
    private static int livello = 1;
    BufferedReader br;
    File path;
    int numero;

    Boolean personalizzato;

    public void addEntities(MovingEntity entity){
        movingEntities.add(entity);
    }
    public void addEntities(StationaryEntity entity){
        stationaryEntities.add(entity);
    }

    public TileManager(Partita partita, KeyHandler keyH){
        personalizzato = false;
        pulisci();
        path = new File("src/FileLivelli/livello" + livello + ".txt");
        this.partita = partita;
        tile = new Tile[10]; //rappresenta il numero di tile diverse che abbiamo a disposizione
        mapTileNum = new int[partita.maxScreenCol][partita.maxScreenRow];
        this.keyH = keyH;
        numero = 0;
        getTileImage();
        loadMap();
    }

    public TileManager(Partita partita, KeyHandler keyH, String percorso){
        personalizzato = true;
        pulisci();
        path = new File(percorso);
        this.partita = partita;
        tile = new Tile[10]; //rappresenta il numero di tile diverse che abbiamo a disposizione
        mapTileNum = new int[partita.maxScreenCol][partita.maxScreenRow];
        this.keyH = keyH;
        getTileImage();
        loadMap();
    }

    public void pulisci(){
        movingEntities.clear();
        stationaryEntities.clear();
        tiles.clear();
        powerUps.clear();
        movingEntitiesR.clear();
        stationaryEntitiesR.clear();
        powerUpsR.clear();
        codaAggiunte.clear();
        codaRimozioni.clear();
        boss = null;
        path = new File("src/FileLivelli/livello" + livello + ".txt");
    }

    public static void setLivello(int liv){
        livello = liv;
    }


    public TileManager(){
        tile = new Tile[10]; //rappresenta il numero di tile diverse che abbiamo a disposizione
        getTileImage();

    }
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/grass.png"));
            tile[1] = new Tile();
            tile[1].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/muroUp.png"));
            tile[2] = new Tile();
            tile[2].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/muroDown.png"));
            tile[3] = new Tile();
            tile[3].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/muroLeft.png"));
            tile[4] = new Tile();
            tile[4].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/muroRight.png"));
            tile[5] = new Tile();
            tile[5].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/blocco.png"));
            tile[6] = new Tile();
            tile[6].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/bloccoDistruttibile.png"));
            tile[7] = new Tile();
            tile[7].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/white.png"));


            //possiamo aggiungere le altre dopo
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/" + path.toString().replace("src\\","").replace("\\","/"));
            if(is == null){
                //ritorna al menu principale se é l'ultimo livello
                //TODO implementa schermata vittoria (?)
                Storico.addVittoria();
                SwingUtilities.getWindowAncestor(Partita.getIstanza()).dispose();
                ProfiloUtente.getProfilo().setVisible(true);
                return;
            }
            br = new BufferedReader(new java.io.InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < partita.maxScreenCol && row < partita.maxScreenRow) {
                String line = br.readLine();
                while (col < partita.maxScreenCol) {

                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    if (num == 8){
                        tiles.add(new TileObject(col*64, row*64, tile[7].immagine, partita));
                    }else{
                        tiles.add(new TileObject(col*64, row*64, tile[0].immagine, partita));
                    }
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
                            stationaryEntities.add(new Muro(col*64, row*64, tile[6].immagine, true, partita)); //ogni blocco distruttibile ha true
                            break;
                        case 7:
                            bomber = new Bomberman(col*64, row*64,tile[6].immagine, 4, 4, keyH, partita);
                            indexBomberman = movingEntities.size()-1;
                            break;
                        case 9:
                            movingEntities.add(new Oneal(col*64, row*64,tile[6].immagine, 2, 3, partita));
                            break;
                        case 10:
                            movingEntities.add(new Baloon(col*64, row*64,tile[6].immagine, 3, 3, partita));
                            break;
                        case 11:
                            movingEntities.add(new Kondoria(col*64, row*64,tile[6].immagine, 3, 4, partita));
                            break;
                        case 12:
                            movingEntities.add(new Doll(col*64, row*64,tile[6].immagine, 3, 5, partita));
                            break;
                        case 13:
                            movingEntities.add(new Ovapi(col*64, row*64,tile[6].immagine, 1, 1, partita));
                            break;
                        case 14:
                            boss = new Boss2(col*64, row*64,tile[6].immagine, 2, 10, partita);
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

    public static ArrayList<StationaryEntity> getStationaryEntities(){
        return stationaryEntities;
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

        
        for(Bomba b : partita.bombM.bombe){
            b.disegna(g2);
            b.update();
        }
        if(boss != null){
            if(!boss.dead){
                boss.disegna(g2);
                boss.update();
            }
        }

        for(Esplosione e : codaAggiunte){ //controlla le collisioni tra bombe e blocchi distruttibili
            for(StationaryEntity entity : stationaryEntities){
                if(entity instanceof Muro){
                    if(e.getHitbox().intersects(entity.getHitbox()) && !entity.isDistruttibile()){
                        this.codaRimozioni.add(e);
                    }

                }

                }
            }
        for (Esplosione e : codaRimozioni){
            codaAggiunte.remove(e);
        }
        this.codaRimozioni.clear();
        partita.bombM.esplosioni.addAll(codaAggiunte);
        this.codaAggiunte.clear();

        for(Esplosione e : partita.bombM.esplosioni){
            e.disegna(g2);
            e.update();
        }
        for(MovingEntity entity : movingEntities){
            if (entity.vite <= -1) {
                addEntityR(entity);
            }
            entity.update();
            entity.disegna(g2);
        }
        for(PowerUp p : powerUps){
            p.disegna(g2);
            p.update();
        }
        for (StationaryEntity r: stationaryEntitiesR){
            removeEntity(r);
            bomber.setScore(10);
        }
        stationaryEntitiesR.clear();
        for (MovingEntity r: movingEntitiesR){
            removeEntity(r);
            bomber.setScore(50);
        }
        movingEntitiesR.clear();
        for (PowerUp r: powerUpsR){
            removeEntity(r);
        }
        for(Bomba r : BombManager.bombeR){
            BombManager.removeBomba(r);
        }
        for(Esplosione r : BombManager.esplosioniR){
            BombManager.removeEsplosione(r);
        }

        BombManager.bombeR.clear();
        BombManager.esplosioniR.clear();
        powerUpsR.clear();
        bomber.update();
        bomber.disegna(g2);
        checkCollision();
        if(boss != null){
            if(boss.dead){

                System.out.println("Hai vinto boss");
                if(!personalizzato) livello++;
                Partita.stopGameThread();
                SchermataVittoria.getIstanza(personalizzato).setVisible(true);


                //ferma il thread, carica prossimo livello
            }
        } else if (movingEntities.size() == 0){

            System.out.println("Hai vinto nemici");
            System.out.println(numero);
            if(!personalizzato) {
                livello++;
            }
            Partita.stopGameThread();
            SchermataVittoria.getIstanza(personalizzato).setVisible(true);



            //ferma il thread, carica prossimo livello
        }

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
        for(MovingEntity mentity : movingEntities){ //controlla le collisioni tra bomberman e nemici
            for(StationaryEntity entity : stationaryEntities){
                if(mentity.getHitbox().intersects(entity.getHitbox())){
                    mentity.handleCollision(entity);
                }
            }
            for(Bomba b : partita.bombM.bombe){
                if(mentity.getHitbox().intersects(b.getHitbox())){
                    mentity.handleCollision(b);
                }
            }
            if(bomber.getHitbox().intersects(mentity.getHitbox())) {
                bomber.handleCollision(mentity);
            }
        }
        for(Esplosione e : partita.bombM.esplosioni){ //controlla le collisioni tra bombe e blocchi distruttibili
            for(StationaryEntity entity : stationaryEntities){
                if(e.getHitbox().intersects(entity.getHitbox())){
                    entity.handleCollision(e);

                }
            }
            for(MovingEntity mentity : movingEntities){
                if(e.getHitbox().intersects(mentity.getHitbox())){
                    mentity.handleCollision(e);
                }
            }
            if(bomber.getHitbox().intersects(e.getHitbox())){
                bomber.handleCollision(e);
            }
            if(boss != null){
                if(!boss.dead) {
                    if (boss.getHitbox().intersects(e.getHitbox())) {
                        boss.handleCollision(e);
                    }
                }
            }
        }
        for(PowerUp p : powerUps){
            if(bomber.getHitbox().intersects(p.getHitbox())){
                bomber.handleCollision(p);
            }
        }
        if(boss != null){
            if(!boss.dead) {
                for (StationaryEntity entity : stationaryEntities) {
                    if (boss.getHitbox().intersects(entity.getHitbox())) {
                        boss.handleCollision(entity);
                    }
                }
                if(bomber.getHitbox().intersects(boss.getHitbox())){
                    bomber.handleCollision(boss);
                }
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

    public static void addEntity(GameEntity entity) {
        if (entity instanceof MovingEntity) {
            movingEntities.add((MovingEntity) entity);
        } else if (entity instanceof PowerUp) {
            powerUps.add((PowerUp) entity);
        } else if (entity instanceof StationaryEntity) {
            stationaryEntities.add((StationaryEntity) entity);
        }}

    public static void addEntityR (GameEntity entity){

        if (entity instanceof MovingEntity) {
            movingEntitiesR.add((MovingEntity) entity);
        } else if (entity instanceof PowerUp) {
            powerUpsR.add((PowerUp) entity);
        } else if (entity instanceof StationaryEntity) {
            stationaryEntitiesR.add((StationaryEntity) entity);
        }
    }
    public static void AggiungiACoda(Esplosione e){
        codaAggiunte.add(e);
    }
    public void addNumero(){
        numero++;
    }

}