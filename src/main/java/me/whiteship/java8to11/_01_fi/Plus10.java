package me.whiteship.java8to11._01_fi;

import java.util.function.Function;

public class Plus10 implements Function<Integer, Integer> {

    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
}
