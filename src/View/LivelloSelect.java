package View;

import Model.Partita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.*;

/***
 * Schermata di selezione dei livelli custom
 */
public class LivelloSelect extends JFrame {

    int livelli = 0;
    private static LivelloSelect istanza;

    /***
     * Costruttore del selettore dei livelli
     */
    private LivelloSelect(){


        String directoryPath = "src/LivelliPersonalizzati"; // Sostituisci con il percorso della directory

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directoryPath))) {
            for (Path path : directoryStream) {
                if (Files.isRegularFile(path)) {
                    livelli ++;
                    JButton bottone = new JButton(path.getFileName().toString());
                    bottone.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            AudioManager.getInstance().stop(7);
                            ProfiloUtente.stop();
                            Partita partita = new Partita(path.toString());
                            JFrame frame = new JFrame("Bomberman");
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            //audioManager.setFile(1);
                            //audioManager.play();
                            frame.add(partita);
                            frame.pack();
                            frame.setVisible(true);
                            partita.startGameThread(); //fa partire direttamente la partita senza chiedere ulteriori cose
                            ProfiloUtente.getProfilo().dispose();
                            dispose();
                        }
                    });
                    add(bottone);
                    setLayout(new GridLayout(livelli/2,2));


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(livelli/2 * 200,livelli/2 * 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



    }

    /***
     *
     * @return l'istanza corrente del selettore
     */
    public static LivelloSelect getIstanza(){

        istanza = new LivelloSelect();
        return istanza;

    }



}
