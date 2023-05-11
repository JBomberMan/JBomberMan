package Taruffi.Grafica;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

public class TileMaganer {

    Partita partita; //equivale al suo gamepanel
    Tile[] tile;
    int mapTileNum[][];

    public TileMaganer(Partita partita){
        this.partita = partita;
        tile = new Tile[10]; //rappresenta il numero di tile diverse che abbiamo a disposizione
        mapTileNum = new int[partita.maxScreenCol][partita.maxScreenRow];
        getTileImage();
        loadMap();

    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/erbaprova.png"));
            tile[1] = new Tile();
            tile[1].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/mattoneprova.png"));
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

            while(col < partita.maxScreenCol && row < partita.maxScreenRow){
                String line = br.readLine();
                while(col < partita.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col ++;
                }
                if(col == partita.maxScreenCol){
                    col = 0;
                    row ++;
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

        while(col < partita.maxScreenCol && row < partita.maxScreenRow){

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].immagine, x, y, partita.tileSize, partita.tileSize, null);
            col ++;
            x += partita.tileSize;

            if(col == partita.maxScreenCol){
                col = 0;
                row ++;
                x = 0;
                y += partita.tileSize;
            }
        }

    }


}
