package Taruffi.Nemici;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Nemico4 extends NemicoGenerico {

        public Nemico4(Image[] animazione, int x, int y, int puntiVita) {
            super(animazione, x, y, puntiVita);
            getEnemiesImage();
        }

        @Override
        public void pattern() {
            //pattern di movimento del nemico
        }

        public void getEnemiesImage() {
            try {
                up1 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
            up2 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
            down1 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
            down2 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
            right1 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
            right2 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
            left1 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
            left2 = ImageIO.read(getClass().getResourceAsStream("nome_file"));
            
        } catch(IOException e){
                e.printStackTrace();
            }
        }
}
