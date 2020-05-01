package kz.kaspi;

import kz.kaspi.socket.Server;

public class ServerMain {

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
