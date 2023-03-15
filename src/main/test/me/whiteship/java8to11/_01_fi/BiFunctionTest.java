package me.whiteship.java8to11._01_fi;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;

public class BiFunctionTest {

    @Test
    void applyTest() {
        BiFunction<Integer, Boolean, String> isTrue = (num, bool) -> bool ? "true" : "false";
        assertThat(isTrue.apply(10, false)).isEqualTo("false");
    }
}
