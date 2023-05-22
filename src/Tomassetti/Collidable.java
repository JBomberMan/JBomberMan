package Tomassetti;

import Gobjects.PowerUp;
import Porfiri.Bomba;
import Porfiri.Esplosione;
import Taruffi.Nemici.NemicoGenerico;
import Porfiri.BomberMan;


public interface Collidable {

    default void handleCollision(Esplosione e) {
        //TODO implementare il metodo
    }

    default void handleCollision(PowerUp p) {
        //TODO implementare il metodo
    }

    default void handleCollision(Bomba b) {
        //TODO implementare il metodo
    }

    default void handleCollision(Blocco b) {
        //TODO implementare il metodo
    }

    default void handleCollision(BomberMan b) {
        //TODO implementare il metodo
    }

    default void handleCollision(NemicoGenerico n) {
        //TODO implementare il metodo
    }
}
