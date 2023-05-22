package Tomassetti;

import Gobjects.GameEntity;

import javax.swing.*;

public class CollisionChecker {
    JPanel gamePanel;
    public CollisionChecker(JPanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void checkTileCollision(GameEntity gameObject){
        //TODO check if
        int entityLeft = gameObject.getX();
        int entityRight = gameObject.getX() + gameObject.getImage().getWidth();
        int entityTop = gameObject.getY();
        int entityBottom = gameObject.getY() + gameObject.getImage().getHeight();

        int entityLeftColumn = entityLeft / 32;
        int entityRightColumn = entityRight / 32;
        int entityTopRow = entityTop / 32;
        int entityBottomRow = entityBottom / 32;
    }
}
