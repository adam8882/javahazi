/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package network;


import engine.Field;
import graphics.FrameGUI;

import javax.swing.*;

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

	//Nem biztos hogy kell
	public FrameGUI getGUI() {
		return this.gui;
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

	public void  sendScore(int score) {
		if (net == null)
			return;
		net.sendScore(score);
	}

	public void sendSeed(int seed) {
		if (net == null)
			return;
		net.sendSeed(seed);
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

	public void connected(boolean bool){
		gui.connected(bool);
	}

	public void serverDisconnected() {
		gui.serverDisconnected();
	}

	public void serverNotAvailable() {
		gui.serverNotAvailable();
	}

	public void clientConnected() {
		gui.clientConnected();
	}

	public void clientDisconnected() {
		gui.clientDisconnected();
	}
}
