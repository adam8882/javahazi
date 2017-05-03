package graphics.frames;
//testtt

import engine.Field;
import graphics.Dimensions;
import graphics.DrawArea;
import graphics.FrameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SinglePlayer {
    public SinglePlayer(FrameGUI fGUI) {
        BufferStrategy strategy = fGUI.getBufferStrategy();
        Random rand = new Random();
        int seed = rand.nextInt(1000000);
        Timer timer = new Timer();
        Field gameField = new Field(seed);
        DrawArea drawarea = new DrawArea(gameField);
        fGUI.add(drawarea);
        JLabel points = new JLabel("Pontszám: " + String.valueOf(gameField.getScore()));
        JLabel level = new JLabel("LvL: " + String.valueOf(gameField.getLevel()));
        fGUI.add(points);
        fGUI.add(level);
        drawarea.setPreferredSize(new Dimension(10 * Dimensions.BLOCK_SIZE, 20 * Dimensions.BLOCK_SIZE));
        fGUI.requestFocusInWindow();
        fGUI.pack();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                fGUI.update(fGUI.getBufferStrategy().getDrawGraphics());
                //frame.pack();
                fGUI.getBufferStrategy().show();
                points.setText("Pontszám: " + String.valueOf(gameField.getScore()));
                level.setText("LvL: " + String.valueOf(gameField.getLevel()));
            }
        }, 0, 20);

        //Billentyű figyelés
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
