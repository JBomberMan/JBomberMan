package Taruffi.Grafica;

import javax.swing.*;
import java.awt.*;

//Classe che gestisce l'avatar del giocatore
//TODO  : implementare il pattern MVC
public class Avatar extends JPanel {

    private static JLabel contenitoreAvatar;
    private static Avatar istanza;

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

        ImageIcon immagineAvatar = new ImageIcon(getClass().getResource(avatar));
        immagineAvatar = new ImageIcon(immagineAvatar.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH));
        contenitoreAvatar.setIcon(immagineAvatar);
        contenitoreAvatar.setSize(100,100);
        contenitoreAvatar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));

    }

    public void setAvatar(){
        /*
        String avatar = JOptionPane.showInputDialog("Inserisci percorso");
        ImageIcon immagineAvatar = new ImageIcon(avatar);
        */
        ImageIcon immagineAvatar = new ImageIcon(getClass().getResource("/Images/avatarBase.png"));
        immagineAvatar = new ImageIcon(immagineAvatar.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH));
        contenitoreAvatar = new JLabel(immagineAvatar);
        contenitoreAvatar.setSize(20,20);
        contenitoreAvatar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        add(contenitoreAvatar);
    }

    //implementa il pattern singleton
    public static Avatar getAvatar(){
        if(istanza==null){
            istanza = new Avatar();
        }
        return istanza;
    }
}
