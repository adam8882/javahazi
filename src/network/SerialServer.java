package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerialServer extends Network {

    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private Control c;

    SerialServer(Control c) {
        super(c);
        this.c = c;
    }

    private class ReceiverThread implements Runnable {

        public void run() {
            try {
                System.out.println("Várakozás a kliensre");
                clientSocket = serverSocket.accept();
                c.connected(true);
                System.out.println("Kliens csatlakozott.");
            } catch (IOException e) {
                System.err.println("Kliens csatlakozása sikertelen.");
                disconnect();
                return;
            }

            try {
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
                out.flush();
            } catch (IOException e) {
                System.err.println("Hiba a streamek lekérésekor.");
                disconnect();
                return;
            }

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
                        case 4: {
                            String name = (String) in.readObject();
                            ctrl.nameReceived(name);
                        }
                        break;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.err.println("Kliens lecsatlakozott!");
                c.clientDisconnected();
            } finally {
                disconnect();
            }
        }
    }

    @Override
    void connect(String ip) {
        disconnect();
        try {
            serverSocket = new ServerSocket(10007);

            Thread rec = new Thread(new ReceiverThread());
            rec.start();
        } catch (IOException e) {
            System.err.println("Nem elérhető a port.");
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
    void sendName(String name) {
        if (out == null)
            return;
        try {
            out.writeByte(4);
            out.writeObject(name);
            out.flush();
        } catch (IOException ex) {
            System.err.println("Név küldés hiba.");
        }
    }

    @Override
    void disconnect() {
        try {
            if (out != null)
                out.close();
            if (in != null)
                in.close();
            if (clientSocket != null)
                clientSocket.close();
            if (serverSocket != null)
                serverSocket.close();
        } catch (IOException ex) {
            System.err.println("Hiba a kapcsolat lezárásakor.");
        }
    }
}
