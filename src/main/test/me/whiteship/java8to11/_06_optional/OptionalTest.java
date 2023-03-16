package me.whiteship.java8to11._06_optional;

import me.whiteship.java8to11._05_stream.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalLong;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionalTest {

    private List<Food> asianFoods;

    @BeforeEach
    void init() {
        asianFoods = new ArrayList<>(){{
            add(new Food(1, "비빔밥", true));
            add(new Food(2, "설렁탕", false));
            add(new Food(3, "불닭볶음면", true));
            add(new Food(4, "떡볶이", true));
            add(new Food(5, "궁중떡볶이", false));
            add(new Food(6, "순대국", false));
        }};
    }


    @Test
    void primitiveTypeOptionalTest() {
        OptionalInt optionalInt = OptionalInt.of(10);
        optionalInt.ifPresent(System.out::println);

        OptionalLong optionalLong = OptionalLong.of(10);
        if (optionalLong.isPresent()) {
            assertThat(optionalLong.getAsLong()).isEqualTo(10);
        }
    }

    @Test
    void optional이_필요없을때_두번감싸지_말자() {
        Optional<Optional<String>>  opt = Optional.ofNullable(null);    // not recommended
        Optional<String> s = opt.get(); //NPE 발생
        String s1 = s.get();

        Optional<List<Food>> optionalFoods = Optional.of(asianFoods);   // not recommended
        asianFoods.isEmpty();                                           // recommended
    }
}
