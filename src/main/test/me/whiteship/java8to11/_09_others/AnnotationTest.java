package me.whiteship.java8to11._09_others;

import me.whiteship.java8to11._09_annotation.Chicken;
import me.whiteship.java8to11._09_annotation.ChickenContainer;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Chicken("양념")
@Chicken("후라이드")
public class AnnotationTest {

    @Test
    void 컨테이너_어노테이션을_이용하여_중복_어노테이션을_참조한다() {
        ChickenContainer chickenContainer = AnnotationTest.class.getAnnotation(ChickenContainer.class);
        Chicken[] values = chickenContainer.value();

        String[] expected = {"양념", "후라이드"};
        IntStream.range(0, expected.length)
                .forEach(i -> {
                    assertThat(values[i].value()).isEqualTo(expected[i]);
                });
    }

    @Test
    void 어노테이션_타입으로_중복_어노테이션을_참조한다() {
        Chicken[] values = AnnotationTest.class.getAnnotationsByType(Chicken.class);

        String[] expected = {"양념", "후라이드"};
        IntStream.range(0, expected.length)
                .forEach(i -> {
                    assertThat(values[i].value()).isEqualTo(expected[i]);
                });
    }
}
