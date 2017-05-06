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

		sp4.add(Fonts.A);
		sp4.add(Fonts.B);
		sp4.add(Fonts.O);
		sp4.add(Fonts.U);
		sp4.add(Fonts.T);

		sp5.add(Fonts.E);
		sp5.add(Fonts.X);
		sp5.add(Fonts.I);
		sp5.add(Fonts.T);

		DrawText p1 = new DrawText(sp1);
		DrawText p2 = new DrawText(sp2);
		DrawText p3 = new DrawText(sp3);
		DrawText p4 = new DrawText(sp4);
		DrawText p5 = new DrawText(sp5);

		p1.setPreferredSize(new Dimension(600,144));
		p2.setPreferredSize(new Dimension(600,144));
		p3.setPreferredSize(new Dimension(600,144));
		p4.setPreferredSize(new Dimension(600,144));
		p5.setPreferredSize(new Dimension(600,144));
		fGUI.setLayout(new GridLayout(5, 1));
		fGUI.add(p1);
		fGUI.add(p2);
		fGUI.add(p3);
		fGUI.add(p4);
		fGUI.add(p5);

		fGUI.pack();

		p1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fGUI.setActualFrame(1);
			}
		});

		p2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fGUI.setActualFrame(2);
			}
		});

		p3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fGUI.setActualFrame(3);
			}
		});

		p4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fGUI.setActualFrame(4);
			}
		});

		p5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fGUI.dispose();
			}
		});
	}
}
