package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TankGameMain extends JFrame {

    public TankGameDraw newDraw;
    public Container newContainer;
    public BorderLayout newLayout;
    public KeyListen keyListener;


    public TankGameMain() {
        setSize(TankGameVariables.gameWidth, TankGameVariables.gameHeight);
        setVisible(true);
        setResizable(false);
        setTitle("Tank Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        newDraw = new TankGameDraw();
        newContainer = getContentPane();
        newLayout = new BorderLayout();
        keyListener = new KeyListen();
        keyListener.mainKey = this;

        setLayout(new BorderLayout());

        newContainer.add(newDraw, BorderLayout.CENTER);

        requestFocus();

        addKeyListener(keyListener);

        ActionListener updateKey = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                repaint();
            }
        };
        new Timer(TankGameVariables.gameFps, updateKey).start();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TankGameMain();
            }
        });

    }
}

