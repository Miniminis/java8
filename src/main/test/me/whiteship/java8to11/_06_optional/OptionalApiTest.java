package me.whiteship.java8to11._06_optional;

import me.whiteship.java8to11._05_stream.Food;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OptionalApiTest {

    private List<Food> asianFoods = new ArrayList<>(){{
        add(new Food(1, "비빔밥", true));
        add(new Food(2, "설렁탕", false));
        add(new Food(3, "불닭볶음면", true));
        add(new Food(4, "떡볶이", true));
        add(new Food(5, "궁중떡볶이", false));
        add(new Food(6, "순대국", false));
    }};


    @Test
    void optionalOf를이용하여_Optional을_만들수있다() {
        Optional<Food> optionalOf = Optional.of(new Food(1, "라면", true));
        Optional<Food> optionalNullable = Optional.ofNullable(null);
        Optional<Food> optionalEmpty = Optional.empty();
    }

    @Test
    void optionalIsPresent를_이용하여_값의_존재여부를_확인한다() {
        Optional<Food> optionalOf = Optional.of(new Food(1, "라면", true));
        Optional<Food> optionalNullable = Optional.ofNullable(null);
        Optional<Food> optionalEmpty = Optional.empty();

        assertThat(optionalOf.isPresent()).isTrue();
        assertThat(optionalNullable.isPresent()).isFalse();
        assertThat(optionalEmpty.isEmpty()).isTrue();
    }

    @Test
    void optionalGet을_이용하여_값을_꺼낸다() {
        Optional<Food> optionalRamen = Optional.of(new Food(1, "라면", true));
        if (optionalRamen.isPresent()) {
            Food ramen = optionalRamen.get();
            assertThat(ramen).isEqualTo(new Food(1, "라면", true));
        }
    }

    @Test
    void null값을_Get하려고하면_예외가_발생한다() {
        Optional<Food> optionalNullable = Optional.ofNullable(null);
        assertThatThrownBy(() -> optionalNullable.get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void empty값을_Get하려고하면_예외가_발생한다() {
        Optional<Food> optionalEmpty = Optional.empty();
        assertThatThrownBy(() -> optionalEmpty.get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void ifPresentConsumer로_반환값_없이_행위를_수행한다() {
        Optional<Food> optionalRamen = Optional.of(new Food(1, "라면", true));
        optionalRamen.ifPresent(f -> System.out.println(f.getName()));
        //라면
    }

    @Test
    void orElse는_무조건_실행되므로_이미_반환할_값이_있을떄_사용하면_좋다() {
        Optional<Food> optionalEmpty = Optional.empty();
        Food food = optionalEmpty.orElse(createNewFood());
        assertThat(food).isEqualTo(new Food(1, "라면", true));

        System.out.println("=====");

        Optional<Food> optionalOf = Optional.of(new Food(1, "라면", true));
        Food food2 = optionalOf.orElse(createNewFood());
        assertThat(food2).isEqualTo(new Food(1, "라면", true));

        //new food created
        //=====
        //new food created
    }

    @Test
    void orElseGet은_없을때만_실행되므로_새롭게_만들어줄떄_사용하면_좋다() {
        Optional<Food> optionalEmpty = Optional.empty();
        Food food = optionalEmpty.orElseGet(() -> createNewFood()); //supplier
        assertThat(food).isEqualTo(new Food(1, "라면", true));

        System.out.println("=====");

        Optional<Food> optionalOf = Optional.of(new Food(1, "라면", true));
        Food food2 = optionalOf.orElseGet(OptionalApiTest::createNewFood);  //supplier
        assertThat(food2).isEqualTo(new Food(1, "라면", true));

        //new food created
        //=====
    }

    @Test
    void orElseThrow는_없는경우_에러를_던진다() {
        Optional<Food> optionalEmpty = Optional.empty();
        assertThatThrownBy(() -> optionalEmpty.orElseThrow(() -> new NullPointerException()))
                .isInstanceOf(NullPointerException.class);

        Optional<Food> optionalEmpty2 = Optional.empty();
        assertThatThrownBy(() -> optionalEmpty2.orElseThrow(NullPointerException::new))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void filter를_이용하여_optional_값을_걸러낼수있다() {
        Optional<Food> first = asianFoods.stream()
                .filter(f -> f.getId() > 2)
                .findFirst();

        Food food = first.filter(f -> f.getId() == 3).orElseThrow();
        assertThat(food.getName()).isEqualTo("불닭볶음면");
    }

    @Test
    void flatMap으로_Optional에_들어있는값을_변환할수있다() {
        Optional<Food> first = asianFoods.stream()
                .filter(f -> f.getId() > 2)
                .findFirst();

        Optional<String> filtered = first.map(f -> f.getBestRestaurantName()).orElse(Optional.empty());
        assertThat(filtered).isEmpty();

        Optional<String> filtered2 = first.flatMap(f -> f.getBestRestaurantName());
        assertThat(filtered2).isEmpty();
    }


    private static Food createNewFood() {
        System.out.println("new food created");
        return new Food(1, "라면", true);
    }
}
