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

        setSize(300, 300);
        tornaALMenu = new JButton("Torna al Menu Principale");
        prossimoLivello = new JButton("Prossimo Livello");
        setLayout(new BorderLayout());
        add(tornaALMenu, BorderLayout.CENTER);
        add(prossimoLivello, BorderLayout.SOUTH);

        tornaALMenu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //TODO ritorna al menu principale
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
