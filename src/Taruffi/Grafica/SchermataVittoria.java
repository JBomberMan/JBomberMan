package Taruffi.Grafica;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataVittoria extends JFrame {

    private JButton tornaALMenu;
    private JButton prossimoLivello;
    private static SchermataVittoria istanza;


    private SchermataVittoria(){

        setSize(400, 200);
        tornaALMenu = new JButton("Torna al Menu Principale");
        prossimoLivello = new JButton("Prossimo Livello");
        setLayout(new GridBagLayout());
        //add(tornaALMenu);
        //(prossimoLivello);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        add(tornaALMenu, c);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        add(prossimoLivello, c);


        tornaALMenu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                SwingUtilities.getWindowAncestor(Partita.getIstanza()).dispose();
                istanza.dispose();
                ProfiloUtente.getProfilo().setVisible(true);
            }
        });

        prossimoLivello.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {


                //TODO avvia il prossimo livello
                //dovrebbe usare thread.sleep ma non va
                Partita.getIstanza().riavvia();
                Partita.getIstanza().startGameThread();
                istanza.dispose();



            }
        });



    }

    public static SchermataVittoria getIstanza(){
        if(istanza == null)
            istanza = new SchermataVittoria();
        return istanza;
    }

}
