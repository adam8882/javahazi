/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.awt.Point;

/**
 *
 * @author Predi
 */
abstract class Network {

	protected Control ctrl;

	Network(Control c) {
		ctrl = c;
	}

	abstract boolean connect(String ip);

	abstract void disconnect();

	abstract void sendMatrix(Integer[][] matrix);

	abstract void sendScore(int score);

	abstract boolean sendSeed(int seed);
}
