package tankgame;

import javax.swing.*;
import java.awt.*;

public class TankGameDraw extends JPanel  {

    public Tank tank1 = new Tank();

    public TankGameDraw() {

    }

    public void paint(Graphics graphics) {
        graphics.setColor(Colors.green);
        graphics.fillRect(0, 0, TankGameVariables.gameWidth, TankGameVariables.gameHeight);
        paintTank(graphics);
    }

    public void paintTerrain(Graphics graphics) {

    }

    public void paintEnemies(Graphics graphics) {

    }

    public void paintTank(Graphics graphics) {
        tank1.paint(graphics);
    }




}
