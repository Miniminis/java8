package me.whiteship.java8to11._04_interfacechange;

import java.util.Locale;

public class DefaultBoo implements Boo {

    private String name;

    private DefaultBoo() {
    }

    public DefaultBoo(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUpperCaseName() {
        return getName().toUpperCase(Locale.ROOT) + "!!";
    }
}
