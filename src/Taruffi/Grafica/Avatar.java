package Taruffi.Grafica;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.*;

//Classe che gestisce l'avatar del giocatore
//TODO  : implementare il pattern MVC
public class Avatar extends JPanel {

    private static JLabel contenitoreAvatar;
    private static Avatar istanza;
    private File file = new File("src/FileLivelli/profilo.json");

    private Avatar(){
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        setAvatar();
        setVisible(true);
    }

    /**
     * Metodo che permette di settare l'avatar del giocatore
     * @param avatar Stringa contenente il percorso dell'immagine
     */
    //TODO: sostituire la stringa con un filesystem a schermo ed unire i due metodi
    public void setAvatar(String avatar){

        try{
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject obj = new JSONObject(content);
            obj.put("avatar", avatar);
            Files.write(Paths.get(file.toURI()), obj.toString().getBytes()); //salva le modifiche al file
            ImageIcon immagineAvatar = new ImageIcon((obj.getString("avatar")));
            immagineAvatar = new ImageIcon(immagineAvatar.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH));
            contenitoreAvatar.setIcon(immagineAvatar);
            contenitoreAvatar.setSize(20,20);
            contenitoreAvatar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    public void setAvatar(){
        /*
        String avatar = JOptionPane.showInputDialog("Inserisci percorso");
        ImageIcon immagineAvatar = new ImageIcon(avatar);
        */
        try{
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject obj = new JSONObject(content);
            ImageIcon immagineAvatar = new ImageIcon(getClass().getResource(obj.getString("avatar")));
            immagineAvatar = new ImageIcon(immagineAvatar.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH));
            contenitoreAvatar = new JLabel(immagineAvatar);
            contenitoreAvatar.setSize(20,20);
            contenitoreAvatar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
            add(contenitoreAvatar);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    //implementa il pattern singleton
    public static Avatar getAvatar(){
        if(istanza==null){
            istanza = new Avatar();
        }
        return istanza;
    }
}
