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

/**
 * Created by knagymate on 2017. 05. 01..
 */

public class MultiPlayer {
    boolean isGameOver = false;
    Timer timer;
    public MultiPlayer(FrameGUI fGUI) {
        fGUI.setLayout(new FlowLayout(FlowLayout.RIGHT));
        BufferStrategy strategy = fGUI.getBufferStrategy();
        Random rand = new Random();

        //Engine
        Field gameField = new Field(fGUI.getSeed());

        //Drawareas
        DrawArea drawarea1 = new DrawArea(gameField);
        DrawArea drawarea2 = new DrawArea(fGUI);
        drawarea1.setBlockSize(Dimensions.BLOCK_SIZE_MAIN);
        drawarea2.setBlockSize(Dimensions.BLOCK_SIZE_SEC);

        //Points label
        JLabel points1 = new JLabel();
        points1.setFont(new Font("Serif", Font.PLAIN, 30));
        points1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel points2 = new JLabel();
        points2.setFont(new Font("Serif", Font.PLAIN, 20));
        points2.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Level label
        JLabel level1 = new JLabel();
        level1.setFont(new Font("Serif", Font.PLAIN, 30));
        level1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel level2 = new JLabel();
        level2.setFont(new Font("Serif", Font.PLAIN, 20));
        level2.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Name label
        JLabel name2 = new JLabel();
        name2.setFont(new Font("Serif", Font.PLAIN, 30));
        name2.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Serif", Font.PLAIN, 30));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //resultPanel1
        JPanel resultsPanel1 = new JPanel();
        resultsPanel1.setLayout(new BoxLayout(resultsPanel1,BoxLayout.Y_AXIS));
        resultsPanel1.add(Box.createRigidArea(new Dimension(100, 100)));
        resultsPanel1.add(points1);
        resultsPanel1.add(Box.createRigidArea(new Dimension(100, 100)));
        resultsPanel1.add(level1);
        resultsPanel1.add(Box.createRigidArea(new Dimension(100, 100)));
        resultsPanel1.add(exitButton);

        //resultPanel2
        JPanel resultsPanel2 = new JPanel();
        resultsPanel2.setLayout(new BoxLayout(resultsPanel2,BoxLayout.Y_AXIS));
        resultsPanel2.add(name2);
        resultsPanel2.add(drawarea2);
        resultsPanel2.add(points2);
        resultsPanel2.add(level2);

        //Frame
        fGUI.add(drawarea1);
        fGUI.add(resultsPanel1);
        fGUI.add(resultsPanel2);

        //Panel sizes
        drawarea1.setPreferredSize(new Dimension(10 * Dimensions.BLOCK_SIZE_MAIN + 1, 20 * Dimensions.BLOCK_SIZE_MAIN + 1));
        drawarea2.setPreferredSize(new Dimension(10 * Dimensions.BLOCK_SIZE_SEC + 1, 20 * Dimensions.BLOCK_SIZE_SEC + 1));
        resultsPanel1.setPreferredSize((new Dimension(200,600)));

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //Network send
                if(fGUI.isConnected) {
                    fGUI.getNet().sendMatrix(gameField.getMatrix());
                    fGUI.getNet().sendScore(gameField.getScore());
                }

                //Network get
                points1.setText("Points: " + String.valueOf(gameField.getScore()));
                level1.setText("Level: " + String.valueOf(gameField.getLevel()));
                name2.setText(fGUI.getName());
                points2.setText("Points: " + String.valueOf(fGUI.getScore()));
                int lvl2 = 0;
                while (fGUI.getScore() > (500 + lvl2*lvl2*1000 - 1))
                    lvl2 += 1;
                level2.setText("Level: " + Integer.toString(lvl2+1));

                //Draw
                fGUI.update(fGUI.getBufferStrategy().getDrawGraphics());
                fGUI.getBufferStrategy().show();
                fGUI.pack();

                //End game
                if (!isGameOver && gameField.getMatrix()[0][0] == 9 && fGUI.getMatrix()[0][0] == 9) {
                    if(gameField.getScore() > fGUI.getScore())
                        JOptionPane.showMessageDialog(null, "Gratulálok! Nyertél!");
                    else if (gameField.getScore() == fGUI.getScore())
                        JOptionPane.showMessageDialog(null, "Döntetlen!");
                    else
                        JOptionPane.showMessageDialog(null, "Vesztettél!");
                    isGameOver = true;
                }

            }
        }, 0, 20);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.cancel();
                timer.purge();
                fGUI.getNet().disconnect();
                fGUI.isConnected = false;
                fGUI.getContentPane().removeAll();
            }
        });

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
                        timer.cancel();
                        timer.purge();
                        fGUI.getNet().disconnect();
                        fGUI.isConnected = false;
                        fGUI.getContentPane().removeAll();
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

    public Timer getTimer() {
        return timer;
    }
}
