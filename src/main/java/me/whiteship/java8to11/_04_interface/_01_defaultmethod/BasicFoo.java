package me.whiteship.java8to11._04_interface._01_defaultmethod;

import me.whiteship.java8to11._04_interface.Foo;

public class BasicFoo implements Foo {

    private final String city;

    public BasicFoo(String city) {
        this.city = city;
    }


    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void printCity() {
        System.out.println(city);
    }

    @Override
    public void toUpperCase() {
        if (city != null) {
            System.out.println(city.toUpperCase());
            return;
        }
        System.out.println("City is null");
    }
}
