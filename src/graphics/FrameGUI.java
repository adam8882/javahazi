package graphics;

import engine.Field;
import graphics.frames.*;
import network.Control;

import javax.swing.*;

public class FrameGUI extends JFrame {
    Control net;
    Integer [][] net_matrix;
    public int net_score;
    public int net_seed;
    public boolean isConnected;
	
	public FrameGUI(Control net) {
		new MainMenu(this);
		this.setResizable(false);

		isConnected = false;
        this.net = net;
        net_matrix = new Integer[Field.WIDTH][Field.HEIGHT];
        for (int h = 0; h < Field.HEIGHT; h++)
            for (int w = 0; w < Field.WIDTH; w++)
                net_matrix[w][h] = 0;
        net_score = 0;
        net_seed = 0;
	}

    public void setMatrix(Integer [][] net_matrix) {
	    this.net_matrix = net_matrix;
    }

    public void setScore(int net_score) {
        this.net_score = net_score;
    }

    public void setNetSeed(int seed) {
        this.net_seed = seed;
    }

    public Integer[][] getMatrix() {
        return net_matrix;
    }

    public void setActualFrame(int actualFrame) {
    	switch (actualFrame) {
            case 1:	SinglePlayer sp = new SinglePlayer(this);
                break;
            case 2: LobbyServer ls = new LobbyServer(this);
    			break;
            case 3: LobbyClient lc = new LobbyClient(this);
    			break;
            case 4:
    			break;
            case 5:
    			break;
            case 6:
    			break;
            case 7: MainMenu mm = new MainMenu(this);
    			break;
            case 8: MultiPlayer mp = new MultiPlayer(this);
                break;

    	}
    }

    public Control getNet() {
        return net;
    }

    public void connected(boolean bool) {
        isConnected = bool;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void serverDisconnected() {
        JOptionPane.showMessageDialog(null, "A szerver lecsatlakozott!");
    }

    public void serverNotAvailable() {
        JOptionPane.showMessageDialog(null, "A szerver nem elérhető!");
    }

    public void clientConnected() {
        //JOptionPane.showMessageDialog(null, "Kliens csatlakozott.");
    }

    public void clientDisconnected() {
        JOptionPane.showMessageDialog(null, "Kliens lecsatlakozott.");
    }
}
