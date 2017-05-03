package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SerialClient extends Network {

    private Socket socket = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private Control c;

    SerialClient(Control c) {
        super(c);
        this.c = c;
    }

    private class ReceiverThread implements Runnable {

        public void run() {
            try {
                while (true) {
                    switch (in.readByte()) {
                        case 1: {
                            Integer[][] matrix = (Integer[][]) in.readObject();
                            ctrl.matrixReceived(matrix);
                        }
                        break;
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
                System.err.println("Szerver lecsatlakozott!");
                c.serverDisconnected();
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
            ctrl.connected(true);
        } catch (UnknownHostException e) {
            System.err.println("Host nem található.");
            c.serverNotAvailable();
        } catch (IOException e) {
            System.err.println("Szerver nem elérhető!");
            c.serverNotAvailable();
        }
    }

    @Override
    void sendMatrix(Integer[][] matrix) {
        if (out == null)
            return;
        try {
            out.writeByte(1);
            out.writeObject(matrix);
            out.flush();
        } catch (IOException ex) {
            System.err.println("Mátrix küldés hiba.");
        }
    }

    @Override
    void sendScore(int score) {
        if (out == null)
            return;
        try {
            out.writeByte(2);
            out.writeObject(score);
            out.flush();
        } catch (IOException ex) {
            System.err.println("Score küldés hiba.");
        }
    }

    @Override
    void sendSeed(int seed) {
        if (out == null)
            return;
        try {
            out.writeByte(3);
            out.writeObject(seed);
            out.flush();
        } catch (IOException ex) {
            System.err.println("Seed küldés hiba.");
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
            System.err.println("Hiba a kapcsolat lezárásakor.");
        }
    }
}
