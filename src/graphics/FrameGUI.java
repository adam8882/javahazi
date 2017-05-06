package graphics;

import engine.Field;
import graphics.frames.*;
import network.Control;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;

public class FrameGUI extends JFrame {
    Control net;
    Integer[][] net_matrix;
    int net_score;
    int net_seed;
    String net_name;
    public boolean isConnected;
    MultiPlayer multi;
    LobbyClient lobby_c;
    LobbyServer lobby_s;

    public FrameGUI() {
        this.net=new Control();
        this.net.setGUI(this);
        new MainMenu(this);
        this.setTitle("Main Menu");
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
        killThemAll();
        switch (actualFrame) {
            case 1:
                this.setTitle("Singleplayer");
                new SinglePlayer(this);
                break;
            case 2:
                this.setTitle("Lobby (Server)");
                this.setLayout(new GridLayout(2, 1));
                lobby_s = new LobbyServer(this);
                break;
            case 3:
                this.setTitle("Lobby (Client)");
                this.setLayout(new GridLayout(2, 1));
                lobby_c = new LobbyClient(this);
                break;
            case 4:
                this.setTitle("About");
                this.setLayout(new GridLayout(1, 1));
                new About(this);
                break;
            case 7:
                this.setTitle("Main Menu");
                new MainMenu(this);
                break;
            case 8:
                this.setTitle("Multiplayer");
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

    public void setName(String net_name) {
        this.net_name = net_name;
    }

    public String getName() {
        return net_name;
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
        killThemAll();
        JOptionPane.showMessageDialog(null, "A kapcsolat megszakadt.");
        setActualFrame(7);
    }

    public void serverNotAvailable() {
        JOptionPane.showMessageDialog(null, "A szerver nem elérhető!");
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
        killThemAll();
        JOptionPane.showMessageDialog(null, "A kapcsolat megszakadt.");
        setActualFrame(7);
    }

    public void killThemAll() {
        if (multi != null) {
            if (multi.getTimer() != null){
                multi.getTimer().cancel();
                multi.getTimer().purge();
            }
        }
        if (lobby_c != null){
            if(lobby_c.getTimer()!=null) {
                lobby_c.getTimer().cancel();
                lobby_c.getTimer().purge();
            }
        }
        if (lobby_s != null){
            if(lobby_s.getTimer()!=null) {
                lobby_s.getTimer().cancel();
                lobby_s.getTimer().purge();
            }
        }
    }
}
