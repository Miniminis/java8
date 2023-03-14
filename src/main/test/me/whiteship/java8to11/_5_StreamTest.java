package me.whiteship.java8to11;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class _5_StreamTest {

    @Test
    void 배열의_각_요소에_2를_곱한_배열을_반환한다() {
        int[] arr = {1, 20, 100, -50, 0};
        assertThat(Arrays.stream(arr).map(i -> i*2).toArray()).isEqualTo(new int[]{2, 40, 200, -100, 0});
    }
}
