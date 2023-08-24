package Controller;

import Model.ETileManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EMouseHandler extends MouseAdapter {

    private static int selectedEntity;

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            System.out.println(e.getX() + " " + e.getY());
            //trova la casella dove si é cliccato
            int mousex = e.getX() / 32;
            int mousey = e.getY() / 32;
            ETileManager.modificaVista(mousex, mousey, selectedEntity);


        }
    }

    public static void setEntity(int number){
        selectedEntity = number;
        System.out.println("Entity selected: " + selectedEntity);
    }
}
