package Taruffi.Grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe che rappresenta i bottoni necessari allo svolgimento delle varie azioni, ovvero
 * Impostare avatar, Impostare il nickname, Avviare una partita, Avviare l'editor dei livelli
 */
public class Menu extends JPanel {

    private JButton bottoneAvatar;
    private JButton bottonePartita;
    private JButton bottoneNickname;
    private JButton bottoneEditor;

    /**
     * Costruttore che inizializza i bottoni e li aggiunge al pannello
     */
    public Menu(){
        bottoneAvatar = new JButton("Imposta avatar");
        bottonePartita = new JButton("Avvia partita");
        bottoneNickname = new JButton("Imposta nickname");
        bottoneEditor = new JButton("Avvia editor livelli");

        setLayout(new FlowLayout());
        add(bottoneAvatar);
        add(bottonePartita); //TODO: aggiungere l'azione di avviare la partita
        add(bottoneNickname);
        add(bottoneEditor); //TODO: aggiungere l'azione di avviare l'editor dei livelli

        /**
         * Classe anonima per impostare il percorso dell'avatar
         */
        bottoneAvatar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String immagine = JOptionPane.showInputDialog("Inserisci il percorso");
                ProfiloUtente.getProfilo().setAvatar(immagine);

            }
        });

        /**
         * Classe anonima per impostare il nickname
         */
        bottoneNickname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = JOptionPane.showInputDialog("Inserisci il nickname");
                ProfiloUtente.getProfilo().setNickname(nickname);
            }
        });
    }



}
