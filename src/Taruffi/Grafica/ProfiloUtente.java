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
    private JLabel contenitoreStorico;

    private Storico storico;


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
        /**
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,400);
        Menu menu = new Menu();
        menu.setBackground(Color.gray);
        menu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        Insets insets = new Insets(0,0,0,0);
        GridBagConstraints menuConstrains = new GridBagConstraints();
        menuConstrains.gridx = 0;
        menuConstrains.gridy = 0;
        menuConstrains.gridwidth = 2;
        menuConstrains.gridheight = 1;
        menuConstrains.fill = GridBagConstraints.HORIZONTAL;
        menuConstrains.insets = insets;
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
         **/
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,800);

        ImageIcon bg = new ImageIcon(getClass().getResource("/Images/avatarBase.png"));
        //prima era senza getClass.getResource, errore:
        //Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.net.URL.toExternalForm()" because "url" is null
        JLabel background = new JLabel(bg);
        background.setLayout(new GridBagLayout());
        background.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        GridBagConstraints backgroundconstrains = new GridBagConstraints();
        backgroundconstrains.gridx = 0;
        backgroundconstrains.gridy = 0;
        backgroundconstrains.weightx = 1;
        backgroundconstrains.weighty = 1;
        backgroundconstrains.fill = GridBagConstraints.BOTH;
        add(background, backgroundconstrains);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        Menu menu = new Menu();
        menu.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        c.weightx = 1; //si espande sulle x
        c.weighty = 0.1; //si espande sulle Y, GLI PERMETTE DI STARE IN CIMA
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10,10,10,10);
        c.anchor = GridBagConstraints.NORTHWEST;
        background.add(menu, c);


        Avatar av = Avatar.getAvatar();
        av.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(10,10,0,10); //specifica il padding rispetto ad altri elementi
        c.weightx = 0;
        c.weighty = 0;
        //c.gridwidth = 1;
        //c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        background.add(av, c);

        Nickname nick = Nickname.getNickname();
        nick.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0,10,10,10); //specifica il padding rispetto ad altri elementi
        c.weightx = 0;
        c.weighty = 1;
        //c.gridwidth = 1;
        //c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        background.add(nick, c);
        audioManager.setFile(0);

        Storico storico = Storico.getStorico();
        storico.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10,10,10,10); //specifica il padding rispetto ad altri elementi
        c.weightx = 1;
        c.weighty = 1;
        //c.gridwidth = 1;
        //c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        background.add(storico, c);

        //audioManager.setFile(0);
        //audioManager.play();
        //audioManager.loop();
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
