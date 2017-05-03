import graphics.FrameGUI;
import network.Control;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws AWTException {
        Control c = new Control();
        FrameGUI fGUI = new FrameGUI(c);

        c.setGUI(fGUI);
        fGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fGUI.setVisible(true);
        fGUI.createBufferStrategy(2);
    }
}