package kz.kaspi.util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger class
 * Use {@link java.time} for logging
 *
 * @author A.Yergali
 * @version 2.0
 */
public class Logger {

    private static Logger logger = null;

    private static final String PREFIX = "logs/";
    private static final String FILE = "log.txt";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Logger() {

    }

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }

        return logger;
    }

    public void info(String message) {
        message = now() + " [INFO] " + message;

        writeToFile(message);
        System.out.println(message);
    }

    public void error(String message) {
        message = now() + " [ERROR] " + message;

        writeToFile(message);
        System.err.println(message);
    }

    private void writeToFile(String message) {
        try (FileWriter writer = new FileWriter(PREFIX + FILE, true)) {
            writer.write(message + '\n');
        }
        catch (IOException e) {
            System.err.println("Cannot log to file");
        }
    }

    private String now() {
        return LocalDateTime.now().format(FORMAT);
    }
}
