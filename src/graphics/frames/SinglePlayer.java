package graphics.frames;

import engine.Field;
import graphics.Dimensions;
import graphics.DrawArea;
import graphics.FrameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SinglePlayer {
    boolean isGameOver = false;
    public SinglePlayer(FrameGUI fGUI) {
        fGUI.setLayout(new FlowLayout(FlowLayout.RIGHT));
        BufferStrategy strategy = fGUI.getBufferStrategy();
        Timer timer = new Timer();

        Random rand = new Random();
        int seed = rand.nextInt(1000000);
        Field gameField = new Field(seed);
        DrawArea drawarea = new DrawArea(gameField);
        drawarea.setBlockSize(Dimensions.BLOCK_SIZE_MAIN);

        //Points label
        JLabel points = new JLabel();
        points.setFont(new Font("Serif", Font.PLAIN, 30));
        points.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Level label
        JLabel level = new JLabel();
        level.setFont(new Font("Serif", Font.PLAIN, 30));
        level.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Back button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Serif", Font.PLAIN, 30));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //resultPanel
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel,BoxLayout.Y_AXIS));
        resultsPanel.add(Box.createRigidArea(new Dimension(100, 100)));
        resultsPanel.add(points);
        resultsPanel.add(Box.createRigidArea(new Dimension(100, 100)));
        resultsPanel.add(level);
        resultsPanel.add(Box.createRigidArea(new Dimension(100, 100)));
        resultsPanel.add(exitButton);

        //Frame
        fGUI.add(drawarea);
        fGUI.add(resultsPanel);

        drawarea.setPreferredSize(new Dimension(10 * Dimensions.BLOCK_SIZE_MAIN + 1, 20 * Dimensions.BLOCK_SIZE_MAIN + 1));
        resultsPanel.setPreferredSize((new Dimension(200,600)));

        fGUI.requestFocusInWindow();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                fGUI.update(fGUI.getBufferStrategy().getDrawGraphics());
                fGUI.getBufferStrategy().show();
                fGUI.pack();
                points.setText("Points: " + String.valueOf(gameField.getScore()));
                level.setText("Level: " + String.valueOf(gameField.getLevel()));
                if (!isGameOver && gameField.getMatrix()[0][0] == 9) {
                    JOptionPane.showMessageDialog(null, "Game Over\nElért pontszámod: " + String.valueOf(gameField.getScore()));
                    isGameOver = true;
                }
            }
        }, 0, 20);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fGUI.getContentPane().removeAll();
                timer.cancel();
                fGUI.setActualFrame(7);
            }
        });
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
