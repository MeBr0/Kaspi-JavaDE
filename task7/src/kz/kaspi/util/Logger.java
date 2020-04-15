package kz.kaspi.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger class
 * Use {@link java.time} for logging
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Logger {

    private static Logger logger = null;

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
        System.out.println(now() + " " + message);
    }

    public void error(String message) {
        System.err.println(now() + " " + message);
    }

    private String now() {
        return LocalDateTime.now().format(FORMAT);
    }
}
