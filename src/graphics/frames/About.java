package graphics.frames;

import graphics.FrameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by knagymate on 2017. 05. 06..
 */
public class About {
    public About(FrameGUI fGUI) {
        JPanel aboutPanel = new JPanel();
        aboutPanel.setLayout(new BoxLayout(aboutPanel,BoxLayout.Y_AXIS));

        //Level label
        JLabel t1 = new JLabel("Tetris");
        t1.setFont(new Font("Serif", Font.PLAIN, 50));
        t1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel t2 = new JLabel("Beágyazott rendszerek szoftvertechnológiája");
        t2.setFont(new Font("Serif", Font.PLAIN, 30));
        t2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel t3 = new JLabel("Projektfeladat");
        t3.setFont(new Font("Serif", Font.PLAIN, 30));
        t3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel t4 = new JLabel("2017");
        t4.setFont(new Font("Serif", Font.PLAIN, 30));
        t4.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel t5 = new JLabel(" ");
        t5.setFont(new Font("Serif", Font.PLAIN, 30));
        t5.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel t6 = new JLabel(" ");
        t6.setFont(new Font("Serif", Font.PLAIN, 30));
        t6.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel n1 = new JLabel("Nárai Ádám (Engine)");
        n1.setFont(new Font("Serif", Font.PLAIN, 30));
        n1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel n2 = new JLabel("Kovács-Nagy Máté (Graphics)");
        n2.setFont(new Font("Serif", Font.PLAIN, 30));
        n2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel n3 = new JLabel("Virágh Eszter (Network)");
        n3.setFont(new Font("Serif", Font.PLAIN, 30));
        n3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton exitButton = new JButton("Back");
        exitButton.setFont(new Font("Serif", Font.PLAIN, 30));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        aboutPanel.add(t1);
        aboutPanel.add(t2);
        aboutPanel.add(t3);
        aboutPanel.add(t4);
        aboutPanel.add(t5);
        aboutPanel.add(n1);
        aboutPanel.add(n2);
        aboutPanel.add(n3);
        aboutPanel.add(t6);
        aboutPanel.add(exitButton);
        aboutPanel.setPreferredSize(new Dimension(600,450));

        fGUI.add(aboutPanel);
        fGUI.pack();

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fGUI.setActualFrame(7);
            }
        });
    }
}
