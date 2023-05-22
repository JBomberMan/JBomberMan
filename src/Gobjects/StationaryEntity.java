package Gobjects;

import Porfiri.Esplosione;

import java.awt.image.BufferedImage;

public abstract class StationaryEntity extends GameEntity{
    protected boolean isDistrutto;
    protected boolean isDistruttibile;



    public StationaryEntity(int x, int y, BufferedImage image){
        super(x,y,image);
        this.isDistrutto = false;
    }
    public boolean checkEsplosione(){
        return this.isDistrutto && false; //rimane così per adesso, una volta che ci sarà la classe esplosione si modificherà
    }
    public void setDistrutto(){
        this.isDistrutto = true;
    }
}
