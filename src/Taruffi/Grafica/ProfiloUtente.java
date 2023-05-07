package Taruffi.Grafica;

import Tomassetti.AudioManager;

import javax.swing.*;
import java.awt.*;

//Classe che gestisce il profilo dell'utente
public class ProfiloUtente extends JFrame{
    static AudioManager audioManager = new AudioManager(); //i panel hanno bisogno di un audio manager, quindi lo dichiaro statico
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

        super("JBomberMan");
        /*AvatarNickname av = AvatarNickname.getAvatarNickname();
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,448);
        Menu menu = new Menu();
        menu.setBackground(Color.gray);
        menu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        add(menu, BorderLayout.PAGE_START);
        Avatar av = Avatar.getAvatar();
        Nickname nick = Nickname.getNickname();
        add(av, BorderLayout.LINE_START);
        add(nick, BorderLayout.LINE_END);
        //
        //add(av, BorderLayout.BEFORE_LINE_BEGINS);
        */
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,448);
        Menu menu = new Menu();
        menu.setBackground(Color.gray);
        menu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        GridBagConstraints menuConstrains = new GridBagConstraints();
        menuConstrains.gridx = 0;
        menuConstrains.gridy = 0;
        menuConstrains.gridwidth = 4;
        menuConstrains.gridheight = 1;
        menuConstrains.fill = GridBagConstraints.HORIZONTAL;
        add(menu, menuConstrains);
        Avatar av = Avatar.getAvatar();
        GridBagConstraints avatarConstrains = new GridBagConstraints();
        avatarConstrains.gridx = 0;
        avatarConstrains.gridy = 2;
        avatarConstrains.gridwidth = 1;
        avatarConstrains.gridheight = 1;
        avatarConstrains.fill = GridBagConstraints.VERTICAL;
        add(av, avatarConstrains);
        Nickname nick = Nickname.getNickname();
        GridBagConstraints nicknameConstrains = new GridBagConstraints();
        nicknameConstrains.gridx = 0;
        nicknameConstrains.gridy = 1;
        nicknameConstrains.gridwidth = 1;
        nicknameConstrains.gridheight = 1;
        nicknameConstrains.anchor = GridBagConstraints.PAGE_END;
        add(nick, nicknameConstrains);
        audioManager.setFile(0);
        audioManager.play();
        audioManager.loop();
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

    public static void stop(){ //permettere di essere richiamato da altre classi per fermare la musica di sottofondo
        audioManager.stop();
    }
}
