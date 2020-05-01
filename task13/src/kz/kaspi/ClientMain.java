package kz.kaspi;

import kz.kaspi.socket.Client;

public class ClientMain {

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
