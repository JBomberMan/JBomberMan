package Taruffi.Grafica;
import org.json.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storico extends JPanel {

    private static JLabel storico;
    private static Storico istanza;
    private static String percorso = new String("src/FileLivelli/storico.json");

    public Storico(){
        setStorico();
        //setOpaque(false);
        setVisible(true);
    }

    private void setStorico(){
        try{
            File file = new File(percorso);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject obj = new JSONObject(content);
            storico = new JLabel("<html><h1>Partite giocate: " + obj.getInt("giocate")
                    + "<br>" + "Partite vinte: " + obj.getInt("vinte") + "<br>"
                    + "Partite perse: " + obj.getInt("perse") + "</h1></html>");
            add(storico);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addVittoria(){
        try{
            File file = new File(percorso);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject obj = new JSONObject(content);
            obj.put("giocate", obj.getInt("giocate")+1);
            obj.put("vinte", obj.getInt("vinte")+1);
            Files.write(Paths.get(file.toURI()), obj.toString().getBytes());
            storico.setText("<html>Partite giocate: " + obj.getInt("giocate")
                    + "<br>" + "Partite vinte: " + obj.getInt("vinte") + "<br>"
                    + "Partite perse: " + obj.getInt("perse") + "</html>");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addSconfitta(){
        try{
            File file = new File(percorso);
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject obj = new JSONObject(content);
            obj.put("giocate", obj.getInt("giocate")+1);
            obj.put("perse", obj.getInt("perse")+1);
            Files.write(Paths.get(file.toURI()), obj.toString().getBytes());
            storico.setText("<html>Partite giocate: " + obj.getInt("giocate")
                    + "<br>" + "Partite vinte: " + obj.getInt("vinte") + "<br>"
                    + "Partite perse: " + obj.getInt("perse") + "</html>");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Storico getStorico(){
        if(istanza==null){
            istanza = new Storico();
        }
        return istanza;
    }

}
