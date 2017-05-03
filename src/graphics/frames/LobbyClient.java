package graphics.frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import graphics.FrameGUI;

public class LobbyClient {

	public LobbyClient(FrameGUI fGUI) {
		fGUI.setTitle("Multiplayer client lobby");
		
	    JPanel labelPanel = new JPanel(new GridLayout(2, 1));
	    JPanel fieldPanel = new JPanel(new GridLayout(2, 1));
	    JPanel p1 = new JPanel(new GridLayout(1, 2));
	    JPanel p2 = new JPanel(new GridLayout(2, 1));
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		nameLabel.setFont(new Font("Serif", Font.BOLD, 30));
		
		JLabel ipLabel = new JLabel("IP Address: ");
		ipLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		ipLabel.setFont(new Font("Serif", Font.BOLD, 30));
		
		JTextField nameField = new JTextField();
		nameField.setText("Client");
		nameField.setFont(new Font("Serif", Font.PLAIN, 30));
		
		JTextField ipField = new JTextField();
		ipField.setFont(new Font("Serif", Font.PLAIN, 30));
		ipField.setText("127.0.0.1");
		
		JButton conButton = new JButton("Connect game");
		conButton.setFont(new Font("Serif", Font.BOLD, 30));
		
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
		p2.add(conButton);
		p2.add(backButton);
		fGUI.add(p1);
		fGUI.add(p2);
		fGUI.pack();
		fGUI.requestFocusInWindow();

		conButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fGUI.getNet().startClient("localhost");
				if(fGUI.isConnected)
				{
					Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							if(fGUI.getSeed() != 0) {
								timer.cancel();
								fGUI.setActualFrame(8);
							}
						}
					}, 0, 20);
				}
			}
		});

        backButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	fGUI.setActualFrame(7);
            }		
        });
	}

}
