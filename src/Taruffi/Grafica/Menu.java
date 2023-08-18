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
    AudioManager audioManager = new AudioManager();

    /**
     * Costruttore che inizializza i bottoni e li aggiunge al pannello
     */
    public Menu(){

        bottoneAvatar = new JButton("Imposta avatar");
        bottonePartita = new JButton("Avvia partita");
        bottoneNickname = new JButton("Imposta nickname");
        bottoneEditor = new JButton("Avvia editor livelli");
        setOpaque(false);

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
                JFrame frame = new JFrame("Editor Livello");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                
                JPanel eLvlPanel = new JPanel(new BorderLayout());
                

                //pannello di sinistra
                JPanel leftPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();


                JButton leftArrowButton = new JButton("\u2190");
                JButton rightArrowButton = new JButton("\u2192");

                JLabel imageLabel = new JLabel(new ImageIcon("\\src\\Images\\avatarBase.png")); 

                leftArrowButton.setPreferredSize(new Dimension(50, 50));
                rightArrowButton.setPreferredSize(new Dimension(50, 50));
                imageLabel.setPreferredSize(new Dimension(80, 80));

                gbc.gridx = 1;
                gbc.gridy = 1;
                gbc.fill = GridBagConstraints.NONE;
                leftPanel.add(leftArrowButton, gbc);

                gbc.gridx = 2;
                gbc.gridy = 1;
                gbc.fill = GridBagConstraints.NONE;
                leftPanel.add(imageLabel, gbc);

                gbc.gridx = 3;
                gbc.gridy = 1;
                gbc.fill = GridBagConstraints.NONE;
                leftPanel.add(rightArrowButton, gbc);

                
                
                

                eLvlPanel.add(leftPanel, BorderLayout.WEST);
                

                // Pannello destro con la griglia di bottoni
                JPanel rightPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc2 = new GridBagConstraints();
        
                for (int i = 0; i < 13; i++) {
                    for (int j = 0; j < 17; j++) {
                        JButton button = new JButton();
                        button.setPreferredSize(new Dimension(32, 32));
                        gbc2.gridx = j;
                        gbc2.gridy = i;
                        gbc2.fill = GridBagConstraints.NONE;
                        rightPanel.add(button, gbc2);
                    }
                }

                eLvlPanel.add(rightPanel, BorderLayout.EAST);

                // Aggiungi il pannello alla finestra
                frame.getContentPane().add(eLvlPanel);
                
                // Imposta le dimensioni della finestra
                frame.setSize(1200, 800);
                
                // Rendi la finestra visibile
                frame.setVisible(true); 
                    }
        });



    }



}
