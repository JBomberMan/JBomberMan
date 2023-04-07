package Taruffi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {

    private JButton bottoneAvatar;
    private JButton bottonePartita;
    private JButton bottoneNickname;

    public Menu(){
        bottoneAvatar = new JButton("Imposta avatar");
        bottonePartita = new JButton("Avvia partita");
        bottoneNickname = new JButton("Imposta nickname");

        setLayout(new FlowLayout());
        add(bottoneAvatar);
        add(bottonePartita);
        add(bottoneNickname);

        bottoneAvatar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String immagine = JOptionPane.showInputDialog("Inserisci il percorso");
                ProfiloUtente.getProfilo().setAvatar(immagine);

            }
        });

        bottoneNickname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = JOptionPane.showInputDialog("Inserisci il nickname");
                ProfiloUtente.getProfilo().setNickname(nickname);

            }
        });
    }



}
