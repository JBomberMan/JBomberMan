package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;

/***
 * Classe per costruire e visualizzare l'avatar dell'utente
 */
public class Avatar extends JPanel {

    private static JLabel contenitoreAvatar;
    private static Avatar istanza;
    private File file = new File("src/FileLivelli/profilo.json");

    /***
     * Costruttore della classe
     */
    private Avatar(){
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        setAvatar();
        setVisible(true);
    }

    /***
     * Setta il percorso dell'immagine che si vuole utilizzare come avatar
     * @param avatar il percorso del file
     */
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

    /***
     * Serve a settare l'immagine dell'avatar di default
     */
    public void setAvatar(){

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

    /***
     *
     * @return l'istanza corrente dell'avatar
     */
    public static Avatar getAvatar(){
        istanza = new Avatar();
        return istanza;
    }
}
