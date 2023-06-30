package Taruffi.Grafica;

import javax.swing.*;
import java.awt.*;

public class Nickname extends JPanel {

    private static JLabel nickname;
    private static Nickname istanza;

    public Nickname(){
        setNickname();
        setOpaque(false);
        setVisible(true);
    }

    public void setNickname(String nick){

        nickname.setText(nick);

    }

    public void setNickname(){
        String nick = JOptionPane.showInputDialog("Inserisci il nickname");
        nickname = new JLabel(nick);
        Font font = new Font("SansSerif", Font.PLAIN, 20);
        nickname.setFont(font);
        nickname.setSize(50,20);
        add(nickname);
    }

    //implementa il pattern singleton
    public static Nickname getNickname(){
        if(istanza==null){
            istanza = new Nickname();
        }
        return istanza;
    }

}
