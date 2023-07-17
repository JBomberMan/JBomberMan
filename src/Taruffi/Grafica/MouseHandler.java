package Taruffi.Grafica;
import Gobjects.Bomberman;
import Gobjects.StationaryEntity;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseHandler  extends MouseAdapter{

    public void mouseClicked(MouseEvent e){
        if (e.getButton() == MouseEvent.BUTTON1){
            System.out.println(e.getX() + " " + e.getY());
            //trova la casella dove si é cliccato
            int mousex = e.getX()/64;
            int mousey = e.getY()/64;
            System.out.println("casella " + mousex + " " + mousey);

            ArrayList<ArrayList<Integer>> map = new ArrayList<>();
            for (int i = 0; i < 13; i++) {
                ArrayList<Integer> row = new ArrayList<>();
                for (int j = 0; j < 17; j++) {
                    row.add(0);
                }
                map.add(row);
            }
            for(ArrayList<Integer> row : map){
                System.out.println(row);
            }
            System.out.println("--------------------");

            ArrayList<StationaryEntity> tiles = TileManager.getStationaryEntities();
            for(StationaryEntity tile : tiles){

                int xx = tile.getX()/64;
                int yy = tile.getY()/64;
                map.get(yy).set(xx, 1);

            }
            for(ArrayList<Integer> row : map){
                System.out.println(row);
            }
            int bomberx = Bomberman.getX()/64;
            int bombery = Bomberman.getY()/64;
            map.get(bombery).set(bomberx, 2);
            //a questo punto 1=blocco 0=libero 2=bomberman
        }
        else if (e.getButton() == MouseEvent.BUTTON2)
            System.out.println("Mouse Middle Clicked");
        else if (e.getButton() == MouseEvent.BUTTON3)
            System.out.println("Mouse Right Clicked");
    }


}
