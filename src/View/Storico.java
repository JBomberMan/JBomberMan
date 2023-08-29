package View;
import Model.Bomberman;
import Model.PunteggioManager;
import org.json.*;

import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/***
 * Rappresenta lo storico delle partite giocate
 */
public class Storico extends JPanel {

    private static JLabel storico;
    private static Storico istanza;
    private static String percorso = "src/FileLivelli/storico.json";

    /***
     * costruttore dello storico
     */
    private Storico(){
        setStorico();
        setVisible(true);
    }

    /***
     * Carica lo storico all'interno del gioco
     */
    private void setStorico(){
        try{
            File file = new File(percorso);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject obj = new JSONObject(content);
            storico = new JLabel("<html><h1>Partite giocate: " + obj.getInt("giocate")
                    + "<br>" + "Partite vinte: " + obj.getInt("vinte") + "<br>"
                    + "Partite perse: " + obj.getInt("perse") + "<br>" + "Esperienza: " + obj.getInt("esperienza") + "<br>" +
                    "Livello: " + obj.getInt("esperienza")/300 + "</h1></html>");
            add(storico);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /***
     * Serve ad aggiungere una vittoria all'interno del file dello storico
     */
    public static void addVittoria(){
        try{
            File file = new File(percorso);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject obj = new JSONObject(content);
            obj.put("giocate", obj.getInt("giocate")+1);
            obj.put("vinte", obj.getInt("vinte")+1);
            obj.put("esperienza", PunteggioManager.getPunteggio());
            Files.write(Paths.get(file.toURI()), obj.toString().getBytes());
            storico.setText("<html><h1>Partite giocate: " + obj.getInt("giocate")
                    + "<br>" + "Partite vinte: " + obj.getInt("vinte") + "<br>"
                    + "Partite perse: " + obj.getInt("perse") + "<br>" + "Esperienza: " + obj.getInt("esperienza") + "<br>" +
                    "Livello: " + obj.getInt("esperienza")/300 + "</h1></html>");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /***
     * Serve ad aggiungere una sconfitta all'interno del file dello storico
     */
    public static void addSconfitta(){
        try{
            File file = new File(percorso);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject obj = new JSONObject(content);
            obj.put("giocate", obj.getInt("giocate")+1);
            obj.put("perse", obj.getInt("perse")+1);
            Files.write(Paths.get(file.toURI()), obj.toString().getBytes());
            storico.setText("<html><h1>Partite giocate: " + obj.getInt("giocate")
                    + "<br>" + "Partite vinte: " + obj.getInt("vinte") + "<br>"
                    + "Partite perse: " + obj.getInt("perse") + "<br>" + "Esperienza: " + obj.getInt("esperienza") + "<br>" +
                    "Livello: " + obj.getInt("esperienza")/300 + "</h1></html>");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /***
     * Metodo per ottenere l'istanza corrente dello storico
     * @return istanza dello storico
     */
    public static Storico getStorico(){

        istanza = new Storico();

        return istanza;
    }

}
