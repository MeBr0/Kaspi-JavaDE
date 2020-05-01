package kz.kaspi.socket;

import kz.kaspi.util.Logger;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client class for socket connections
 *
 * Connect to server and send message there
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Client {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final Logger LOG = Logger.getInstance();

    public void start() {
        try (Socket socket = new Socket("localhost", Server.PORT)) {
            LOG.info("Connected to server at port " + Server.PORT);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                while (true) {
                    String message = this.reader.readLine();
                    writer.write(message + "\n");
                    writer.flush();

                    if (Server.EXIT.equals(message)) {
                        break;
                    }

                    LOG.info(reader.readLine());
                }

                LOG.info("Exited from session");
            }
            catch (IOException e) {
                LOG.error("Cannot read or write to server");
            }
        }
        catch (UnknownHostException e) {
            LOG.error("Unknown host");
        }
        catch (IOException e) {
            LOG.error("Cannot connect to server at port " + Server.PORT);
        }
    }
}
