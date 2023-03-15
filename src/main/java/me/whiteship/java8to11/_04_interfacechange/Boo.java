package me.whiteship.java8to11._04_interfacechange;

public interface Boo extends Foo {

    @Override
    default String greeting() {
        return "hello from Boo!";
    }
}
