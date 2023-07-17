package Taruffi.Grafica;
import Gobjects.Bomberman;
import Gobjects.StationaryEntity;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

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
            System.out.println("--------------------");
            System.out.println("bomberman " + bomberx + " " + bombery);
            System.out.println("cliccato " + mousex + " " + mousey);
            List<Coordinate> path = findShortestPath(bomberx, bombery, mousex, mousey, map);

            // Stampa il percorso
            for (Coordinate coordinate : path) {
                System.out.println("Casella: " + coordinate.x + " " + coordinate.y);
            }
        }
        else if (e.getButton() == MouseEvent.BUTTON2)
            System.out.println("Mouse Middle Clicked");
        else if (e.getButton() == MouseEvent.BUTTON3)
            System.out.println("Mouse Right Clicked");
    }

    public static List<Coordinate> findShortestPath(int bomberx, int bombery, int mousex, int mousey, ArrayList<ArrayList<Integer>> map) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Direzioni possibili: sinistra, destra, alto, basso
        Queue<Coordinate> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.size()][map.get(0).size()];
        Map<Coordinate, Coordinate> parentMap = new HashMap<>();

        if (bomberx < 0 || bomberx >= map.get(0).size() || bombery < 0 || bombery >= map.size() || map.get(bombery).get(bomberx) == 1) {
            System.out.println("Coordinate del giocatore non valide o posizione bloccata.");
            return new ArrayList<>();
        }

        if (mousex < 0 || mousex >= map.get(0).size() || mousey < 0 || mousey >= map.size() || map.get(mousey).get(mousex) == 1) {
            System.out.println("Coordinate della casella premuta non valide o posizione bloccata.");
            return new ArrayList<>();
        }

        Coordinate start = new Coordinate(bomberx, bombery);
        Coordinate end = new Coordinate(mousex, mousey);

        queue.add(start);
        visited[start.y][start.x] = true;

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if (current.equals(end)) {
                break; // Abbiamo trovato la casella premuta, interrompiamo l'esplorazione
            }

            for (int[] direction : directions) {
                int newX = current.x + direction[0];
                int newY = current.y + direction[1];

                if (isValid(newX, newY, map) && !visited[newY][newX] && map.get(newY).get(newX) == 0) {
                    Coordinate neighbor = new Coordinate(newX, newY);
                    queue.add(neighbor);
                    visited[newY][newX] = true;
                    parentMap.put(neighbor, current);
                }
            }
        }

        // Costruisci il percorso dalla casella premuta fino alla casella del giocatore
        List<Coordinate> path = new ArrayList<>();
        if (!visited[mousey][mousex]) {
            return path; // Nessun percorso disponibile
        }

        Coordinate current = new Coordinate(mousex, mousey);
        while (!current.equals(start)) {
            path.add(current);
            current = parentMap.get(current);

            if (current == null) {
                // Gestisci il caso in cui non esista un percorso
                System.out.println("Percorso non trovato.");
                return new ArrayList<>();
            }
        }
        path.add(start); // Aggiungi la casella di partenza al percorso
        Collections.reverse(path); // Inverti il percorso per ottenere il giusto ordine

        return path;
    }

    // Metodo per verificare se una casella è all'interno dei limiti della mappa
    private static boolean isValid(int x, int y, ArrayList<ArrayList<Integer>> map) {
        return x >= 0 && x < map.get(0).size() && y >= 0 && y < map.size();
    }


}
