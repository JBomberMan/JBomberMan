package Model;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Muro extends StationaryEntity {
    public Muro(int x, int y, BufferedImage image, boolean isDistruttibile, Partita play){
        super(x,y,image, play);
        this.isDistruttibile = isDistruttibile;
    }

    public void disegna(Graphics2D g2d){
        g2d.drawImage(image, x, y,play.tileSize, play.tileSize, null);
    }

    public void setDistrutto(){
        this.isDistrutto = true;
    }
    @Override
    public void handleCollision(Esplosione e) {
        if(this.isDistruttibile){
            this.setDistrutto();

        }
    }

    @Override
    public void update() {
        if(this.isDistrutto){
            double random = Math.random();
            if(random < 0.3){
                TileManager.addEntity(new PowerUp(this.x,this.y,play));
            }
            TileManager.addEntityR(this);
        }
    }
}
