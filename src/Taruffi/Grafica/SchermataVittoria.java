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


    private SchermataVittoria(Boolean persona){
        this.personalizzato = persona;
        setSize(400, 200);
        tornaALMenu = new JButton("Torna al Menu Principale");
        prossimoLivello = new JButton("Prossimo Livello");
        setLayout(new GridBagLayout());
        //add(tornaALMenu);
        //(prossimoLivello);
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        if(personalizzato) {
            add(tornaALMenu, c);
        }
        else{

            add(tornaALMenu, c);
            c.gridx = 2;
            c.gridy = 0;
            add(prossimoLivello, c);

        }
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
        personalizzato = b;
        remove(tornaALMenu);
        remove(prossimoLivello);
        setLayout(new GridBagLayout());
        //add(tornaALMenu);
        //(prossimoLivello);
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        if(personalizzato) {
            add(tornaALMenu, c);
        }
        else{

            add(tornaALMenu, c);
            c.gridx = 2;
            c.gridy = 0;
            add(prossimoLivello, c);

        }
        setVisible(true);


        tornaALMenu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                SwingUtilities.getWindowAncestor(Partita.getIstanza()).dispose();
                dispose();
                Partita.getIstanza().riavvia();
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

}
