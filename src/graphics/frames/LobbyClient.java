package graphics.frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import graphics.*;

public class LobbyClient {

    Timer timer;
    public LobbyClient(FrameGUI fGUI) {
        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        JPanel fieldPanel = new JPanel(new GridLayout(2, 1));
        JPanel p1 = new JPanel(new GridLayout(1, 2));
        JPanel p2 = new JPanel(new GridLayout(2, 1));

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        nameLabel.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel ipLabel = new JLabel("IP Address: ");
        ipLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ipLabel.setFont(new Font("Serif", Font.BOLD, 30));

        JTextField nameField = new JTextField();
        nameField.setText("Player (Client)");
        nameField.setFont(new Font("Serif", Font.PLAIN, 30));

        JTextField ipField = new JTextField();
        ipField.setFont(new Font("Serif", Font.PLAIN, 30));
        ipField.setText("127.0.0.1");

        JButton conButton = new JButton("Connect game");
        conButton.setFont(new Font("Serif", Font.BOLD, 30));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Serif", Font.BOLD, 30));


        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        p2.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
        p2.setPreferredSize(new Dimension(600, 100));
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
                String ip = ipField.getText();
                if(ip!=null) {
                    fGUI.getNet().startClient(ip);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Üres IP mező!");
                }
                if(timer != null) {
                    timer.cancel();
                    timer.purge();
                }

                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if (fGUI.isConnected) {
                            if (fGUI.getSeed() != 0) {
                                timer.cancel();
                                timer.purge();
                                String net_name = nameField.getText();
                                fGUI.getNet().sendName(net_name);
                                fGUI.setActualFrame(8);
                            }
                        }
                    }
                }, 0, 20);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fGUI.getNet().disconnect();
                if(timer!=null) {
                    timer.cancel();
                    timer.purge();
                }
                fGUI.setActualFrame(7);
            }
        });
    }
    public Timer getTimer() {
        return timer;
    }
}
