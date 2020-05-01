package kz.kaspi.socket;

import kz.kaspi.util.Logger;

import java.io.*;
import java.net.Socket;

/**
 * Auxiliary class for {@link Server}
 *
 * Serve for one client, echo message received
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Session extends Thread {

    private final BufferedReader reader;
    private final BufferedWriter writer;

    private final Logger LOG = Logger.getInstance();

    public Session(Socket socket) throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = reader.readLine();

                if (Server.EXIT.equals(message)) {
                    LOG.info("Exited from session");
                    send("exited");
                    break;
                }

                String result = "Message " + message + " acknowledged";
                LOG.info(result);

                send(result);
            }
        }
        catch (IOException e) {
            LOG.error("Cannot read message from client");
        }
    }

    private void send(String message) {
        try {
            writer.write(message + '\n');
            writer.flush();
        }
        catch (IOException e) {
            LOG.error("Cannot send message to client");
        }
    }
}
