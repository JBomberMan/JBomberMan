package Tomassetti;
import Porfiri.BomberMan;
import Porfiri.Esplosione;
import Taruffi.Disegnabile;
import Taruffi.Nemici.NemicoGenerico;

import java.awt.*;
//classe blocco che implementa l'interfaccia Disegnabile, rappresenta un blocco del gioco bomberman
//dispone di due campi X e Y che rappresentano le coordinate del blocco
public abstract class Blocco implements Disegnabile, Collidable{
    private int X;
    private int Y;
    Image[] Sprite;
    public Blocco(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }

    public void disegna() {
        //TODO: implementare il metodo
    }

    public void handleCollision(Esplosione e) {
        //TODO: implementare il metodo
    }

    @Override
    public void handleCollision(BomberMan b) {
        //TODO: implementare il metodo
    }

    public void handleCollision(NemicoGenerico n){
        //TODO: implementare il metodo
    }
}
