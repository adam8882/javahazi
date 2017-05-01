package graphics.frames;
//testtt

import graphics.Dimensions;
import graphics.DrawArea;
import graphics.FrameGUI;
import graphics.frames.SinglePlayer;
import javax.swing.*;
import engine.Field;

import java.awt.Dimension;
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
		JLabel points = new JLabel(String.valueOf(gameField.getScore()));
		fGUI.add(points);
        drawarea.setPreferredSize(new Dimension(10*Dimensions.BLOCK_SIZE,20*Dimensions.BLOCK_SIZE));
        fGUI.requestFocusInWindow();
        fGUI.pack();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	fGUI.update(fGUI.getBufferStrategy().getDrawGraphics());
                //frame.pack();
                fGUI.getBufferStrategy().show();
            	points.setText(String.valueOf(gameField.getScore()));
            }
        }, 0, 1);
        //Billentyű figyelés
        fGUI.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            	System.out.println("lel");
                switch (e.getKeyCode()) {
                    case (KeyEvent.VK_RIGHT):
                        gameField.shiftRight();   	//shiftRight()
                        break;
                    case (KeyEvent.VK_LEFT):
                        gameField.shiftLeft();    	//shiftLeft()
                        break;
                    case (KeyEvent.VK_UP):
                        gameField.rotateLeft();;  	//rotateRight()
                        break;
                    case (KeyEvent.VK_DOWN):
                        gameField.drop();   		//rotateLeft()
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
