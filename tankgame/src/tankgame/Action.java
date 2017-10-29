package tankgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Action implements ActionListener {

    public TankGameMain mainKey;

    public Action(TankGameMain mainKey) {
        this.mainKey = mainKey;

    }

    public void actionPerformed(ActionEvent event) {
        mainKey.repaint();
    }
}
