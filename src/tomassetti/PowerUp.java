package tomassetti;

import Taruffi.Disegnabile;

//classe che rappresenta powerup del gioco bomberman
//implementa l'interfaccia Disegnabile, dispone di due campi X e Y che rappresentano le coordinate del powerup che si può trovare anche dentro ad un blocco
//un metodo che permette di eseguire l'effetto del powerup
public abstract  class PowerUp implements Disegnabile {
    private int X;
    private int Y;
    public PowerUp(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }
    public abstract void effetto();

    public void disegna() {
        //TODO  implementare il metodo
    }
}
