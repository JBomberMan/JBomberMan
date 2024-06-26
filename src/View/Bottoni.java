package View;

import Controller.EMouseHandler;
import Model.ETileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * Rappresenta i bottoni che vengono utilizzati all'interno dell'editor
 */
public class Bottoni extends JPanel {

    private static Bottoni istanza;


    /***
     * Costruttore dei vari bottoni per selezionare quale oggetto selezionare
     */
    private Bottoni(){
        setLayout(new GridLayout(5,2));
        ImageIcon grass = new ImageIcon("src/Images/grass.png");
        ImageIcon bomberman = new ImageIcon("src/Images/BomberMan_down1.png");
        ImageIcon muroInd = new ImageIcon("src/Images/blocco.png");
        ImageIcon muroDest = new ImageIcon("src/Images/bloccoDistruttibile.png");
        ImageIcon baloon = new ImageIcon("src/Images/baloon/baloon (1).png");
        ImageIcon doll = new ImageIcon("src/Images/doll/doll (1).png");
        ImageIcon kondoria = new ImageIcon("src/Images/kondoria/kondoria (1).png");
        ImageIcon oneal = new ImageIcon("src/Images/oneal/oneal (1).png");
        ImageIcon ovapi = new ImageIcon("src/Images/ovapi/ovapi (1).png");


        ImageIcon grassScaled = new ImageIcon(grass.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
        ImageIcon ovapiScaled = new ImageIcon(ovapi.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
        ImageIcon onealScaled = new ImageIcon(oneal.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
        ImageIcon kondoriaScaled = new ImageIcon(kondoria.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
        ImageIcon dollScaled = new ImageIcon(doll.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
        ImageIcon baloonScaled = new ImageIcon(baloon.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
        ImageIcon muroIndScaled = new ImageIcon(muroInd.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
        ImageIcon muroDestScaled = new ImageIcon(muroDest.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

        JButton bombermanButton = new JButton(bomberman);
        bombermanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EMouseHandler.setEntity(7);
            }
        });
        add(bombermanButton);

        JButton muroIndButton = new JButton(muroIndScaled);
        muroIndButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EMouseHandler.setEntity(5);
            }
        });
        add(muroIndButton);

        JButton muroDestButton = new JButton(muroDestScaled);
        muroDestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EMouseHandler.setEntity(6);
            }
        });
        add(muroDestButton);

        JButton baloonButton = new JButton(baloonScaled);
        baloonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EMouseHandler.setEntity(10);
            }
        });
        add(baloonButton);

        JButton dollButton = new JButton(dollScaled);
        dollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EMouseHandler.setEntity(12);
            }
        });
        add(dollButton);

        JButton kondoriaButton = new JButton(kondoriaScaled);
        kondoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EMouseHandler.setEntity(11);
            }
        });
        add(kondoriaButton);

        JButton onealButton = new JButton(onealScaled);
        onealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EMouseHandler.setEntity(9);
            }
        });
        add(onealButton);

        JButton ovapiButton = new JButton(ovapiScaled);
        ovapiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EMouseHandler.setEntity(13);
            }
        });
        add(ovapiButton);

        JButton grassButton = new JButton(grassScaled);
        grassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EMouseHandler.setEntity(0);
            }
        });
        add(grassButton);

        JButton salva = new JButton("Salva");
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ETileManager.salvaLivello();
                ETileManager.setBomber(false);
                SwingUtilities.getWindowAncestor(salva).dispose();

            }
        });
        add(salva);


    }

    /***
     *
     * @return l'istanza corrente della schermata dei bottoni
     */
    public static Bottoni getIstanza(){

        istanza = new Bottoni();

        return istanza;
    }
}
