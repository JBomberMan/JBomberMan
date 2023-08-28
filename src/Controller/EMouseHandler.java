package Controller;

import Model.ETileManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe che gestisce gli eventi del mouse
 */
public class EMouseHandler extends MouseAdapter {

    private static int selectedEntity;

    /**
     * Metodo che gestisce l'evento del click del mouse
     * @param e evento del mouse
     */
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            //trova la casella dove si é cliccato
            int mousex = e.getX() / 32;
            int mousey = e.getY() / 32;
            ETileManager.modificaVista(mousex, mousey, selectedEntity);


        }
    }

    /**
     * Metodo che setta l'entitá selezionata
     * @param number numero dell'entitá selezionata
     */
    public static void setEntity(int number){
        selectedEntity = number;
        System.out.println("Entity selected: " + selectedEntity);
    }
}
