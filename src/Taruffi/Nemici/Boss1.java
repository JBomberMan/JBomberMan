package Taruffi.Nemici;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Gobjects.Bomba;
import Gobjects.GameEntity;
import Gobjects.MovingEntity;
import Gobjects.PowerUp;
import Gobjects.StationaryEntity;
import Porfiri.Esplosione;
import Taruffi.Grafica.Partita;
import Taruffi.Grafica.SchermataSconfitta;
import Taruffi.Grafica.TileManager;
import Tomassetti.Collidable;

public class Boss1 extends MovingEntity implements Collidable{

    private static int velocita;
    public BufferedImage standard, hit;

    public static String direction = "left";
    private int spriteCounter = 0;
    private int invTimer = 0;
    public Boolean dead = false;
    private Boolean collision = false;
    private Polygon hitboxPorcata;




    public Boss1(int x, int y, BufferedImage image, int velocita, int vite, Partita play) {
        super(x, y, image, velocita, vite, play);
        this.velocita = 2;
        this.hitboxPorcata = new Polygon(new int[]{this.x + 166,
                this.x + 246, this.x + 294, this.x + 332, this.x + 412, this.x + 412, this.x + 363,
                this.x + 332, this.x + 304, this.x + 206, this.x + 108, this.x + 80, this.x + 49,
                this.x + 0, this.x + 0, this.x + 80, this.x + 118}, new int[]{this.y + 0,
                this.y + 0, this.y + 27, this.y + 117, this.y + 172, this.y + 241, this.y + 278,
                this.y + 254, this.y + 304, this.y + 348, this.y + 304, this.y + 254, this.y + 278,
                this.y + 241, this.y + 172, this.y + 117, this.y + 27
        }, 17);
        getEnemiesImage();


    }

    public void getEnemiesImage() {
        try {
            standard = ImageIO.read(getClass().getResourceAsStream("/Images/clown/clown1.png"));
            hit = ImageIO.read(getClass().getResourceAsStream("/Images/clown/clownHit.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        //this.hitbox.setBounds(x, y+130, standard.getWidth(), standard.getHeight()-200);
        this.hitboxPorcata = new Polygon(new int[]{this.x + 166,
                this.x + 246, this.x + 294, this.x + 332, this.x + 412, this.x + 412, this.x + 363,
                this.x + 332, this.x + 304, this.x + 206, this.x + 108, this.x + 80, this.x + 49,
                this.x + 0, this.x + 0, this.x + 80, this.x + 118}, new int[]{this.y + 0,
                this.y + 0, this.y + 27, this.y + 117, this.y + 172, this.y + 241, this.y + 278,
                this.y + 254, this.y + 304, this.y + 348, this.y + 304, this.y + 254, this.y + 278,
                this.y + 241, this.y + 172, this.y + 117, this.y + 27
        }, 17);
        // 168,0
        collision = false;
        muovi();

    }

    @Override
    public void muovi() {
        switch(direction){
            case "left":
                x -= velocita;
                break;
            case "right":
                x += velocita;
                break;
        }
    }

    @Override
    public void disegna(Graphics2D g2d) {
        if(invTimer > 0){
            g2d.drawImage(hit, x, y, null);
            invTimer--;
        } else {
            g2d.drawImage(standard, x, y, null);
        }
        g2d.draw(new Polygon(new int[]{this.x + 166,
                this.x + 246, this.x + 294, this.x + 332, this.x + 412, this.x + 412, this.x + 363,
                this.x + 332, this.x + 304, this.x + 206, this.x + 108, this.x + 80, this.x + 49,
                this.x + 0, this.x + 0, this.x + 80, this.x + 118}, new int[]{this.y + 0,
                this.y + 0, this.y + 27, this.y + 117, this.y + 172, this.y + 241, this.y + 278,
                this.y + 254, this.y + 304, this.y + 348, this.y + 304, this.y + 254, this.y + 278,
                this.y + 241, this.y + 172, this.y + 117, this.y + 27
        }, 17));

        //g2d.fillRect(x, y+130, standard.getWidth(), standard.getHeight()-200); //orecchie
        //g2d.fillRect(x+100, y, standard.getWidth()-200, standard.getHeight()); //testa

    }

    public void handleCollision(StationaryEntity se){
        if(! collision){
            collision = true;
            this.solidCollision(se);
            if(direction.equals("left")) direction = "right";
            else direction = "left";
        }
    }

    public void handleCollision(Esplosione e){
        if(this.invTimer == 0){
            this.vite--;
            this.invTimer = 40;
            System.out.println("Vite rimaste Nemico: " + this.vite);

            if (this.vite <= 0){
                dead = true;
                System.out.println("Nemico Sconfitto!");
            }
        }
    }

    void solidCollision(GameEntity obj) {
        Rectangle2D intersection = this.hitbox.createIntersection(obj.hitbox);


        // Vertical collision
        if (intersection.getWidth() >= intersection.getHeight()) {
            // From the top
            if (intersection.getMaxY() >= this.hitbox.getMaxY()) {
                this.y -= 5;
            }
            // From the bottom
            if (intersection.getMaxY() >= obj.hitbox.getMaxY()) {

                this.y += 5;
            }

            // Smoothing around corners
            if (intersection.getWidth() < 16) {
                if (intersection.getMaxX() >= this.hitbox.getMaxX()) {

                    this.x -= 0.5;
                }
                if (intersection.getMaxX() >= obj.hitbox.getMaxX()) {

                    this.x += 0.5;
                }
            }
        }

        // Horizontal collision
        if (intersection.getHeight() >= intersection.getWidth()) {
            // From the left
            if (intersection.getMaxX() >= this.hitbox.getMaxX()) {

                this.x -= 5;
            }
            // From the right
            if (intersection.getMaxX() >= obj.hitbox.getMaxX()) {

                this.x += 5;
            }

            // Smoothing around corners
            if (intersection.getHeight() < 16) {
                if (intersection.getMaxY() >= this.hitbox.getMaxY()) {

                    this.y -= 0.5;
                }
                if (intersection.getMaxY() >= obj.hitbox.getMaxY()) {
                    this.y += 0.5;
                }
            }
        }
    }


    public Polygon getHitboxPorcata() {
        return this.hitboxPorcata;
    }
}
