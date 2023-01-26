package me.whiteship.java8to11._01_functionalinterface._01_fi;

public class FooBefore {

    public static void main(String[] args) {
        int base = 123;

        RunSomething runSomething = new RunSomething() {
            @Override
            public int run(int num) {
                return num + base;
            }
        };
        System.out.println(runSomething.run(100));;
    }
}
