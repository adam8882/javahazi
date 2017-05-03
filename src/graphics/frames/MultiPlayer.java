package graphics.frames;

import engine.Field;
import graphics.DrawArea;
import graphics.FrameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by knagymate on 2017. 05. 01..
 */

public class MultiPlayer {
    public MultiPlayer(FrameGUI fGUI) {
        fGUI.setLayout(new GridLayout(2, 1));
        fGUI.getNet().sendSeed(fGUI.net_seed);

        Field gameField = new Field(fGUI.net_seed);

        JLabel points1 = new JLabel("Pontszám: " + String.valueOf(gameField.getScore()));
        JLabel level1 = new JLabel("LvL: " + String.valueOf(gameField.getLevel()));
        JLabel points2 = new JLabel("Pontszám: " + "0");
        JLabel level2 = new JLabel("LvL: " + "0");
        DrawArea drawarea1 = new DrawArea(gameField);
        DrawArea drawarea2 = new DrawArea(fGUI);

        fGUI.add(drawarea1);
        fGUI.add(drawarea2);
        fGUI.add(points1);
        fGUI.add(points2);
        fGUI.add(level1);
        fGUI.add(level2);
        drawarea1.setPreferredSize(new Dimension(300, 600));
        drawarea2.setPreferredSize(new Dimension(300, 600));
        fGUI.requestFocusInWindow();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                points1.setText("Pontszám: " + String.valueOf(gameField.getScore()));
                level1.setText("LvL: " + String.valueOf(gameField.getLevel()));
                points2.setText("Pontszám: " + String.valueOf(fGUI.net_score));
                int lvl2 = 0;
                while (fGUI.net_score > (500 + lvl2*lvl2*1000 - 1))
                    lvl2 += 1;
                level2.setText("LvL: " + Integer.toString(lvl2));
                fGUI.getNet().sendMatrix(gameField.getMatrix());
                fGUI.getNet().sendScore(gameField.getScore());

                fGUI.update(fGUI.getBufferStrategy().getDrawGraphics());
                fGUI.getBufferStrategy().show();
                fGUI.pack();
            }
        }, 0, 20);

        //Billentyű figyelés
        fGUI.requestFocusInWindow();
        fGUI.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case (KeyEvent.VK_RIGHT):
                        gameField.shiftRight();
                        break;
                    case (KeyEvent.VK_LEFT):
                        gameField.shiftLeft();
                        break;
                    case (KeyEvent.VK_UP):
                    case (KeyEvent.VK_X):
                        gameField.rotateRight();
                        break;
                    case (KeyEvent.VK_Y):
                    case (KeyEvent.VK_CONTROL):
                        gameField.rotateLeft();
                        break;
                    case (KeyEvent.VK_DOWN):
                    case (KeyEvent.VK_SPACE):
                        gameField.drop();
                        break;
                    case (KeyEvent.VK_ESCAPE):
                        fGUI.getContentPane().removeAll();
                        timer.cancel();
                        fGUI.setActualFrame(7);
                        fGUI.getNet().disconnect();
                        fGUI.isConnected = false;
                        break;
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
