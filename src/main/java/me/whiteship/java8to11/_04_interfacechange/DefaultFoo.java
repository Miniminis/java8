package me.whiteship.java8to11._04_interfacechange;

public class DefaultFoo implements Foo {

    private String name;

    private DefaultFoo() {
    }

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String greeting() {
        return "hello from DefaultFoo!";
    }
}
