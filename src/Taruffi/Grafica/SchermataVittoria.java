package Taruffi.Grafica;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataVittoria extends JPanel {

    private JButton tornaALMenu;
    private SchermataVittoria istanza;

    private SchermataVittoria(){

        tornaALMenu = new JButton("Torna al Menu Principale");
        setLayout(new BorderLayout());
        add(tornaALMenu, BorderLayout.CENTER);

        tornaALMenu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //TODO ritorna al menu principale
            }
        });

    }

}
