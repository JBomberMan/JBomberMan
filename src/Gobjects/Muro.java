package Gobjects;

import java.awt.image.BufferedImage;

public class Muro extends StationaryEntity{
    public Muro(int x, int y, BufferedImage image, boolean isDistruttibile){
        super(x,y,image);
        this.isDistruttibile = isDistruttibile;
    }

    public void update(){
        if(this.checkEsplosione()){
            this.setDistrutto();
            this.afterDistruzione();
        }
    }

    public void afterDistruzione(){
        float random = (float) Math.random();
        if(random < 0.5){
            PowerUp powerUp = new PowerUp(this.x,this.y);
        }
    }
}
