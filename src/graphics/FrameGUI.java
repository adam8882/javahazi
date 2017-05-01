package graphics;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Field;
import graphics.frames.LobbyClient;
import graphics.frames.LobbyServer;
import graphics.frames.MainMenu;
import graphics.frames.SinglePlayer;
import network.Control;

public class FrameGUI extends JFrame {
	public DrawArea drawarea = new DrawArea(new Field(0));
	public DrawArea drawarea1 = new DrawArea(new Field(0));
	public DrawArea drawarea2 = new DrawArea(new Field(0));
	Timer timer = new Timer();

	//Network
    Control net;
    Integer [][] net_matrix;
    int net_score;
    int net_seed;
    boolean isClientConnected;
    //Network end
	
	public FrameGUI(Control net) {
		MainMenu mM = new MainMenu(this);
		this.setResizable(false);
		//initMainMenu();
		//initSinglePlayer();

        //Network
        isClientConnected = false;
        this.net = net;
        net_matrix = new Integer[Field.WIDTH][Field.HEIGHT];
        for (int h = 0; h < Field.HEIGHT; h++)
            for (int w = 0; w < Field.WIDTH; w++)
                net_matrix[w][h] = 0;
        net_score = 0;
        net_seed = 0;
        //Network end
	}
	public void initMultiPlayer(boolean isServer) {
        //Network
        if(isServer) {
            net.startServer();
        }
        else {
            net.startClient("127.0.0.1");
            Random rand = new Random();
            net_seed = rand.nextInt();
            net.sendSeed(net_seed);
        }
        //Network end
		Field gameField1 = new Field(net_seed);
		//Field gameField2 = new Field(666);
		JLabel points1 = new JLabel(String.valueOf(gameField1.getScore()));
        //JLabel points2 = new JLabel(String.valueOf(gameField2.getScore()));
        JLabel points2 = new JLabel("0");
		DrawArea drawarea1 = new DrawArea(gameField1);
		//Network
		DrawArea drawarea2 = new DrawArea(this);
		//Network end
        this.add(drawarea1);
        this.add(drawarea2);
        this.add(points1);
        this.add(points2);
        drawarea1.setPreferredSize(new Dimension(300,600));
        drawarea2.setPreferredSize(new Dimension(300,600));
        this.requestFocusInWindow();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	System.out.println("multi");
            	points1.setText(String.valueOf(gameField1.getScore()));
            	//points2.setText(String.valueOf(gameField2.getScore()));

                //Network
                points2.setText(String.valueOf(net_score));
                net.sendMatrix(gameField1.getMatrix());
                net.sendScore(gameField1.getScore());
                //Network end
            }
        }, 0, 100);
        //Billentyű figyelés
        this.requestFocusInWindow();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
       
            	
                switch (e.getKeyCode()) {
                    case (KeyEvent.VK_RIGHT):
                        gameField1.shiftRight();   //shiftRight()
                        break;
                    case (KeyEvent.VK_LEFT):
                        gameField1.shiftLeft();    //shiftLeft()
                        break;
                    case (KeyEvent.VK_UP):
                        gameField1.rotateLeft();;  //rotateRight()
                        break;
                    case (KeyEvent.VK_DOWN):
                        gameField1.drop();   //rotateLeft()
                        break;
                    case (KeyEvent.VK_ESCAPE):
                    	FrameGUI.this.getContentPane().removeAll();
                    	//initMainMenu();
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

	//Network
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
    //Network end
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
    	}
    }

    public Control getNet() {
        return net;
    }

    public void clientConnected() {
        isClientConnected = true;
    }
}
