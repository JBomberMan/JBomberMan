package Taruffi.Grafica;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataVittoria extends JFrame {

    private JButton tornaALMenu;
    private JButton prossimoLivello;
    private static SchermataVittoria istanza;

    private Boolean personalizzato;
    private Image bgImage;


    private SchermataVittoria(Boolean persona){

        this.personalizzato = persona;
        setSize(400, 250);
        tornaALMenu = new JButton("Torna al Menu Principale");
        prossimoLivello = new JButton("Prossimo Livello");
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
        if(personalizzato) {
            panel.add(tornaALMenu, c);
        }
        else{

            panel.add(tornaALMenu, c);
            c.gridx = 1;
            //c.gridy = 0;
            panel.add(prossimoLivello, c);

        }

        setContentPane(panel);
        setVisible(true);


        tornaALMenu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                SwingUtilities.getWindowAncestor(Partita.getIstanza()).dispose();
                dispose();
                Partita.getIstanza().riavvia();
                TileManager.setLivello(1);
                ProfiloUtente.getProfilo().setVisible(true);
            }
        });

        prossimoLivello.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {


                //TODO avvia il prossimo livello
                Partita.getIstanza().riavvia();
                Partita.getIstanza().startGameThread();
                dispose();



            }
        });



    }

    public static SchermataVittoria getIstanza(Boolean pers){
        if(istanza==null){
            istanza = new SchermataVittoria(pers);
        }
        else{
            istanza.setPersonalizzato(pers);
        }
        return istanza;
    }

    public void setPersonalizzato(Boolean b){
        this.personalizzato = b;
        setSize(400, 250);
        tornaALMenu = new JButton("Torna al Menu Principale");
        prossimoLivello = new JButton("Prossimo Livello");
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
        if(personalizzato) {
            panel.add(tornaALMenu, c);
        }
        else{

            panel.add(tornaALMenu, c);
            c.gridx = 1;
            //c.gridy = 0;
            panel.add(prossimoLivello, c);

        }

        setContentPane(panel);
        setVisible(true);


        tornaALMenu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                SwingUtilities.getWindowAncestor(Partita.getIstanza()).dispose();
                dispose();
                Partita.getIstanza().riavvia();
                TileManager.setLivello(1);
                ProfiloUtente.getProfilo().setVisible(true);
            }
        });

        prossimoLivello.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {


                //TODO avvia il prossimo livello
                Partita.getIstanza().riavvia();
                Partita.getIstanza().startGameThread();
                dispose();



            }
        });

    }

    public void paint(Graphics g){
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        super.paint(g);
    }

}
