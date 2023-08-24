package View;

import javax.swing.*;
import java.awt.*;

public class ProfiloUtente extends JFrame{
    static AudioManager audioManager = new AudioManager(); //i panel hanno bisogno di un audio manager, quindi lo dichiaro statico
    private String nickname;
    private String avatar;
    private static ProfiloUtente istanza;

    private ProfiloUtente(){

        super("JBomberMan");
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,800);

        ImageIcon bg = new ImageIcon(getClass().getResource("/Images/backgroundBomberman.png"));
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
        c.insets = new Insets(100,10,0,10); //specifica il padding rispetto ad altri elementi
        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        background.add(av, c);

        Nickname nick = Nickname.getNickname();
        nick.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0,10,400,10); //specifica il padding rispetto ad altri elementi
        c.weightx = 0;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        background.add(nick, c);
        audioManager.setFile(0);

        Storico storico = Storico.getStorico();
        storico.setBackground(Color.gray);
        storico.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createLineBorder(Color.BLACK)));
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10,10,10,10); //specifica il padding rispetto ad altri elementi
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        background.add(storico, c);

        //audioManager.setFile(0);
        //audioManager.play();
        //audioManager.loop();
        setVisible(true);

    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        Nickname.getNickname().setNickname(nickname);
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        Avatar.getAvatar().setAvatar(avatar);
    }

    public static ProfiloUtente getProfilo(){

        if(istanza == null)
        istanza = new ProfiloUtente();

        return istanza;
    }

    public static void stop(){ //permettere di essere richiamato da altre classi per fermare la musica di sottofondo
        audioManager.stop();
    }
}
