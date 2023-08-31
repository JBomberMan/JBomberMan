package Model;

import Controller.KeyHandler;

import View.AudioManager;
import View.SchermataCompletamento;
import View.SchermataVittoria;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/***
 * Classe che gestisce le Tile e le entità del gioco
 * @see Tile
 * @see GameEntity
 * @see MovingEntity
 * @see StationaryEntity
 * @see Bomberman
 * @see Muro
 * @see Oneal
 * @see Baloon
 * @see Kondoria
 * @see Doll
 * @see Ovapi
 * @see Boss
 * @see Boss1
 * @see Boss2
 * @see PowerUp
 *
 * Il fulcro delle partite, si occupa di aggiornare e tenere traccia di tutte le entità del gioco, di disegnarle e di gestire le collisioni
 *
 */
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
    private Boss boss;
    public Bomberman bomber;
    private int indexBomberman;
    private static int livello = 1;
    BufferedReader br;
    static File path;
    public int vitt;



    Boolean personalizzato;
    Boolean hitboxSpecial;

    /***
     * costruttore che inizializza il TileManager
     * @param partita la partita in cui si sta giocando
     * @param keyH il keyhandler che gestisce gli input da tastiera
     *
     * inizializza il TileManager, crea le tile, carica la mappa e le entità
     */
    public TileManager(Partita partita, KeyHandler keyH){
        personalizzato = false;
        pulisci();
        path = new File("src/FileLivelli/livello" + livello + ".txt");
        this.partita = partita;
        tile = new Tile[10]; //rappresenta il numero di tile diverse che abbiamo a disposizione
        mapTileNum = new int[partita.maxScreenCol][partita.maxScreenRow];
        this.keyH = keyH;


        getTileImage();
        loadMap();
    }

    /***
     * costruttore che inizializza il TileManager con livelli custom
     * @param partita la partita in cui si sta giocando
     * @param keyH il keyhandler che gestisce gli input da tastiera
     * @param percorso il percorso del file da cui caricare la mappa
     */
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

    /***
     * metodo che pulisce il TileManager
     * prepara il TileManager per un nuovo livello
     */
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
        //AudioManager.getInstance().stop();

    }

    /***
     * metodo per settare il livello della partita
     * @param liv il livello da settare
     */
    public static void setLivello(int liv){
        livello = liv;
        path = new File("res/livelli/livello" + liv+ ".txt");
    }


    /**
     * Metodo che carica le immagini delle tiles
     */
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

    /**
     * Metodo che carica la mappa
     */
    public void loadMap(){

        try{



                FileReader fr = new FileReader(path.getAbsolutePath());
                br = new BufferedReader(fr);



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
                            boss = new Boss1(col*64, row*64,tile[6].immagine, 2, 10, partita);
                            this.hitboxSpecial = true;
                            break;
                        case 15:
                            boss = new Boss2(col*64, row*64,tile[6].immagine, 2, 10, partita);
                            this.hitboxSpecial = false;
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

    /**
     * Metodo che ritorna l'array delle entitá stazionarie presenti
     * @return Entitá stazionarie
     */
    public static ArrayList<StationaryEntity> getStationaryEntities(){
        return stationaryEntities;
    }

    /**
     * Metodo che disegna il livello di gioco aggiornato con le informazioni aggiornate
     * @param g2 il contesto grafico
     */
    public void draw(Graphics2D g2){

        //uso stream per disegnare le tile
        tiles.forEach((tile) -> tile.disegna(g2));


        stationaryEntities.forEach((entity) -> {entity.disegna(g2);
            entity.update();
        });

        

        BombManager.bombe.forEach((bomba) -> {
            bomba.disegna(g2);
            bomba.update();
        });

        if(boss != null){
            if(!boss.isDead()){
                boss.disegna(g2);
                boss.update();
            }
        }
        codaAggiunte.forEach((e) -> {
            stationaryEntities.forEach((entity) -> {
                if(entity instanceof Muro){
                    if(e.getHitbox().intersects(entity.getHitbox()) && !entity.isDistruttibile()){
                        codaRimozioni.add(e);
                    }

                }

            });
        });

        codaRimozioni.forEach(codaAggiunte::remove);
        codaRimozioni.clear();
        BombManager.esplosioni.addAll(codaAggiunte);
        codaAggiunte.clear();


        BombManager.esplosioni.forEach((esplosione) -> {
            esplosione.disegna(g2);
            esplosione.update();
        });

        movingEntities.forEach((entity)->{
            if(entity.vite <= -1){
                addEntityR(entity);
            }
            entity.update();
            entity.disegna(g2);
        });

        powerUps.forEach((powerUp)->{
           powerUp.disegna(g2);
           powerUp.update();
        });

        stationaryEntitiesR.forEach((entity)->{
            removeEntity(entity);
            bomber.setScore(10);
        });
        stationaryEntitiesR.clear();
        movingEntitiesR.forEach((entity)->{
            removeEntity(entity);
            bomber.setScore(50);
        });
        movingEntitiesR.clear();
        powerUpsR.forEach(TileManager::removeEntity);
        BombManager.bombeR.forEach(BombManager::removeBomba);
        BombManager.esplosioniR.forEach(BombManager::removeEsplosione);

        BombManager.bombeR.clear();
        BombManager.esplosioniR.clear();
        powerUpsR.clear();
        bomber.update();
        bomber.disegna(g2);
        checkCollision();
        if(boss != null){
            if(boss.isDead()){


                if(!personalizzato){

                    livello++;
                    path = new File("src/FileLivelli/livello" + livello + ".txt");

                }
                AudioManager.getInstance().stop(2);
                Partita.stopGameThread();
                SchermataVittoria.getIstanza(personalizzato).setVisible(true);

            }
        } else if (movingEntities.size() == 0){
            Partita.stopGameThread();
            AudioManager.getInstance().stop(2);


            if(!personalizzato) {

                livello++;
                path = new File("src/FileLivelli/livello" + livello + ".txt");
            }


            if(livello > 8){

                SchermataCompletamento.getIstanza().setVisible(true);
                resetLivello();
            }
            else {

                SchermataVittoria.getIstanza(personalizzato).setVisible(true);}

        }

    }

    /**
     * Resetta il livello di gioco al primo livello
     */
    public static void resetLivello(){
        livello = 1;
    }


    /**
     * Metodo che controlla le collisioni tra entitá
     */
    public void checkCollision(){

        stationaryEntities.forEach((entity)->{
            if(bomber.getHitbox().intersects(entity.getHitbox())){
                bomber.handleCollision(entity);
            }
        });

        BombManager.bombe.forEach((bomba)->{
            if(bomber.getHitbox().intersects(bomba.getHitbox())){
                bomber.handleCollision(bomba);
            }
        });

        movingEntities.forEach((mentity)->{
            stationaryEntities.forEach((entity)->{
                if(mentity.getHitbox().intersects(entity.getHitbox())){
                    mentity.handleCollision(entity);
                }
            });
            BombManager.bombe.forEach((bomba)->{
                if(mentity.getHitbox().intersects(bomba.getHitbox())){
                    mentity.handleCollision(bomba);
                }
            });
            if(bomber.getHitbox().intersects(mentity.getHitbox())){
                bomber.handleCollision(mentity);
            }
        });

        BombManager.esplosioni.forEach((e)->{
            if(bomber.getHitbox().intersects(e.getHitbox())){
                bomber.handleCollision(e);
            }
            stationaryEntities.forEach((entity)->{
                if(e.getHitbox().intersects(entity.getHitbox())){
                    entity.handleCollision(e);
                }
            });
            movingEntities.forEach((mentity)->{
                if(e.getHitbox().intersects(mentity.getHitbox())){
                    mentity.handleCollision(e);
                }
            });
            if(boss != null){
                if(!boss.isDead()) {
                    if(hitboxSpecial){
                        if (boss.getHitboxSpecial().intersects(e.getHitbox())) {
                            boss.handleCollision(e);
                        }
                    }
                    else if (boss.getHitbox().intersects(e.getHitbox())) {
                        boss.handleCollision(e);
                    }
                }
            }
        });
        powerUps.forEach((powerUp)->{
            if(bomber.getHitbox().intersects(powerUp.getHitbox())){
                bomber.handleCollision(powerUp);
            }
        });
        if(boss != null){
            if(!boss.isDead()) {

                if(hitboxSpecial){
                    if (boss.getHitboxSpecial().intersects(bomber.getHitbox())){
                        bomber.handleCollision(boss);
                    }
                }
                else{
                    if (bomber.getHitbox().intersects(boss.getHitbox())) {
                        bomber.handleCollision(boss);
                    }
                }
            }
        }
    }

    /**
     * Metodo che rimuove un'entitá dal gioco
     * @param entity entitá da rimuovere
     */
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

    /**
     * Metodo che aggiunge un'entitá al gioco
     * @param entity entitá da aggiungere
     */
    public static void addEntity(GameEntity entity) {
        if (entity instanceof MovingEntity) {
            movingEntities.add((MovingEntity) entity);
        } else if (entity instanceof PowerUp) {
            powerUps.add((PowerUp) entity);
        } else if (entity instanceof StationaryEntity) {
            stationaryEntities.add((StationaryEntity) entity);
        }}

    /**
     * Aggiunge un'entitá alla lista di entitá da aggiungere
     * @param entity entitá da aggiungere
     */
    public static void addEntityR (GameEntity entity){

        if (entity instanceof MovingEntity) {
            movingEntitiesR.add((MovingEntity) entity);
        } else if (entity instanceof PowerUp) {
            powerUpsR.add((PowerUp) entity);
        } else if (entity instanceof StationaryEntity) {
            stationaryEntitiesR.add((StationaryEntity) entity);

        }
    }

    /**
     * Aggiunge un'esplosione alla coda di esplosioni da aggiungere
     * @param e esplosione da aggiungere
     */
    public static void AggiungiACoda(Esplosione e){
        codaAggiunte.add(e);
    }


    public void setVitt(int i){
        vitt = i;
    }
}