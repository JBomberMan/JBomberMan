package Model;

import View.Vite;

import java.awt.*;

public class ViteManager {

    private int x, y;
    public ViteManager(){
        this.x = 200;
        this.y = 870;
    }

    public void disegna(Graphics2D g){
        Vite.disegna(g, Bomberman.getVite(), x, y);
    }
}
