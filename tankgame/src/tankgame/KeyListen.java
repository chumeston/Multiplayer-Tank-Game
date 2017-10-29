package tankgame;

import java.awt.event.*;

public class KeyListen implements KeyListener {

    public TankGameMain mainKey;

    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_D) {
            mainKey.newDraw.tank1.dx = TankGameVariables.tankSpeed;
            mainKey.newDraw.tank1.dy = 0;
            mainKey.newDraw.tank1.image = "tankgame/src/tank-0.png";
        }
        if (event.getKeyCode() == KeyEvent.VK_A) {
            mainKey.newDraw.tank1.dx = -TankGameVariables.tankSpeed;
            mainKey.newDraw.tank1.dy = 0;
            mainKey.newDraw.tank1.image = "tankgame/src/tank-30.png";
        }
        if (event.getKeyCode() == KeyEvent.VK_W) {
            mainKey.newDraw.tank1.dx = 0;
            mainKey.newDraw.tank1.dy = -TankGameVariables.tankSpeed;
            mainKey.newDraw.tank1.image = "tankgame/src/tank-15.png";
        }
        if (event.getKeyCode() == KeyEvent.VK_S) {
            mainKey.newDraw.tank1.dx = 0;
            mainKey.newDraw.tank1.dy = TankGameVariables.tankSpeed;
            mainKey.newDraw.tank1.image = "tankgame/src/tank-45.png";
        }
    }

    public void keyTyped(KeyEvent event) {
        /*
        if (event.getKeyCode() == KeyEvent.VK_D) {
            mainKey.newDraw.tank1.dx = 0;
            mainKey.newDraw.tank1.dy = 0;
            mainKey.newDraw.tank1.image = "src/tank-0.png";
        } else if (event.getKeyCode() == KeyEvent.VK_A) {
            mainKey.newDraw.tank1.dx = 0;
            mainKey.newDraw.tank1.dy = 0;
            mainKey.newDraw.tank1.image = "src/tank-30.png";
        } else if (event.getKeyCode() == KeyEvent.VK_W) {
            mainKey.newDraw.tank1.dx = 0;
            mainKey.newDraw.tank1.dy = 0;
            mainKey.newDraw.tank1.image = "src/tank-15.png";
        } else if (event.getKeyCode() == KeyEvent.VK_S) {
            mainKey.newDraw.tank1.dx = 0;
            mainKey.newDraw.tank1.dy = 0;
            mainKey.newDraw.tank1.image = "src/tank-45.png";
        }
        */
    }

    public void keyReleased(KeyEvent event) {
        //mainKey.newDraw.tank1.dx = 0;
        //mainKey.newDraw.tank1.dy = 0;
    }

}