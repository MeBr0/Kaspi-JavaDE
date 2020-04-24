package kz.kaspi;

import kz.kaspi.actor.Actor;
import kz.kaspi.actor.ActorList;
import kz.kaspi.util.ActorParser;

import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        ActorList actors = ActorParser.getList("oscar_age_female.csv");

        int year = 35;
        System.out.println("Actors older than " + year);
        System.out.println(actors.getActorsOlderThan(year).stream().
                map(Actor::getName).collect(Collectors.toList()));


        int oscars = 2;
        System.out.println("Actors received Oscar more or equal than " + oscars);
        System.out.println(actors.getActorsOscarsMoreOrEqualThan(oscars).stream().
                map(Actor::getName).collect(Collectors.toList()));


        System.out.println("Sorted actors");
        System.out.println(actors.getSortedActors().stream().
                map(Actor::getName).collect(Collectors.toList()));

        int top = 5;
        System.out.println("Actors with " + top + " most popular names");
        System.out.println(actors.getPopularActors(top).stream().
                map(Actor::getName).collect(Collectors.toList()));

        System.out.println("Actors by decades");
        actors.getActorsByDecades().
                forEach((decade, list) -> {
                    System.out.println("Decade " + decade + " - " +
                            list.stream().map(Actor::getName).collect(Collectors.toList()));
                });

        System.out.println("Popular age of actors " + actors.getPopularAge());
    }
}
