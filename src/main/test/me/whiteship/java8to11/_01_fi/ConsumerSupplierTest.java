package me.whiteship.java8to11._01_fi;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsumerSupplierTest {

    @Test
    void consumerAccept() {
        Consumer<Integer> consumer = num -> System.out.println(num);
        consumer.accept(10);
    }

    @Test
    void andThenTest() {
        Consumer<Integer> consumer = num -> System.out.println(num + 10);
        Consumer<Integer> consumer2 = num -> System.out.println(num + 20);
        Consumer<Integer> both = consumer.andThen(consumer2);
        both.accept(10);
    }

    @Test
    void supplierGet() {
        Supplier<Integer> supplier = () -> 10;
        assertThat(supplier.get()).isEqualTo(10);
    }

}
