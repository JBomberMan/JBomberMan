package View;
import Model.Partita;
import Model.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * La finestra utilizzata ogni volta che si vince un livello
 */
public class SchermataVittoria extends JFrame {

    private JButton tornaALMenu;
    private JButton prossimoLivello;
    private static SchermataVittoria istanza;

    private Boolean personalizzato;
    private Image bgImage;



    /***
     * Costruttore della schermata
     * @param persona serve al costruttore per capire se il ivello giocato e' uno principale o uno custom e i base al livello ha un comportamento diverso
     */
    private SchermataVittoria(Boolean persona){

        this.personalizzato = persona;
        setSize(400, 250);
        tornaALMenu = new JButton("Torna al Menu Principale");
        prossimoLivello = new JButton("Prossimo Livello");
        bgImage = new ImageIcon("src\\Images\\vittoria.png").getImage();

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
        if(personalizzato) {
            panel.add(tornaALMenu, c);
        }
        else{

            panel.add(tornaALMenu, c);
            c.gridx = 1;
            panel.add(prossimoLivello, c);

        }


       AudioManager.getInstance().play(1);

        setContentPane(panel);
        setVisible(true);


        tornaALMenu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {


                TileManager.resetLivello();
                SwingUtilities.getWindowAncestor(Partita.getIstanza()).dispose();
                dispose();
                Partita.getIstanza().riavvia();
                TileManager.setLivello(1);
                ProfiloUtente.getProfilo().setVisible(true);
                AudioManager.getInstance().stop(1);
                AudioManager.getInstance().play(7);

            }
        });

        prossimoLivello.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                //AudioManager.getInstance().stop();
                Partita.getIstanza().riavvia();
                Partita.getIstanza().startGameThread();
                dispose();
                AudioManager.getInstance().stop(1);


            }
        });



    }

    /***
     * Metodo per ritornare l'istanza corrente della schermata
     * @param pers per indicarese il livello personalizzato o meno
     * @return l'istanza della scermata
     */
    public static SchermataVittoria getIstanza(Boolean pers){
        if(istanza==null){
            istanza = new SchermataVittoria(pers);
        }
        else{
            istanza.setPersonalizzato(pers);
        }

        return istanza;
    }

    /***
     * serve a modificare ogni volta la schermata
     * @param b indica se il livello personalizzato o meno
     */
    public void setPersonalizzato(Boolean b){
        this.personalizzato = b;
        setSize(400, 250);
        tornaALMenu = new JButton("Torna al Menu Principale");
        prossimoLivello = new JButton("Prossimo Livello");
        bgImage = new ImageIcon("src\\Images\\vittoria.png").getImage();

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
        if(personalizzato) {
            panel.add(tornaALMenu, c);
        }
        else{

            panel.add(tornaALMenu, c);
            c.gridx = 1;
            panel.add(prossimoLivello, c);

        }

        AudioManager.getInstance().stop(1);
        setContentPane(panel);
        setVisible(true);


        tornaALMenu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                SwingUtilities.getWindowAncestor(Partita.getIstanza()).dispose();
                dispose();
                Partita.getIstanza().riavvia();
                TileManager.setLivello(1);
                ProfiloUtente.getProfilo().setVisible(true);
                AudioManager.getInstance().stop(1);
                AudioManager.getInstance().play(7);
            }
        });

        prossimoLivello.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {


                //TODO avvia il prossimo livello
                Partita.getIstanza().riavvia();
                Partita.getIstanza().startGameThread();
                dispose();
                AudioManager.getInstance().stop(1);



            }
        });

        AudioManager.getInstance().play(1);
    }

    public void paint(Graphics g){
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        super.paint(g);
    }

}
