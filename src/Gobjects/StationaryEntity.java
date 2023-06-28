package Gobjects;

import Porfiri.Esplosione;
import Taruffi.Grafica.Partita;
import Taruffi.Grafica.TileManager;
import Tomassetti.Collidable;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class StationaryEntity extends GameEntity implements Collidable {
    protected boolean isDistrutto;
    protected boolean isDistruttibile;
    protected Partita play;


    public StationaryEntity(int x, int y, BufferedImage image, Partita play){
        super(x,y,image);
        this.isDistrutto = false;
        this.play = play;
    }

    public boolean checkEsplosione(){
        return this.isDistrutto; //rimane così per adesso, una volta che ci sarà la classe esplosione si modificherà
    }
    public void setDistrutto(){
        this.isDistrutto = true;
    }

    public abstract void disegna(Graphics2D g2d);

    @Override
    public void handleCollision(Esplosione e) {
        if(this.isDistruttibile){
            this.setDistrutto();
        }
    }

    @Override
    public void update() {
        if(checkEsplosione()){
            TileManager.removeEntity(this);
            TileManager.addEntity(new PowerUp(this.x,this.y,play));
        }
    }
}
