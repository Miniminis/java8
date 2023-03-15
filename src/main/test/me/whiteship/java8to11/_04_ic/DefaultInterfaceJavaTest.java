package me.whiteship.java8to11._04_ic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultInterfaceJavaTest {

    @Test
    void forEachTest() {
        List<String> names = List.of("flash", "doolly", "kildong", "ttochi");
        names.forEach(System.out::println);
    }

    @Test
    void spliterator() {
        List<String> names = List.of("flash", "doolly", "kildong", "ttochi");
        Spliterator<String> spliterator = names.spliterator();
        Spliterator<String> spliterator2 = spliterator.trySplit();
        while (spliterator.tryAdvance(System.out::println));
        System.out.println("==================");
        while (spliterator2.tryAdvance(System.out::println));
        /**
         * kildong
         * ttochi
         * ==================
         * flash
         * doolly
         * */
    }

    @Test
    void streamTest() {
        List<String> names = List.of("flash", "doolly", "kildong", "ttochi");
        long count = names.stream()
                .filter(n -> n.contains("l"))
                .count();
        assertThat(count).isEqualTo(3);

        Set<String> set = names.stream()
                .filter(n -> n.contains("l"))
                .collect(Collectors.toSet());
        assertThat(set).isEqualTo(Set.of("flash", "doolly", "kildong"));
    }

    @Test
    void removeIfTest() {
        List<String> names = new ArrayList<>() {{
            add("flash");
            add("doolly");
            add("kildong");
            add("ttochi");
        }};
        names.removeIf(n -> n.startsWith("f"));

        assertThat(names).doesNotContain("flash");
    }

    @Test
    void reversedSort() {
        List<String> names = new ArrayList<>() {{
            add("flash");
            add("doolly");
            add("kildong");
            add("ttochi");
        }};
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed());

        names.forEach(System.out::println);
        /**
         * ttochi
         * kildong
         * flash
         * doolly
         * */
    }
}
