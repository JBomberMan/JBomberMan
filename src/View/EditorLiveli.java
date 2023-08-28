package View;

import javax.swing.*;
import java.awt.*;

/***
 * Classe per visualizzare a schermo l'editor dei livelli
 */
public class EditorLiveli extends JFrame {
    /***
     * Costruttore dell'editor
     */
    public EditorLiveli(){

        setLayout(new GridBagLayout());
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(40,20,10,10);
        Bottoni bottoni = Bottoni.getIstanza();
        c.gridx = 0;
        c.gridy = 0;
        add(bottoni,c);

        c.insets = new Insets(80,20,10,20);
        c.gridx = 1;
        c.gridy = 0;
        c.weighty = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        ELvlPanel pannello = ELvlPanel.getIstanza();
        add(pannello,c);

        setVisible(true);

    }
}
