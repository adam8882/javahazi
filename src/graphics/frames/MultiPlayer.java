package graphics.frames;

import engine.Field;
import graphics.Dimensions;
import graphics.DrawArea;
import graphics.FrameGUI;
import network.Control;

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
        timer = new Timer();
        fGUI.setLayout(new FlowLayout(FlowLayout.RIGHT));
        BufferStrategy strategy = fGUI.getBufferStrategy();
        Random rand = new Random();
        int seed = rand.nextInt(1000000);
        Field gameField = new Field(seed);
        DrawArea drawarea1 = new DrawArea(gameField);
        DrawArea drawarea2 = new DrawArea(fGUI);
        drawarea2.setBlockSize(Dimensions.BLOCK_SIZE_3);

        //Points label
        JLabel points1 = new JLabel("Points: " + String.valueOf(gameField.getScore()));
        points1.setFont(new Font("Serif", Font.PLAIN, 30));
        points1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel points2 = new JLabel("Points: " + String.valueOf(gameField.getScore()));
        points2.setFont(new Font("Serif", Font.PLAIN, 20));
        points2.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Level label
        JLabel level1 = new JLabel("Level: " + String.valueOf(gameField.getLevel()));
        level1.setFont(new Font("Serif", Font.PLAIN, 30));
        level1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel level2 = new JLabel("Level: " + String.valueOf(gameField.getLevel()));
        level2.setFont(new Font("Serif", Font.PLAIN, 20));
        level2.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Name label
        JLabel name2 = new JLabel("Name2");
        name2.setFont(new Font("Serif", Font.PLAIN, 30));
        name2.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Back button
        JButton exitButton = new JButton("Back");
        exitButton.setFont(new Font("Serif", Font.PLAIN, 30));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //resultPanel
        JPanel resultsPanel1 = new JPanel();
        resultsPanel1.setLayout(new BoxLayout(resultsPanel1,BoxLayout.Y_AXIS));
        resultsPanel1.add(Box.createRigidArea(new Dimension(100, 100)));
        resultsPanel1.add(points1);
        resultsPanel1.add(Box.createRigidArea(new Dimension(100, 100)));
        resultsPanel1.add(level1);
        resultsPanel1.add(Box.createRigidArea(new Dimension(100, 100)));
        resultsPanel1.add(exitButton);

        JPanel resultsPanel2 = new JPanel();
        resultsPanel2.setLayout(new BoxLayout(resultsPanel2,BoxLayout.Y_AXIS));
        resultsPanel2.add(name2);
        resultsPanel2.add(drawarea2);
        resultsPanel2.add(points2);
        resultsPanel2.add(level2);
        //resultsPanel2.setBorder(BorderFactory.createLineBorder(Color.black));

        //Frame
        fGUI.add(drawarea1);
        fGUI.add(resultsPanel1);
        fGUI.add(resultsPanel2);

        drawarea1.setPreferredSize(new Dimension(10 * Dimensions.BLOCK_SIZE + 1, 20 * Dimensions.BLOCK_SIZE + 1));
        drawarea2.setPreferredSize(new Dimension(10 * Dimensions.BLOCK_SIZE_3 + 1, 20 * Dimensions.BLOCK_SIZE_3 + 1));
        resultsPanel1.setPreferredSize((new Dimension(200,600)));
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                points1.setText("Points: " + String.valueOf(gameField.getScore()));
                level1.setText("Level: " + String.valueOf(gameField.getLevel()));
                name2.setText(fGUI.getName());
                points2.setText("Points: " + String.valueOf(fGUI.getScore()));
                int lvl2 = 0;
                while (fGUI.getScore() > (500 + lvl2*lvl2*1000 - 1))
                    lvl2 += 1;
                level2.setText("Level: " + Integer.toString(lvl2));
                if(fGUI.isConnected) {
                    fGUI.getNet().sendMatrix(gameField.getMatrix());
                    fGUI.getNet().sendScore(gameField.getScore());
                    //fGUI.getNet().sendName("sada");
                }

                fGUI.update(fGUI.getBufferStrategy().getDrawGraphics());
                fGUI.getBufferStrategy().show();
                fGUI.pack();
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
