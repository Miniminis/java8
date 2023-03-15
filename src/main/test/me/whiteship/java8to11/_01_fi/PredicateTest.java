package me.whiteship.java8to11._01_fi;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class PredicateTest {

    @Test
    void test() {
        Predicate<Integer> isEven = num -> num % 2 == 0;
        assertThat(isEven.test(11)).isFalse();
        assertThat(isEven.test(10)).isTrue();
    }

    @Test
    void combineTest() {
        Predicate<String> length6 = str -> str.length() == 6;
        Predicate<String> startsWithM = str -> str.startsWith("M");
        Predicate<String> containsT = str -> str.contains("T");

        Predicate<String> triple = length6.and(startsWithM).or(containsT);

        assertThat(triple.test("Mountain")).isFalse();
        assertThat(triple.test("moTher")).isTrue();
    }

    @Test
    void negateTest() {
        Predicate<String> length6 = str -> str.length() == 6;
        assertThat(length6.negate().test("hello")).isTrue();
    }
}
