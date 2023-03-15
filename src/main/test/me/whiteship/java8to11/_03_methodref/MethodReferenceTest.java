package me.whiteship.java8to11._03_methodref;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

public class MethodReferenceTest {

    @Test
    void 스태틱_메소드_참조() {
        Function<String, String> function = Greeting::hi;

        assertThat(function.apply("flash")).isEqualTo("hi, flash");
    }

    @Test
    void 특정_객체의_인스턴스_메소드_참조() {
        Greeting greeting = new Greeting();
        Function<String, String> function = greeting::hello;

        assertThat(function.apply("flash")).isEqualTo("hello, flash");
    }

    @Test
    void 임의_객체의_인스턴스_메소드_참조() {
        String[] names = {"kildong", "michle", "doolly"};
        Arrays.sort(names, String::compareToIgnoreCase);

        assertThat(Arrays.toString(names))
                .isEqualTo("[doolly, kildong, michle]");
    }

    @Test
    void 생성자_참조() {
        Function<String , Greeting> greetingFunction = Greeting::new;   //with name
        Supplier<Greeting> greetingSupplier = Greeting::new;        //default

        assertThat(greetingFunction.apply("flash")).isEqualTo(new Greeting("flash"));
        assertThat(greetingSupplier.get()).isEqualTo(new Greeting());

    }
}
