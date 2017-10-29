package tankgame;

import javax.swing.*;
import java.awt.*;

public class TankGameSprites extends JPanel {

    public int x = 100, y = 100, width, height, health, dx, dy;

    public int placeHolder = 0;

    public String imageId;

    public ImageIcon imageIcon;

    public Image image;

    public void updateGame() {
        x += dx;
        y += dy;
    }

    public void destroy() {

    }
}
