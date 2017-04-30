package graphics.frames;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import graphics.FrameGUI;

public class LobbyClient {

	public LobbyClient(FrameGUI fGUI) {
		/*JLabel nameLabel = new JLabel("Name: ");
		JLabel ipLabel = new JLabel("IP Adress: ");
		
		JTextField nameField = new JTextField();
		JTextField ipField = new JTextField();
		
		JButton connect = new JButton("Connect");
		
	    JPanel labelPanel = new JPanel(new GridLayout(2, 1));
	    JPanel fieldPanel = new JPanel(new GridLayout(2, 1));
	    JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    
	    JPanel panel = new JPanel(new BorderLayout());
	    panel.add(labelPanel, BorderLayout.WEST);
	    panel.add(fieldPanel, BorderLayout.CENTER);
	    labelPanel.add(nameLabel);
	    labelPanel.add(ipLabel);
	    p.add(nameField);
	    p.add(ipField);
	    fieldPanel.add(nameField);
	    fieldPanel.add(p);
		fGUI.add(panel);
		//fGUI.pack();
		System.out.println("sss");*/
		//fGUI.setSize(new Dimension(600,400));
		fGUI.setTitle("Multiplayer client lobby");
		
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
        backButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	fGUI.getContentPane().removeAll();
            	fGUI.setActualFrame(7);
            }		
        });
	}

}
