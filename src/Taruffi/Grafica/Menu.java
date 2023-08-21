package Taruffi.Grafica;

import Tomassetti.AudioManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Classe che rappresenta i bottoni necessari allo svolgimento delle varie azioni, ovvero
 * Impostare avatar, Impostare il nickname, Avviare una partita, Avviare l'editor dei livelli
 */
public class Menu extends JPanel {

    private JButton bottoneAvatar;
    private JButton bottonePartita;
    private JButton bottoneNickname;
    private JButton bottoneEditor;
    private JButton bottoneLivelliPers;
    AudioManager audioManager = new AudioManager();

    /**
     * Costruttore che inizializza i bottoni e li aggiunge al pannello
     */
    public Menu(){

        bottoneAvatar = new JButton("Imposta avatar");
        bottonePartita = new JButton("Avvia partita");
        bottoneNickname = new JButton("Imposta nickname");
        bottoneEditor = new JButton("Avvia editor livelli");
        bottoneLivelliPers = new JButton("Livelli personalizzati");
        setOpaque(false);

        setLayout(new FlowLayout());
        add(bottoneAvatar);
        add(bottonePartita);
        add(bottoneNickname);
        add(bottoneEditor);
        add(bottoneLivelliPers);

        /**
         * Classe anonima per impostare il percorso dell'avatar
         */
        bottoneAvatar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String percorso = selectedFile.getAbsolutePath();
                    ProfiloUtente.getProfilo().setAvatar(percorso);
                }

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

        /**
         * Classe anonima per avviare la partita
         */
        bottonePartita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfiloUtente.stop();
                Partita partita = new Partita();
                JFrame frame = new JFrame("Bomberman");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //audioManager.setFile(1);
                //audioManager.play();
                frame.add(partita);
                frame.pack();
                frame.setVisible(true);
                partita.startGameThread(); //fa partire direttamente la partita senza chiedere ulteriori cose
                                           //leggasi "dovrebbe", va tutto debuggato
                ProfiloUtente.getProfilo().dispose();
            }
        });

        bottoneEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditorLiveli editorLiveli = new EditorLiveli();

            }
        });

        bottoneLivelliPers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LivelloSelect livelloSelect = new LivelloSelect();

            }
        });



    }



}
