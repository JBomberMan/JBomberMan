package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.*;

public class Nickname extends JPanel {

    private static JLabel nickname;
    private static Nickname istanza;

    private File file = new File("src/FileLivelli/profilo.json");

    public Nickname(){
        setNickname();
        setOpaque(false);
        setVisible(true);
    }

    public void setNickname(String nick){


        try{
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject obj = new JSONObject(content);
            obj.put("nickname", nick);
            Files.write(Paths.get(file.toURI()), obj.toString().getBytes());
            nickname.setText(nick);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

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

    //implementa il pattern singleton
    public static Nickname getNickname(){
        if(istanza==null){
            istanza = new Nickname();
        }
        return istanza;
    }

}
