package network;

import java.awt.Point;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class SerialClient extends Network {

	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;

	SerialClient(Control c) {
		super(c);
	}

	private class ReceiverThread implements Runnable {

		public void run() {
			System.out.println("Waiting for points...");
			try {
				while (true) {
					switch (in.readByte()) {
						case 1: {
							Integer[][] matrix = (Integer[][]) in.readObject();
							ctrl.matrixReceived(matrix);
						} break;
						case 2: {
							int score = (int) in.readObject();
							ctrl.scoreReceived(score);
						}
						break;
						case 3: {
							int seed = (int) in.readObject();
							ctrl.seedReceived(seed);
						}
						break;
					}
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				System.err.println("Server disconnected!");
			} finally {
				disconnect();
			}
		}
	}

	@Override
	void connect(String ip) {
		disconnect();
		try {
			socket = new Socket(ip, 10007);

			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			out.flush();

			Thread rec = new Thread(new ReceiverThread());
			rec.start();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host");
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection. ");
			JOptionPane.showMessageDialog(null, "Cannot connect to server!");
		}
	}

	@Override
	void sendMatrix(Integer[][] matrix) {
		if (out == null)
			return;
		System.out.println("Sending matrix to Server");
		try {
			out.writeByte(1);
			out.writeObject(matrix);
			out.flush();
		} catch (IOException ex) {
			System.err.println("Send error.");
		}
	}

	@Override
	void sendScore(int score) {
		if (out == null)
			return;
		System.out.println("Sending score to Server");
		try {
			out.writeByte(2);
			out.writeObject(score);
			out.flush();
		} catch (IOException ex) {
			System.err.println("Send error.");
		}
	}

	@Override
	boolean sendSeed(int seed) {
		if (out == null)
			return true;
		System.out.println("Sending seed to Client");
		try {
			out.writeByte(3);
			out.writeObject(seed);
			out.flush();
			return false;
		} catch (IOException ex) {
			System.err.println("Send error.");
			return true;
		}
	}

	@Override
	void disconnect() {
		try {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (socket != null)
				socket.close();
		} catch (IOException ex) {
			System.err.println("Error while closing conn.");
		}
	}
}
