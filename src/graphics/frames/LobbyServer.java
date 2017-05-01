package graphics.frames;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import graphics.FrameGUI;

public class LobbyServer {

	public LobbyServer(FrameGUI fGUI) {
		fGUI.setTitle("Multiplayer server lobby");
		String ipAdress = "";
    	try {
    		ipAdress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			ipAdress = "Nincs IP";
			e1.printStackTrace();
		}
		
	    JPanel labelPanel = new JPanel(new GridLayout(2, 1));
	    JPanel fieldPanel = new JPanel(new GridLayout(2, 1));
	    JPanel p1 = new JPanel(new GridLayout(1, 2));
	    JPanel p2 = new JPanel(new GridLayout(2, 1));
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		nameLabel.setFont(new Font("Serif", Font.BOLD, 30));
		
		JLabel ipLabel = new JLabel("IP Adress: ");
		ipLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		ipLabel.setFont(new Font("Serif", Font.BOLD, 30));
		
		JTextField nameField = new JTextField();
		nameField.setText("Host");
		nameField.setFont(new Font("Serif", Font.PLAIN, 30));
		
		JTextField ipField = new JTextField();
		ipField.setText(ipAdress);
		ipField.setFont(new Font("Serif", Font.PLAIN, 30));
    	try {
    		ipField.setText(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			ipField.setText("Nincs IP");
			e1.printStackTrace();
		}
		
		JButton hostButton = new JButton("Host game");
		hostButton.setFont(new Font("Serif", Font.BOLD, 30));
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Serif", Font.BOLD, 30));
		
		
		labelPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		fieldPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		p2.setBorder(BorderFactory.createEmptyBorder(30,20,20,20));
		p2.setPreferredSize(new Dimension(600,100));
		labelPanel.add(nameLabel);
		labelPanel.add(ipLabel);
		fieldPanel.add(nameField);
		fieldPanel.add(ipField);
		p1.add(labelPanel);
		p1.add(fieldPanel);
		p2.add(hostButton);
		p2.add(backButton);
		fGUI.add(p1);
		fGUI.add(p2);
		fGUI.pack();
		
        hostButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				fGUI.getNet().startServer();
				hostButton.setEnabled(false);
				Timer timer = new Timer();
		        timer.scheduleAtFixedRate(new TimerTask() {
		            @Override
		            public void run() {
		            	if(fGUI.isConnected()) {
							timer.cancel();
							Random rand = new Random();
							int net_seed = rand.nextInt(10000)+1;
							fGUI.setNetSeed(net_seed);
							fGUI.getContentPane().removeAll();
		            		fGUI.setActualFrame(8);
		            	}
		            }
		        }, 0, 20);
            }		
        });
        
        backButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	fGUI.getContentPane().removeAll();
            	fGUI.setActualFrame(7);
            }
        });
	}

}
