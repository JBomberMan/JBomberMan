package Model;
import View.ELvlPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;


public class ETileManager {

    ELvlPanel eLvlPanel;
    private static Boolean bomberman = false;
    private static int bombx, bomby;
    public static Tile[] tile;
    static int[][] mapTileNum;
    public static TileObject[][] tiles;

    public ETileManager(ELvlPanel eLvlPanel) {
        this.eLvlPanel = eLvlPanel;
        tile = new Tile[16];
        mapTileNum = new int[17][13];
        tiles = new TileObject[17][13];
        getTileImage();
        loadMap();
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
            tile[7].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/IconEditorLivelli/grass_bomberman.png"));
            tile[9] = new Tile();
            tile[9].immagine = ImageIO.read((getClass().getResourceAsStream("/Images/IconEditorLivelli/grass_oneal.png")));
            tile[10] = new Tile();
            tile[10].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/IconEditorLivelli/grass_baloon.png"));
            tile[11] = new Tile();
            tile[11].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/IconEditorLivelli/grass_kondoria.png"));
            tile[12] = new Tile();
            tile[12].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/IconEditorLivelli/grass_doll.png"));
            tile[13] = new Tile();
            tile[13].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/IconEditorLivelli/grass_ovapi.png"));
            tile[14] = new Tile();
            tile[14].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/clown/clown1.png"));
            tile[15] = new Tile();
            tile[15].immagine = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/normale.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/FileLivelli/livelloTemplate.txt");
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < 17 && row < 13){
                String line = br.readLine();
                while (col < 17) {

                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;

                    switch (num) {
                        case 0:
                            tiles[col][row] = new TileObject(col * 32, row * 32, tile[0].immagine);
                            break;
                        case 1:
                            tiles[col][row]= new TileObject(col*32, row*32, tile[1].immagine);
                            break;
                        case 2:
                            tiles[col][row] = new TileObject(col*32, row*32, tile[2].immagine);
                            break;
                        case 3:
                            tiles[col][row] = new TileObject(col*32, row*32, tile[3].immagine);
                            break;
                        case 4:
                            tiles[col][row] = new TileObject(col*32, row*32, tile[4].immagine);
                            break;
                        case 5:
                            tiles[col][row] = new TileObject(col*32, row*32, tile[5].immagine);
                            break;
                        case 6:
                            tiles[col][row] = new TileObject(col*32, row*32, tile[6].immagine);
                            break;
                        case 7:
                            tiles[col][row] = new TileObject(col*32, row*32, tile[7].immagine);
                            break;
                        case 9:
                            tiles[col][row] = new TileObject(col*32, row*32, tile[9].immagine);
                            break;
                        case 10:
                            tiles[col][row] = new TileObject(col*32, row*32, tile[10].immagine);
                            break;
                        case 11:
                            tiles[col][row] = new TileObject(col*32, row*32, tile[11].immagine);
                            break;
                        case 12:
                            tiles[col][row] = new TileObject(col*32, row*32, tile[12].immagine);
                            break;
                        case 13:
                            tiles[col][row] = new TileObject(col*32, row*32, tile[13].immagine);
                            break;
                        case 14:
                            tiles[col][row] = new TileObject(col*32, row*32, tile[14].immagine);
                            break;
                        case 15:
                            tiles[col][row] = new TileObject(col*32, row*32, tile[15].immagine);
                            break;
                    }
                    col++;
                }
                if (col == 17) {
                    col = 0;
                    row++;
                }
            }

            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void modificaVista(int colonna, int riga, int numero){

        if(colonna == 0 || colonna == 16 || riga == 0 || riga == 12 || (numero == 7 && bomberman)) {
            return;
        }
        else{
            tiles[colonna][riga] = new TileObject(colonna*32,riga*32, tile[numero].immagine);
            if(numero == 7){
                bomberman = true;
                bombx = colonna;
                bomby = riga;
            }
            if(!tiles[bombx][bomby].getImage().equals(tile[7].immagine)){
                bomberman = false;

            }

        }


    }
    public void disegna(Graphics2D g2d){
        for(int i =0; i< 17; i++){
            for(int j = 0; j<13  ; j ++){
                tiles[i][j].disegna(g2d);
            }
        }
    }

    public static void salvaLivello(){

        try{
            if(bomberman == false) {return;}
            String fileName = JOptionPane.showInputDialog("Inserisci nome filge");
            String path = "src/LivelliPersonalizzati";
            File outputfile = new File(path, fileName + ".txt");

            PrintWriter pw = new PrintWriter(new FileWriter(outputfile));
            for(int i = 0; i<13; i++){
                for(int j = 0; j<17; j++){
                    if(tiles[j][i].getImage().equals(tile[0].immagine) ){
                        pw.print("0" + " ");
                    }
                    if(tiles[j][i].getImage().equals(tile[1].immagine) ){
                        pw.print("1" + " ");
                    }
                    if(tiles[j][i].getImage().equals(tile[2].immagine) ){
                        pw.print("2" + " ");
                    }
                    if(tiles[j][i].getImage().equals(tile[3].immagine) ){
                        pw.print("3" + " ");
                    }
                    if(tiles[j][i].getImage().equals(tile[4].immagine) ){
                        pw.print("4" + " ");
                    }
                    if(tiles[j][i].getImage().equals(tile[5].immagine) ){
                        pw.print("5" + " ");
                    }
                    if(tiles[j][i].getImage().equals(tile[6].immagine) ){
                        pw.print("6" + " ");
                    }
                    if(tiles[j][i].getImage().equals(tile[7].immagine) ){
                        pw.print("7" + " ");
                    }
                    if(tiles[j][i].getImage().equals(tile[9].immagine) ){
                        pw.print("9" + " ");
                    }
                    if(tiles[j][i].getImage().equals(tile[10].immagine) ){
                        pw.print("10" + " ");
                    }
                    if(tiles[j][i].getImage().equals(tile[11].immagine) ){
                        pw.print("11" + " ");
                    }
                    if(tiles[j][i].getImage().equals(tile[12].immagine) ){
                        pw.print("12" + " ");
                    }
                    if(tiles[j][i].getImage().equals(tile[13].immagine) ){
                        pw.print("13" + " ");
                    }
                    if(tiles[j][i].getImage().equals(tile[14].immagine) ){
                        pw.print("14" + " ");
                    }
                    if(tiles[j][i].getImage().equals(tile[15].immagine) ){
                        pw.print("15" + " ");
                    }
                }
                pw.println();
            }
            pw.print("8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8");
            pw.close();
            

        }catch(Exception e){
            e.printStackTrace();
        }



    }
}
