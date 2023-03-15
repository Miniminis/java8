package me.whiteship.java8to11._01_fi;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctionTest {

    @Test
    void applyTest() {
        //구현체
        Plus10 plus10 = new Plus10();
        assertThat(plus10.apply(10)).isEqualTo(20);

        //lambda
        Function<Integer, Integer> plus11 = num -> num + 11;
        assertThat(plus11.apply(10)).isEqualTo(21);
    }

    @Test
    void composeTest() {
        Function<Integer, Integer> plus10 = num -> num + 10;
        Function<Integer, Integer> multiply5 = num -> num * 5;
        Function<Integer, Integer> both = plus10.compose(multiply5);

        assertThat(both.apply(2)).isEqualTo(20);

    }

    @Test
    void andThenTest() {
        Function<Integer, Integer> plus10 = num -> num + 10;
        Function<Integer, Integer> multiply5 = num -> num * 5;
        Function<Integer, Integer> both = plus10.andThen(multiply5);

        assertThat(both.apply(2)).isEqualTo(60);
    }
}
