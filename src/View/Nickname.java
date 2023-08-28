package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;

/***
 * Classe che serve per disegnare il nickname a schermo
 */
public class Nickname extends JPanel {

    private static JLabel nickname;
    private static Nickname istanza;
    private File file = new File("src/FileLivelli/profilo.json");

    /***
     * Costruttore della classe
     */
    public Nickname(){
        setNickname();
        setOpaque(false);
        setVisible(true);
    }

    /***
     * Metodo per settare il nickname corrente
     * @param nick la stringa contenente il nickname che si vuole
     */
    public void setNickname(String nick){


        try{
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject obj = new JSONObject(content);
            obj.put("nickname", nick);
            Files.write(Paths.get(file.toURI()), obj.toString().getBytes());
            nickname.setText(nick);
            add(nickname);
            System.out.println(nickname.getText());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /***
     * Per settare il nickname di default
     */
    public void setNickname(){

        try{
            //TODO fixa che se apri e non inserisci nulla cancella la entry dal json
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject obj = new JSONObject(content);
            nickname = new JLabel(obj.getString("nickname"));
            Font font = new Font("SansSerif", Font.PLAIN, 20);
            nickname.setFont(font);
            nickname.setSize(50,20);
            add(nickname);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
