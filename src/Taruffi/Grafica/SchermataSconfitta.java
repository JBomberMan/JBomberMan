package Taruffi.Grafica;
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
                Partita partita = Partita.getIstanza();
                partita.chiudi();
                istanza.dispose();
            }
        });

        riprova.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //TODO controlla le vite e se sono finite ritorna al menu principale
            }
        });

    }

    public static SchermataSconfitta getIstanza(){
        if(istanza == null)
            istanza = new SchermataSconfitta();
        return istanza;
    }

}
