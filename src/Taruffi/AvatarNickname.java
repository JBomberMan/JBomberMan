package Taruffi;

import javax.swing.*;
import java.awt.*;

public class AvatarNickname extends JPanel {

    private static JLabel nickname;
    private static JLabel contenitoreAvatar;
    private static AvatarNickname istanza;

    private AvatarNickname(){

        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        setNickname();
        setAvatar();
        setVisible(true);


    }

    public static AvatarNickname getAvatarNickname(){
        if(istanza==null){
            istanza = new AvatarNickname();
        }
        return istanza;
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

    public void setAvatar(String avatar){

        ImageIcon immagineAvatar = new ImageIcon(avatar);
        immagineAvatar = new ImageIcon(immagineAvatar.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH));
        contenitoreAvatar.setIcon(immagineAvatar);
        contenitoreAvatar.setSize(100,100);
        contenitoreAvatar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));


    }

    public void setAvatar(){
        String avatar = JOptionPane.showInputDialog("Inserisci percorso");
        ImageIcon immagineAvatar = new ImageIcon(avatar);
        immagineAvatar = new ImageIcon(immagineAvatar.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH));
        contenitoreAvatar = new JLabel(immagineAvatar);
        contenitoreAvatar.setSize(20,20);
        contenitoreAvatar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        add(contenitoreAvatar);
    }

}
