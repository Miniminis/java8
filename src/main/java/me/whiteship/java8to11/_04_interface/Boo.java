package me.whiteship.java8to11._04_interface;

public interface Boo extends Foo {

    void toUpperCase();

    @Override
    default void putBok() {
        System.out.println("🐡" + getCity() + "🐡");
    }
}
