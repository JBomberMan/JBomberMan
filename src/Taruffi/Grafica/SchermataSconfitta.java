package Taruffi.Grafica;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataSconfitta extends JPanel{

    private JButton tornaALMenu;
    private JButton riprova;
    private SchermataSconfitta istanza;

    private SchermataSconfitta(){

        tornaALMenu = new JButton("Torna al Menu Principale");
        riprova = new JButton("Riprova");
        setLayout(new BorderLayout());
        add(tornaALMenu, BorderLayout.CENTER);
        add(riprova, BorderLayout.CENTER);

        tornaALMenu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //TODO ritorna al menu principale
            }
        });

        riprova.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //TODO controlla le vite e se sono finite ritorna al menu principale
            }
        });

    }

}
