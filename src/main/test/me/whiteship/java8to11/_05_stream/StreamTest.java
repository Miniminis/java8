package me.whiteship.java8to11._05_stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamTest {

    private List<Food> asianFoods;
    private List<Food> westernFoods;

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

        westernFoods = new ArrayList<>(){{
            add(new Food(1, "토마토 파스타", true));
            add(new Food(2, "목살 스테이크", true));
            add(new Food(3, "티본 스테이크", false));
            add(new Food(4, "미국식 짠 피자", false));
        }};
    }

    @Test
    void 떡볶이_메뉴만() {
        List<Food> filtered = asianFoods.stream().filter(s -> s.getName().contains("떡볶이")).collect(Collectors.toList());

        assertThat(filtered).hasSize(2);
        assertThat(filtered).containsAll(
                List.of(new Food(4, "떡볶이", true),
                        new Food(5, "궁중떡볶이", false)));
    }

    @Test
    void 좋아하지_않는_음식만() {
        List<Food> filtered = asianFoods.stream().filter(f -> !f.isLiked()).collect(Collectors.toList());

        assertThat(filtered).hasSize(3);
        assertThat(filtered).containsAll(List.of(
                new Food(2, "설렁탕", false),
                new Food(5, "궁중떡볶이", false),
                new Food(6, "순대국", false)
        ));
    }

    @Test
    void 음식이름만_모아서_스트림_만들기() {
        List<String> foodNames = asianFoods.stream().map(Food::getName).collect(Collectors.toList());

        assertThat(foodNames).hasSize(6);
        assertThat(foodNames).containsAll(List.of("비빔밥", "설렁탕", "불닭볶음면", "떡볶이", "궁중떡볶이", "순대국"));
    }

    @Test
    void 아시아와_서양음식의_모든_이름만_출력하기() {
        List<List<Food>> globalFoods = new ArrayList<>(){{
            add(asianFoods);
            add(westernFoods);
        }};


        // with lambda : globalFoods.stream().flatMap(Collection::stream).map(Food::getName).collect(Collectors.toList());
        List<String> foodNames = globalFoods.stream()
                .flatMap(foods -> foods.stream())
                .map(food -> food.getName())
                .collect(Collectors.toList());

        assertThat(foodNames).isEqualTo(List.of(
                "비빔밥", "설렁탕", "불닭볶음면", "떡볶이", "궁중떡볶이", "순대국", "토마토 파스타", "목살 스테이크", "티본 스테이크", "미국식 짠 피자"
        ));
    }

    @Test
    void _10부터_1씩_증가하는_무제한스트림에서_처음의_10개_제외하고_최대_10개까지만_출력() {
        List<Integer> ints = Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .collect(Collectors.toList());

        assertThat(ints).isEqualTo(List.of(20, 21, 22, 23, 24, 25, 26, 27, 28, 29));
    }

    @Test
    void 서양음식중에서_피자종류가_있는지_확인() {
        boolean hasPizza = westernFoods.stream()
                .anyMatch(f -> f.getName().contains("피자"));

        assertThat(hasPizza).isTrue();
    }



    @Test
    void 배열의_각_요소에_2를_곱한_배열을_반환한다() {
        int[] arr = {1, 20, 100, -50, 0};
        assertThat(Arrays.stream(arr).map(i -> i*2).toArray()).isEqualTo(new int[]{2, 40, 200, -100, 0});
    }
}
