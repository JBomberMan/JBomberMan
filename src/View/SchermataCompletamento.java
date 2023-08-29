package View;
import Model.Partita;
import Model.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataCompletamento extends JFrame {

    private JButton tornaALMenu;

    private static SchermataCompletamento istanza;


    private Image bgImage;


    private SchermataCompletamento(){


        setSize(400, 250);
        tornaALMenu = new JButton("COMPLIMENTI HAI COMPLETATO IL GIOCO!!!");

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

            panel.add(tornaALMenu, c);

            //c.gridy = 0;




        setContentPane(panel);
        setVisible(true);


        tornaALMenu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                TileManager.resetLivello();
                Storico.addVittoria();
                SwingUtilities.getWindowAncestor(Partita.getIstanza()).dispose();
                dispose();
                Partita.getIstanza().pulisci();
                TileManager.setLivello(1);
                ProfiloUtente.getProfilo().setVisible(true);
            }
        });



        }





    public static SchermataCompletamento getIstanza(){
        if(istanza==null){
            istanza = new SchermataCompletamento();
        }

        return istanza;
    }



    public void paint(Graphics g){
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        super.paint(g);
    }

}
