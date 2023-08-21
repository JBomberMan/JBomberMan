package Taruffi.Grafica;

import javax.swing.*;
import java.awt.*;

public class EditorLiveli extends JFrame {

    public EditorLiveli(){


        setLayout(new GridBagLayout());


        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(40,20,10,10);
        Bottoni bottoni = new Bottoni();
        c.gridx = 0;
        c.gridy = 0;
        add(bottoni,c);

        c.gridx = 1;
        c.gridy = 0;
        c.weighty = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        ELvlPanel pannello = new ELvlPanel();
        add(pannello,c);

        setSize(800,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
}
