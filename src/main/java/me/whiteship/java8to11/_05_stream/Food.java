package me.whiteship.java8to11._05_stream;

import java.util.Objects;
import java.util.Optional;

public class Food {

    private int id;

    private String name;

    private boolean isLiked;

    private String bestRestaurantName;

    public Food(int id, String name, boolean isLiked) {
        this.id = id;
        this.name = name;
        this.isLiked = isLiked;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public Optional<String> getBestRestaurantName() {
        return Optional.ofNullable(bestRestaurantName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return getId() == food.getId() && isLiked() == food.isLiked() && Objects.equals(getName(), food.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), isLiked());
    }
}
