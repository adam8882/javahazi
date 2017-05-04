package graphics.frames;

import graphics.DrawText;
import graphics.Fonts;
import graphics.FrameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainMenu {

	public MainMenu (FrameGUI fGUI) {
		ArrayList<Object[][]> sp1 = new ArrayList<>();
		ArrayList<Object[][]> sp2 = new ArrayList<>();
		ArrayList<Object[][]> sp3 = new ArrayList<>();
		ArrayList<Object[][]> sp4 = new ArrayList<>();
		ArrayList<Object[][]> sp5 = new ArrayList<>();
		ArrayList<Object[][]> sp6 = new ArrayList<>();
		sp1.add(Fonts.S);
		sp1.add(Fonts.I);
		sp1.add(Fonts.N);
		sp1.add(Fonts.G);
		sp1.add(Fonts.L);
		sp1.add(Fonts.E);
		sp1.add(Fonts.P);
		sp1.add(Fonts.L);
		sp1.add(Fonts.A);
		sp1.add(Fonts.Y);
		sp1.add(Fonts.E);
		sp1.add(Fonts.R);

		sp2.add(Fonts.S);
		sp2.add(Fonts.E);
		sp2.add(Fonts.R);
		sp2.add(Fonts.V);
		sp2.add(Fonts.E);
		sp2.add(Fonts.R);

		sp3.add(Fonts.C);
		sp3.add(Fonts.L);
		sp3.add(Fonts.I);
		sp3.add(Fonts.E);
		sp3.add(Fonts.N);
		sp3.add(Fonts.T);

		sp4.add(Fonts.H);
		sp4.add(Fonts.E);
		sp4.add(Fonts.L);
		sp4.add(Fonts.P);

		sp5.add(Fonts.A);
		sp5.add(Fonts.B);
		sp5.add(Fonts.O);
		sp5.add(Fonts.U);
		sp5.add(Fonts.T);

		sp6.add(Fonts.E);
		sp6.add(Fonts.X);
		sp6.add(Fonts.I);
		sp6.add(Fonts.T);

		DrawText p1 = new DrawText(sp1);
		DrawText p2 = new DrawText(sp2);
		DrawText p3 = new DrawText(sp3);
		DrawText p4 = new DrawText(sp4);
		DrawText p5 = new DrawText(sp5);
		DrawText p6 = new DrawText(sp6);

		p1.setPreferredSize(new Dimension(600,120));
		p2.setPreferredSize(new Dimension(600,120));
		p3.setPreferredSize(new Dimension(600,120));
		p4.setPreferredSize(new Dimension(600,120));
		p5.setPreferredSize(new Dimension(600,120));
		p6.setPreferredSize(new Dimension(600,120));

		fGUI.setLayout(new GridLayout(6, 1));
		fGUI.add(p1);
		fGUI.add(p2);
		fGUI.add(p3);
		fGUI.add(p4);
		fGUI.add(p5);
		fGUI.add(p6);

		fGUI.pack();

		p1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fGUI.getContentPane().removeAll();
				fGUI.setLayout(new GridLayout(1, 1));
				fGUI.setActualFrame(1);
			}
		});
		/*
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
			  } 
		} );
		multiPlayerServerButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  fGUI.getContentPane().removeAll();
				  fGUI.setLayout(new GridLayout(2, 2));
				  fGUI.setActualFrame(2);
			  } 
		} );
        multiPlayerClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	fGUI.getContentPane().removeAll();
            	fGUI.setLayout(new GridLayout(2, 2));
            	fGUI.setActualFrame(3);
            }
        } );
        */
	}
}
