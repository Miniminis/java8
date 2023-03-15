package me.whiteship.java8to11._03_methodref;

import java.util.Objects;

public class Greeting {

    private String name;

    public Greeting() {
    }

    public Greeting(String name) {
        this.name = name;
    }

    public String hello(String name) {
        return "hello, " + name;
    }

    public static String hi (String name) {
        return "hi, " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Greeting greeting = (Greeting) o;
        return Objects.equals(name, greeting.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
