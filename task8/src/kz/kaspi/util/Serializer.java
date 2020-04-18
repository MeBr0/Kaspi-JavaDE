package kz.kaspi.util;

import java.io.*;

/**
 * Class for general java serialization
 * Files for saving stored in {@link #PREFIX} directory
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Serializer {

    private static final Logger LOG = Logger.getInstance();

    private static final String PREFIX = "ser/";

    public static boolean serialize(String file, Object object) {
        String path = PREFIX + file;

        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path))) {
            stream.writeObject(object);

            stream.flush();

            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    public static <T> T deserialize(String file, Class<T> clazz) {
        String path = PREFIX + file;

        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(path))) {
            return (T) stream.readObject();
        }
        catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }
}