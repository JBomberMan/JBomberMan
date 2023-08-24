package Taruffi.Grafica;
import Gobjects.Bomberman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataSconfitta extends JFrame{

    private JButton tornaALMenu;
    private JButton riprova;
    private static SchermataSconfitta istanza;
    private Image bgImage;


    private SchermataSconfitta(){

        setSize(300, 300);
        tornaALMenu = new JButton("Torna al Menu Principale");
        riprova = new JButton("Riprova");
        bgImage = new ImageIcon("src\\Images\\vittoria.png").getImage();
        //setLayout(new GridBagLayout());
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        panel.add(tornaALMenu, c);
        c.gridx = 1;
        panel.add(riprova, c);
        setContentPane(panel);

        tornaALMenu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Storico.addSconfitta();
                SwingUtilities.getWindowAncestor(Partita.getIstanza()).dispose();
                istanza.dispose();
                ProfiloUtente.getProfilo().setVisible(true);
            }
        });

        riprova.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(Bomberman.gettoni > 0){
                    //SwingUtilities.getWindowAncestor(Partita.getIstanza()).dispose();
                    Bomberman.gettoni--;
                    Bomberman.setDead(false);
                    Partita.getIstanza().riavvia();
                    Partita.getIstanza().startGameThread();
                    istanza.dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "Non hai più gettoni, torna al menu principale", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    public static SchermataSconfitta getIstanza(){
        if(istanza == null)
            istanza = new SchermataSconfitta();
        return istanza;
    }

}
