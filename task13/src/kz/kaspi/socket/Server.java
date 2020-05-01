package kz.kaspi.socket;

import kz.kaspi.util.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Server class of socket connections
 *
 * Add all connected clients to list and create for them {@link Session}s
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Server {

    private final List<Session> sessions;

    public static final int PORT = 32000;
    public static final String EXIT = "exit";

    private final Logger LOG = Logger.getInstance();

    {
        sessions = new LinkedList<>();
    }

    public void start() {
        try (ServerSocket server = new ServerSocket(PORT)) {

            LOG.info("Server started at port " + PORT);

            while (true) {
                try {
                    Socket socket = server.accept();

                    LOG.info("New client joined");

                    sessions.add(new Session(socket));
                }
                catch (IOException e) {
                    LOG.error("Cannot create session for new client");
                }
            }

        }
        catch (IOException e) {
            LOG.error("Cannot start server at port " + PORT);
        }
    }
}
