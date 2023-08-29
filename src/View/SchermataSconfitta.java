package View;
import Model.Bomberman;
import Model.GettoniManager;
import Model.Partita;
import Model.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * Schermata che appare ad ogni sconfitta
 */
public class SchermataSconfitta extends JFrame{

    private JButton tornaALMenu;
    private JButton riprova;
    private static SchermataSconfitta istanza;
    private Image bgImage;
    AudioManager am = AudioManager.getInstance();

    /***
     * Il costruttore della schermata
     */
    private SchermataSconfitta(){

        setSize(320, 288);
        tornaALMenu = new JButton("Torna al Menu Principale");
        riprova = new JButton("Riprova");
        bgImage = new ImageIcon("src\\Images\\gameover.png").getImage();
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
        am.stop();
        am.play(9);

        tornaALMenu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                am.stop();
                TileManager.resetLivello();
                Storico.addSconfitta();
                Bomberman.resetVite();
                SwingUtilities.getWindowAncestor(Partita.getIstanza()).dispose();
                Partita.getIstanza().pulisci();
                Bomberman.setDead(false);
                istanza.dispose();
                ProfiloUtente.getProfilo().setVisible(true);
            }
        });

        riprova.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(Bomberman.gettoni > 0){
                    am.stop();
                    am.play(1);
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

    /***
     *
     * @return l'istanza corrente della schermata
     */
    public static SchermataSconfitta getIstanza(){

        istanza = new SchermataSconfitta();
        return istanza;
    }

}
