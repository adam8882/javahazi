package graphics;

import engine.Field;
import graphics.frames.*;
import network.Control;

import javax.swing.*;

public class FrameGUI extends JFrame {
    Control net;
    Integer[][] net_matrix;
    int net_score;
    int net_seed;
    public boolean isConnected;
    MultiPlayer multi;

    public FrameGUI() {
        this.net=new Control();
        this.net.setGUI(this);
        new MainMenu(this);
        this.setResizable(false);

        isConnected = false;
        net_matrix = new Integer[Field.WIDTH][Field.HEIGHT];
        for (int h = 0; h < Field.HEIGHT; h++)
            for (int w = 0; w < Field.WIDTH; w++)
                net_matrix[w][h] = 0;
        net_score = 0;
        net_seed = 0;
    }

    public void setActualFrame(int actualFrame) {
        this.getContentPane().removeAll();
        switch (actualFrame) {
            case 1:
                new SinglePlayer(this);
                break;
            case 2:
                new LobbyServer(this);
                break;
            case 3:
                new LobbyClient(this);
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                new MainMenu(this);
                break;
            case 8:
                multi = new MultiPlayer(this);
                break;

        }
    }

    public void setMatrix(Integer[][] net_matrix) {
        this.net_matrix = net_matrix;
    }

    public void setScore(int net_score) {
        this.net_score = net_score;
    }

    public int getScore() {
        return net_score;
    }

    public int getSeed() {
        return net_seed;
    }

    public void setNetSeed(int seed) {
        this.net_seed = seed;
    }

    public Integer[][] getMatrix() {
        return net_matrix;
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
        isConnected = false;
        net.disconnect();
        net_matrix = new Integer[Field.WIDTH][Field.HEIGHT];
        for (int h = 0; h < Field.HEIGHT; h++)
            for (int w = 0; w < Field.WIDTH; w++)
                net_matrix[w][h] = 0;
        net_score = 0;
        net_seed = 0;
        //JOptionPane.showMessageDialog(null, "A szerver lecsatlakozott!");
        multi.getTimer().cancel();
        multi.getTimer().purge();
        setActualFrame(7);
    }

    public void serverNotAvailable() {
        //JOptionPane.showMessageDialog(null, "A szerver nem elérhető!");
    }

    public void clientConnected() {
        //JOptionPane.showMessageDialog(null, "Kliens csatlakozott.");
    }

    public void clientDisconnected() {
        isConnected = false;
        net.disconnect();
        net_matrix = new Integer[Field.WIDTH][Field.HEIGHT];
        for (int h = 0; h < Field.HEIGHT; h++)
            for (int w = 0; w < Field.WIDTH; w++)
                net_matrix[w][h] = 0;
        net_score = 0;
        net_seed = 0;
        //JOptionPane.showMessageDialog(null, "Kliens lecsatlakozott.");
        multi.getTimer().cancel();
        multi.getTimer().purge();
        setActualFrame(7);
    }
}
