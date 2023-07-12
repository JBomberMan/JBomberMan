package Taruffi.Grafica;

import Gobjects.Bomberman;

import java.awt.*;

public class ViteManager {

    private int x, y;
    public ViteManager(){
        this.x = 400;
        this.y = 870;
    }

    public void disegna(Graphics2D g){
        Vite.disegna(g, Bomberman.getVite(), x, y);
    }
}
