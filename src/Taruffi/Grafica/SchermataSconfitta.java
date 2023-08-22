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


    private SchermataSconfitta(){

        setSize(300, 300);
        tornaALMenu = new JButton("Torna al Menu Principale");
        riprova = new JButton("Riprova");
        setLayout(new FlowLayout());
        add(tornaALMenu);
        add(riprova);

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
