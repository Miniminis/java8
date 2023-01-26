package me.whiteship.java8to11._01_functionalinterface._02_function;

import java.util.function.Function;

public class IntToString implements Function<Integer, String> {

    @Override
    public String apply(Integer integer) {
        return "toString " + integer;
    }
}
