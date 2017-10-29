package tankgame;

import javax.swing.*;
import java.awt.*;

public class Tank extends TankGameSprites {

    public String image = "tankgame/src/tank-0.png";

    public Tank() {
        x = 0;
        y = 0;
        imageId = "tank";
        health = 100;

    }

    public void paint(Graphics graphics) {
        ImageIcon imageIcon = new ImageIcon(image);
        Image image = imageIcon.getImage();
        graphics.drawImage(image, x, y, null);
        updateGame();
    }




}
