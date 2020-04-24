package kz.kaspi.util;

import kz.kaspi.actor.Actor;
import kz.kaspi.actor.ActorList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Util class for parsing {@link Actor} from CSV files
 *
 * @author A.Yergali
 * @version 1.0
 */
public class ActorParser {

    private static final String PREFIX = "files/";
    private static final String DELIMITER = ",";

    public static ActorList getList(String fileName) {
        fileName = PREFIX + fileName;

        ActorList actors = ActorList.empty();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            reader.lines().
                    map(line -> line.split(DELIMITER)).
                    filter(values -> values.length >= 5).
                    skip(1).                                // Skip headers
                    forEach(values -> {

                        long id = Long.parseLong(values[0].trim());
                        int year = Integer.parseInt(values[1].trim());
                        int age = Integer.parseInt(values[2].trim());
                        String name = values[3].trim().replaceAll("\"", "");
                        String movie = Arrays.stream(values, 4, values.length).
                                collect(Collectors.joining()).
                                trim().
                                replaceAll("\"", "");

                        actors.add(new Actor(id, year, age, name, movie));
                    });
        }
        catch (FileNotFoundException e) {
            System.err.println("File " + fileName + " not found!");
        }
        catch (IOException e) {
            System.err.println("IO exception!");
        }

        return actors;
    }
}
