package Taruffi.Grafica;

import Taruffi.Disegnabile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

//classe che rappresenta una casella di gioco disegnabile
public class Tile implements Disegnabile {

    public BufferedImage immagine;
    public boolean collision = false; //non implementa collidable quindi probably superfluo



    @Override
    public void disegna(Graphics2D g2) {

    }
}
