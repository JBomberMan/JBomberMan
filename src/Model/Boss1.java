package Model;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;

/**
 * classe che modella il boss clown
 */
public class Boss1 extends Boss implements Collidable{

    private static int velocita;
    public BufferedImage standard, hit, cry1, cry2, cry3;

    public static String direction = "left";
    private int invTimer = 0, rageTimer = 0, invSprite = 0;

    public Boolean dead = false;
    private Boolean collision = false, inRage = false;
    private Polygon hitboxSpecial;


    /**
     * costruttore
     * @param x coordinate x
     * @param y coordinate y
     * @param image immagione iniziale
     * @param velocita velocità
     * @param vite vite
     * @param play riferimento alla partita
     */
    public Boss1(int x, int y, BufferedImage image, int velocita, int vite, Partita play) {
        super(x, y, image, velocita, vite, play);
        this.velocita = 1;
        this.vite = 10;
        this.hitboxSpecial = new Polygon(new int[]{this.x + 166,
                this.x + 246, this.x + 294, this.x + 332, this.x + 412, this.x + 412, this.x + 363,
                this.x + 332, this.x + 304, this.x + 206, this.x + 108, this.x + 80, this.x + 49,
                this.x + 0, this.x + 0, this.x + 80, this.x + 118}, new int[]{this.y + 0,
                this.y + 0, this.y + 27, this.y + 117, this.y + 172, this.y + 241, this.y + 278,
                this.y + 254, this.y + 304, this.y + 348, this.y + 304, this.y + 254, this.y + 278,
                this.y + 241, this.y + 172, this.y + 117, this.y + 27
        }, 17);
        this.getEnemiesImage();


    }

    /**
     * metodo per ottenere l'immagine del clown
     */
    public void getEnemiesImage() {
        try {
            standard = ImageIO.read(getClass().getResourceAsStream("/Images/clown/clown1.png"));
            hit = ImageIO.read(getClass().getResourceAsStream("/Images/clown/clownHit.png"));
            cry1 = ImageIO.read(getClass().getResourceAsStream("/Images/clown/cry1.png"));
            cry2 = ImageIO.read(getClass().getResourceAsStream("/Images/clown/cry2.png"));
            cry3 = ImageIO.read(getClass().getResourceAsStream("/Images/clown/cry3.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * metodo per aggiornare il clown
     */
    @Override
    public void update() {
        this.hitboxSpecial = new Polygon(new int[]{this.x + 166,
                this.x + 246, this.x + 294, this.x + 332, this.x + 412, this.x + 412, this.x + 363,
                this.x + 332, this.x + 304, this.x + 206, this.x + 108, this.x + 80, this.x + 49,
                this.x + 0, this.x + 0, this.x + 80, this.x + 118}, new int[]{this.y + 0,
                this.y + 0, this.y + 27, this.y + 117, this.y + 172, this.y + 241, this.y + 278,
                this.y + 254, this.y + 304, this.y + 348, this.y + 304, this.y + 254, this.y + 278,
                this.y + 241, this.y + 172, this.y + 117, this.y + 27
        }, 17);
        collision = false;
        muovi();

    }

    /**
     * metodo per far muovere il boss
     */
    @Override
    public void muovi() {
        if(!inRage){
            if(this.x + this.standard.getWidth()/2 < Bomberman.getX()){
                x += velocita;
            }else{
                x -= velocita;
            }
            if(this.y + this.standard.getHeight()/2 < Bomberman.getY()){
                y += velocita;
            }else{
                y -= velocita;
            }
        }
    }

    /**
     * metodo per disegnare il boss
     * @param g2d il contesto grafico
     */
    @Override
    public void disegna(Graphics2D g2d) {
        BufferedImage sprite = null;

        if(invTimer > 0){
            invTimer--;
        }
        if(invSprite > 0){
            invSprite--;
            sprite = hit;
        }
        else if(inRage){
            if(rageTimer < 40){
                sprite = cry1;
                rageTimer++;
            } else if(rageTimer < 80){
                sprite = cry2;
                rageTimer++;
            } else if(rageTimer < 120){
                sprite = cry3;
                rageTimer++;
            }
            else if(rageTimer < 160){
                sprite = cry2;
                rageTimer++;
            }
            else {
                inRage = false;
                rageTimer = 0;
            }
        }else {
            sprite = standard;
        }
        g2d.drawImage(sprite, x, y, null);

    }

    /**
     * metodo per gestire le collisioni con una entitá stazionaria
     * @param se entitá stazionaria
     */
    public void handleCollision(StationaryEntity se){
        if(! collision){
            collision = true;
            this.solidCollision(se);
            if(direction.equals("left")) direction = "right";
            else direction = "left";
        }
    }

    /**
     * metodo per gestire le collisioni con le esplosioni
     * @param e esplosione
     */
    public void handleCollision(Esplosione e){
        if(this.invTimer == 0 && this.rageTimer == 0){
            this.vite--;
            if(this.vite == 4){
                velocita = 2;
            }
            this.invTimer = 160;
            this.invSprite = 40;


            if (this.vite <= 0){
                dead = true;
                TileManager.removeEntity(this);

            }
            rage();

        }

    }


    /**
     * Metodo che implementa la collisione solida tra due entità
     * @param obj entità con cui si vuole controllare la collisione
     */
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


    /**
     * metodo per ottenere la hitbox del boss
     * @return hitbox del boss
     */
    public Polygon getHitboxSpecial() {
        return this.hitboxSpecial;
    }


    /**
     * metodo per attivare la rage mode del boss
     */
    private void rage(){
        inRage = true;
        int xp = this.x + (standard.getWidth()/2) - 30;
        int yp = this.y + (standard.getHeight()/2) -30;
        IntStream.range(0,8).forEach(i -> {
            TileManager.addEntity(new Proiettile(xp, yp, null, this.vite<5 ? 3:2, 1, play, i)); //aggiunge le 8 bombe
        });

    }

    /**
     * metodo per controllare che il boss sia morto
     * @return un booleano che rappresenta se il boss e' morto
     */
    @Override
    public boolean isDead() {
        return this.dead;
    }
}
