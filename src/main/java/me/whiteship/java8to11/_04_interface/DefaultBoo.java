package me.whiteship.java8to11._04_interface;

public class DefaultBoo implements Boo {

    private final String city;

    public DefaultBoo(String city) {
        this.city = city;
    }

    @Override
    public String getCity() {
        return this.city;
    }

    @Override
    public void printCity() {
        System.out.println(this.city);
    }

    @Override
    public void toUpperCase() {
        System.out.println("abstrct method UpperCase from " + this.city);
    }
}
