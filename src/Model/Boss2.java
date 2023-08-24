package Model;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Boss2 extends Boss implements Collidable{

    private static int velocita;
    public BufferedImage standard, hit, cry1, cry2, cry3;
    public BufferedImage[] risorse = new BufferedImage[15];
    private int timerAttacco;

    private int timerAttaccoNormal =120;

    private  int timerAttacco2Rage = 75;

    public static String direction = "left";
    private int spriteCounter = 0;
    private int invTimer = 0, incazzatoTimer = 0, invSprite = 0;
    public Boolean dead = false;
    private Boolean collision = false, incazzato = false, attaccando = false;

    BufferedImage sprite = standard;


    public Boss2(int x, int y, BufferedImage image, int velocita, int vite, Partita play) {
        super(x, y, image, velocita, vite, play);
        this.velocita = 1;
        this.vite = 3;
        getEnemiesImage();
        this.hitbox = new Rectangle(x, y, standard.getWidth(), standard.getHeight());
        this.timerAttacco = timerAttaccoNormal;
    }

    public void getEnemiesImage() {
        try {
            risorse[0] = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/normaleLLL.png")); //standard
            risorse[1] = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/colpitoLLL.png")); //hit
            risorse[2] = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/attacco1LLL.png")); //colpo1
            risorse[3] = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/attacco2LLL.png")); //colpo2
            risorse[4] = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/attacco3LLL.png")); //colpo3
            risorse[5] = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/normaleLLLL.png")); //standard
            risorse[6] = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/colpitoLLLL.png")); //hit
            risorse[7] = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/attacco1LLLL.png")); //colpo1
            risorse[8] = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/attacco2LLLL.png")); //colpo2
            risorse[9] = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/attacco3LLLL.png")); //colpo3
            risorse[10] = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/normaleLLLLL.png")); //standard
            risorse[11] = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/colpitoLLLLL.png")); //hit
            risorse[12] = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/attacco1LLLLL.png")); //colpo1
            risorse[13] = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/attacco2LLLLL.png")); //colpo2
            risorse[14] = ImageIO.read(getClass().getResourceAsStream("/Images/Ciccio/attacco3LLLLL.png")); //colpo3

        } catch(IOException e){
            e.printStackTrace();
        }
        standard = risorse[0];
        hit = risorse[1];
        cry1 = risorse[2];
        cry2 = risorse[3];
        cry3 = risorse[4];

    }

    @Override
    public void update() {
        this.hitbox.setBounds(x, y, sprite.getWidth(), sprite.getHeight());
        if(vite == 2){
            standard = risorse[5];
            hit = risorse[6];
            cry1 = risorse[7];
            cry2 = risorse[8];
            cry3 = risorse[9];
        }
        if(vite == 1){
            standard = risorse[10];
            hit = risorse[11];
            cry1 = risorse[12];
            cry2 = risorse[13];
            cry3 = risorse[14];
        }
        // 168,0
        collision = false;
        muovi();

    }

    @Override
    public void muovi() {

            if(this.x + this.sprite.getWidth()/2 < Bomberman.getX()){
                x += velocita;
            }else{
                x -= velocita;
            }
            if(this.y + this.sprite.getHeight()/2 < Bomberman.getY()){
                y += velocita;
            }else{
                y -= velocita;
            }

        if (this.timerAttacco == 0) {
            this.timerAttacco = incazzato ? timerAttacco2Rage : timerAttaccoNormal;
            this.Attacco();
        }
        else{
            this.timerAttacco--;
        }
    }

    @Override
    public void disegna(Graphics2D g2d) {


        if(invTimer > 0){
            invTimer--;
            //sprite = hit;
        }
        if(invSprite > 0){
            invSprite--;
            sprite = hit;
        }
        else if(attaccando){
            if(timerAttacco > 60){
                sprite = cry1;
                timerAttacco--;
            } else if(timerAttacco > 30){
                sprite = cry2;
                timerAttacco--;
            } else if(timerAttacco > 0){
                sprite = cry3;
                timerAttacco--;
            }

            else if(timerAttacco <= 0){
                incazzato = false;
                incazzatoTimer = 0;
            }
        }else {
            sprite = standard;
        }
        g2d.drawImage(sprite, x, y, null);
        g2d.draw(hitbox);

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
            if(this.vite == 4){
                velocita = 1;
            }
            this.invTimer = 60;
            this.invSprite = 60;
            System.out.println("Vite rimaste Nemico: " + this.vite);

            if (this.vite <= 0){
                dead = true;
                TileManager.removeEntity(this);
                System.out.println("Nemico Sconfitto!");
            }
            incazzati();

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


    private void incazzati(){
        incazzato = true;
    }
    private void Attacco(){
        attaccando = true;
    }

    @Override
    public boolean isDead() {
        return this.dead;
    }

    public Polygon getHitboxPorcata(){return new Polygon();}
}
