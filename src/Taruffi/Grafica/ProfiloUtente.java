package Taruffi.Grafica;

import javax.swing.*;
import java.awt.*;

//Classe che gestisce il profilo dell'utente
public class ProfiloUtente extends JFrame{

    private String nickname;
    private String avatar;
    private int partiteGiocate;
    private int partiteVinte;
    private int partitePerse;
    private int livello;
    private static ProfiloUtente istanza;

    private JTextArea textArea;
    private JButton button;
    private ImageIcon immagineProfilo;
    private JLabel contenitoreImmagine;
    private JLabel contenitoreNickname;


    /**
     * Costruttore della classe ProfiloUtente
     * Privato per implementare il pattern singleton
     */
    private ProfiloUtente(){

        super("JUNO");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        Menu menu = new Menu();
        menu.setBackground(Color.gray);
        menu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        add(menu, BorderLayout.PAGE_START);
        Avatar av = Avatar.getAvatar();
        Nickname nick = Nickname.getNickname();
        add(av, BorderLayout.LINE_START);
        add(nick, BorderLayout.LINE_END);
        //AvatarNickname av = AvatarNickname.getAvatarNickname();
        //add(av, BorderLayout.BEFORE_LINE_BEGINS);
        setVisible(true);

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        Nickname.getNickname().setNickname(nickname);
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        Avatar.getAvatar().setAvatar(avatar);
    }

    public int getPartiteGiocate() {
        return partiteGiocate;
    }

    public int getPartiteVinte() {
        return partiteVinte;
    }

    public int getPartitePerse() {
        return partitePerse;
    }

    public int getLivello() {
        return livello;
    }

    //SINGLETON
    public static ProfiloUtente getProfilo(){
        if(istanza == null){
            istanza = new ProfiloUtente();
        }
        return istanza;
    }
}
