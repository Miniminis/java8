package me.whiteship.java8to11._04_interface;

public class DefaultFoo implements Foo {

    private final String city;

    public DefaultFoo(String city) {
        this.city = city;
    }

    @Override
    public String getCity() {
        return this.city;
    }

    @Override
    public void printCity() {
        System.out.println("hello from " + city);
    }
}
