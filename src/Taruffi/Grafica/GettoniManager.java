package Taruffi.Grafica;

import Gobjects.Bomberman;

import java.awt.*;

public class GettoniManager {

    private int x, y;
    public GettoniManager(){
        this.x = 600;
        this.y = 870;
    }

    public void disegna(Graphics2D g){
        Gettoni.disegna(g, Bomberman.getGettoni(), x, y);
    }
}
