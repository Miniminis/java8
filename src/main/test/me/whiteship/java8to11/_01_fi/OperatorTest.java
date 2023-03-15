package me.whiteship.java8to11._01_fi;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.assertj.core.api.Assertions.assertThat;

public class OperatorTest {

    @Test
    void unaryOperatorTest() {
        Function<Integer, Integer> function = (i1) -> i1 + 10;
        UnaryOperator<Integer> unaryOperator = i1 -> i1 + 10;
        assertThat(function.apply(10)).isEqualTo(unaryOperator.apply(10));
    }

    @Test
    void binaryOperatorTest() {
        BiFunction<Integer, Integer, Integer> biFunction = (i1, i2) -> i1 * i2;
        BinaryOperator<Integer> binaryOperator = (i1, i2) -> i1 * i2;
        assertThat(biFunction.apply(10, 12)).isEqualTo(binaryOperator.apply(10, 12));
    }
}
