package tomassetti;
import Taruffi.Disegnabile;
//classe blocco che implementa l'interfaccia Disegnabile, rappresenta un blocco del gioco bomberman
//dispone di due campi X e Y che rappresentano le coordinate del blocco
public abstract class Blocco implements Disegnabile{
    private int X;
    private int Y;
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


}
