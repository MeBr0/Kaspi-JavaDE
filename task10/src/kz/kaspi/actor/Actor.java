package kz.kaspi.actor;

import java.util.Objects;

/**
 * Record for actor receiving Oscar for specific movie at some year
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Actor {

    private final long id;
    private int year;
    private int age;
    private String name;
    private String movie;

    public Actor(long id, int year, int age, String name, String movie) {
        this.id = id;
        this.year = year;
        this.age = age;
        this.name = name;
        this.movie = movie;
    }

    public int getDecade() {
        return ((year % 100) / 10) * 10;
    }

    public long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Actor))
            return false;

        Actor actor = (Actor) o;
        return id == actor.id &&
                year == actor.year &&
                age == actor.age &&
                name.equals(actor.name) &&
                movie.equals(actor.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id: " + id + ", year: " + year + ", name: " + name + ", age: " + age +
                ", movie: " + movie + "]";
    }
}
