package me.whiteship.java8to11._01_functionalinterface._01_fi;

public class FooAfter {

    public static void main(String[] args) {
        int base = 123;
        RunSomething runSomething = num -> num + base;
        System.out.println(runSomething.run(100));;
    }
}
