package kz.kaspi.actor;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * Collection class for {@link Actor}
 *
 * Have special methods for filtering data
 *
 * @author A.Yergali
 * @version 1.0
 */
public class ActorList {

    private final List<Actor> actors;

    {
        actors = new ArrayList<>();
    }

    private ActorList() { }

    public static ActorList empty() {
        return new ActorList();
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<Actor> getActorsOlderThan(int year) {
        return actors.stream().
                collect(groupingBy(Actor::getName)).
                values().stream().
                map(actorList -> actorList.stream().
                        max(Comparator.comparing(Actor::getAge)).
                        orElseThrow(NoSuchElementException::new)).
                filter(actor -> actor.getAge() > year).
                collect(Collectors.toList());
    }

    public List<Actor> getActorsOscarsMoreOrEqualThan(int oscars) {
        return actors.stream().
                collect(groupingBy(Actor::getName)).
                values().stream().
                filter(actorList -> actorList.size() >= oscars).
                map(actorList -> actorList.get(0)).
                collect(Collectors.toList());
    }

    public List<Actor> getSortedActors() {
        return actors.stream().
                sorted(Comparator.
                        comparing(Actor::getName).
                        thenComparing(Actor::getMovie)).
                collect(Collectors.toList());
    }

    public List<Actor> getPopularActors(int top) {
        return actors.stream().
                collect(groupingBy(Actor::getName)).
                values().stream().
                sorted((list1, list2) -> list2.size() - list1.size()).
                limit(top).
                map(list -> list.get(0)).
                collect(Collectors.toList());
    }

    public Map<Integer, List<Actor>> getActorsByDecades() {
        return actors.stream().collect(groupingBy(Actor::getDecade));
    }

    public int getPopularAge() {
        return actors.stream().
                collect(groupingBy(Actor::getAge)).
                values().stream().
                max(Comparator.comparingInt(List::size)).
                orElseThrow(NoSuchElementException::new).
                get(0).getAge();
    }

    public void add(Actor actor) {
        actors.add(actor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof ActorList))
            return false;

        ActorList actorList = (ActorList) o;
        return actors.equals(actorList.actors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actors);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + actors;
    }
}
