package Model;

import Model.*;

/**
 * interfaccia per gestire le collisioni
 */
public interface Collidable {

 /**
  * metodo per gestire la collisione con un muro
  * @param c il muro con cui si e' verificata la collisione
  */
   default void handleCollision(Muro c) {
        //TODO implementare il metodo
    }

 /**
  * metodo per gestire la collisione con un powerup
  * @param p il powerup con cui si e' verificata la collisione
  */
 default void handleCollision(PowerUp p) {
        //TODO implementare il metodo
    }

 /**
  * metodo per gestire la collisione con una bomba
  * @param b la bomba con cui si e' verificata la collisione
  */
 default void handleCollision(Bomba b) {
        //TODO implementare il metodo
    }

 /**
  * metodo per gestire la collisione con una entitá stazionaria
  * @param b l'entitá stazionaria con cui si e' verificata la collisione
  */
 default void handleCollision(StationaryEntity b) {
        //TODO implementare il metodo
    }

 /**
  * metodo per gestire la collisione con un bomberman
  * @param b il bomberman con cui si e' verificata la collisione
  */
 default void handleCollision(Bomberman b) {
        //TODO implementare il metodo
    }

 /**
  * metodo per gestire la collisione con un nemico
  * @param n il nemico con cui si e' verificata la collisione
  */
 default void handleCollision(MovingEntity n) {
        //TODO implementare il metodo
    }

 /**
  * metodo per gestire la collisione con una esplosione
  * @param e l'esplosione con cui si e' verificata la collisione
  */
    default void handleCollision(Esplosione e){
        //TODO implementare il metodo
    }
}
