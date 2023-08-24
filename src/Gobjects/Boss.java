package Gobjects;

import Taruffi.Grafica.Partita;
import Tomassetti.Collidable;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Boss extends MovingEntity implements Collidable {
    private static int velocita;

    public BufferedImage[] risorse = new BufferedImage[15];
    private int timerAttacco;

    private int timerAttaccoNormal;

    private  int timerAttacco2Rage;

    public static String direction = "left";
    private int spriteCounter = 0;
    private int invTimer = 0, incazzatoTimer = 0, invSprite = 0;
    public Boolean dead = false;
    private Boolean collision = false, incazzato = false, attaccando = false;




    public Boss(int x, int y, BufferedImage image, int velocita, int vite, Partita play) {
        super(x, y, image, velocita, vite, play);}



    public abstract boolean isDead();
    public abstract Polygon getHitboxPorcata();

}
