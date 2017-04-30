package graphics.frames;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import graphics.FrameGUI;

public class MainMenu {

	public MainMenu (FrameGUI fGUI) {
		JButton singlePlayerButton = new JButton("Single player");
		JButton multiPlayerServerButton = new JButton("Multi player server");
        JButton multiPlayerClientButton = new JButton("Multi player client");
		JButton helpButton = new JButton("Help");
		JButton aboutButton = new JButton("About");
		JButton exitButton = new JButton("Exit");
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
		
		p1.setPreferredSize(new Dimension(600,120));
		p2.setPreferredSize(new Dimension(600,120));
		p3.setPreferredSize(new Dimension(600,120));
		p4.setPreferredSize(new Dimension(600,120));
		p5.setPreferredSize(new Dimension(600,120));
        p6.setPreferredSize(new Dimension(600,120));
		
		p1.add(singlePlayerButton);
		p2.add(multiPlayerServerButton);
		p3.add(multiPlayerClientButton);
		p4.add(helpButton);
		p5.add(aboutButton);
		p6.add(exitButton);
        
		
		fGUI.setLayout(new GridLayout(6, 1));
		fGUI.add(p1);
		fGUI.add(p2);
		fGUI.add(p3);
		fGUI.add(p4);
		fGUI.add(p5);
		fGUI.add(p6);
		
		fGUI.pack();
		
		singlePlayerButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  fGUI.getContentPane().removeAll();
				  fGUI.setLayout(new GridLayout(1, 1));
				  fGUI.setActualFrame(1);
				  //initSinglePlayer();
			  } 
		} );
		multiPlayerServerButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  fGUI.getContentPane().removeAll();
				  fGUI.setLayout(new GridLayout(2, 2));
				  fGUI.setActualFrame(2);
				  //initMultiPlayer(true);
			  } 
		} );
        multiPlayerClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	fGUI.getContentPane().removeAll();
            	fGUI.setLayout(new GridLayout(2, 2));
            	fGUI.setActualFrame(3);
                //initMultiPlayer(false);
            }
        } );
	}
}
