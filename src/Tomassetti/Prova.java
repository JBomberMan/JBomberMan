package Tomassetti;
import Taruffi.Grafica.Tile;
import Taruffi.Grafica.TileManager;

import static java.lang.Thread.sleep;

public class Prova {
    TileManager tileMaganer;

    public static void main(String[] args) {
        TileManager tileMaganer = new TileManager();
        for(Tile tile : tileMaganer.tile){
            System.out.println(tile.immagine);
        }
    }
}
