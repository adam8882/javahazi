import engine.Field;

import graphics.DrawArea;
import graphics.FrameGUI;
import graphics.frames.MainMenu;
import graphics.frames.SinglePlayer;
import network.Control;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

/**
 * Created by Adam on 2017. 02. 26..
 */
/*
    Field osztály tagfüggvényei (engine.Field):
    getMatrix()         visszadja a játéktér Integer[][] mátrixot
                        játéktér bal fölső sarka a (0;0)
                        játéktér szélessége: engine.Field.WIDTH (ez a legnagyobb x koordináta indexe, tehát 1-el kisebb mint a szélesség, mert 0-ról indul)
                        játéktér magassága: engine.Field.HEIGHT (ez a legnagyobb y koordináta indexe, tehát 1-el kisebb mint a magasság, mert 0-ról indul)
                        ha egy cella 0 akkor nincs ott elem, egyébként a szám a formát jelöli (1-7-ig), ez használható akár színhez is
    shiftRight()        jobra mozgatás (ellenőrzi, hogy lehetséges-e)
    shiftLeft()         balra mozgatás (ellenőrzi, hogy lehetséges-e)
    rotateRight()       jobbra forgatás (ellenőrzi, hogy lehetséges-e)
    rotateLeft()        balra forgatás (ellenőrzi, hogy lehetséges-e)
    drop()              leejtés
    isGameOver()        (boolean) true, ha betelt a képernyő
    getScore()          pontszám lekérése (int-el tér vissza)

*/

public class Main {
    public static Field gameField1 = new Field(666);   //Field példányosítás
    public static Field gameField2 = new Field(666);
    public static JFrame frame;
    public static void main(String[] args) throws AWTException {

        //Network
        Control c = new Control();
        FrameGUI fGUI = new FrameGUI(c);
        c.setGUI(fGUI);
        //Network end

    	JFrame frame = fGUI;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.createBufferStrategy(2);
        BufferStrategy strategy = frame.getBufferStrategy();
    	//
        // while(true) {
    		//strategy = frame.getBufferStrategy();
        	////frame.update(frame.getBufferStrategy().getDrawGraphics());
            //frame.pack();
            ////frame.getBufferStrategy().show();
    	//}
    }
}