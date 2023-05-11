package Tomassetti;

import javax.swing.*;

public class CollisionChecker {
    JPanel gamePanel;
    public CollisionChecker(JPanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void checkTileCollision(GameEntity gameObject){
        //TODO check if
        int entityLeft = gameObject.x;
        int entityRight = gameObject.x + gameObject.image.getWidth();
        int entityTop = gameObject.y;
        int entityBottom = gameObject.y + gameObject.image.getHeight();

        int entityLeftColumn = entityLeft / 32;
        int entityRightColumn = entityRight / 32;
        int entityTopRow = entityTop / 32;
        int entityBottomRow = entityBottom / 32;
    }
}
