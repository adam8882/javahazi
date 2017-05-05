package network;

abstract class Network {

    protected Control ctrl;

    Network(Control c) {
        ctrl = c;
    }

    abstract void connect(String ip);

    abstract void disconnect();

    abstract void sendMatrix(Integer[][] matrix);

    abstract void sendScore(int score);

    abstract void sendSeed(int seed);

    abstract void sendName(String name);
}
