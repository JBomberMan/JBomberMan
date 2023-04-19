package Tomassetti;
import java.awt.*;
import java.util.Observable;

//il blocco può contenere un powerup, se il blocco viene distrutto il powerup viene rilasciato
// può essere visto da Livello per sapere se il blocco è distrutto(implementa l'interfaccia Observable)
public class DestructibleBlock extends Block implements Observable {
    private boolean distrutto = false;
    private PowerUp powerup;
    int indiceAnimazione;
    public DestructibleBlock(int X, int Y, PowerUp powerup) {
        super(X, Y);
        this.powerup = powerup;
        this.indiceAnimazione = 0;
    }
    public void distruggi() {
        setDistrutto(true);
    }
    public Image nextSprite() {return Sprite[indiceAnimazione++ % Sprite.length];}
    public boolean isDistrutto() {
        return distrutto;
    }
    public void setDistrutto(boolean distrutto) {
        this.distrutto = distrutto;
    }
    public PowerUp getPowerup() {
        return powerup;
    }
    public void setPowerup(PowerUp powerup) {
        this.powerup = powerup;
    }
    @Override
    public void addObserver(Observer o) {
        // TODO Auto-generated method stub

    }
}

