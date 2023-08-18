package Taruffi.Grafica;

import javax.swing.*;
import java.awt.*;

public class EditorLiveli extends JFrame {

    public EditorLiveli(){

        ELvlPanel pannello = new ELvlPanel();
        add(pannello);
        setSize(800,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
}
