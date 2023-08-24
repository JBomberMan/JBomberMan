package Model;

import Model.*;


public interface Collidable {


   default void handleCollision(Muro c) {
        //TODO implementare il metodo
    }
    default void handleCollision(PowerUp p) {
        //TODO implementare il metodo
    }

    default void handleCollision(Bomba b) {
        //TODO implementare il metodo
    }

    default void handleCollision(StationaryEntity b) {
        //TODO implementare il metodo
    }

    default void handleCollision(Bomberman b) {
        //TODO implementare il metodo
    }

    default void handleCollision(MovingEntity n) {
        //TODO implementare il metodo
    }

    default void handleCollision(Esplosione e){
        //TODO implementare il metodo
    }
}
