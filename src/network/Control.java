/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package network;


import engine.Field;
import graphics.FrameGUI;

/**
 *
 * @author Predi
 */
public class Control {

	private FrameGUI gui;
	private Network net = null;

	public Control() {
	}

	public void setGUI(FrameGUI g) {
		gui = g;
	}

	public void startServer() {
		if (net != null)
			net.disconnect();
		net = new SerialServer(this);
		net.connect("localhost");
	}

	public void startClient(String IP) {
		if (net != null)
			net.disconnect();
		net = new SerialClient(this);
		net.connect(IP);
	}

	public void sendMatrix(Integer[][] matrix) {
		if (net == null)
			return;
		Integer [][] matrix2 = new Integer[Field.WIDTH][Field.HEIGHT];
		for (int h = 0; h < Field.HEIGHT; h++)
			for (int w = 0; w < Field.WIDTH; w++)
				matrix2[w][h] = matrix[w][h];
		net.sendMatrix(matrix2);
	}

	public void sendScore(int score) {
		if (net == null)
			return;
		net.sendScore(score);
	}

	public boolean sendSeed(int seed) {
		if (net == null)
			return true;
		return net.sendSeed(seed);
	}

	public void matrixReceived(Integer[][] matrix) {
		if (gui == null)
			return;
		gui.setMatrix(matrix);
	}

	public void scoreReceived(int score) {
		if (gui == null)
			return;
		gui.setScore(score);
	}

	public void seedReceived(int seed) {
		if (gui == null)
			return;
		gui.setNetSeed(seed);
	}
}
