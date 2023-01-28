package me.whiteship.java8to11._04_interface._02_java;

import java.util.ArrayList;
import java.util.List;

/**
 * 실제 java API 에서의 변화
 * - WebMvcConfigurer <- WebMvcConfigurerAdapter
 *      - 실제로 webMvcConfigurer 인터페이스에서 필요한 매소드만 오바라이딩 하여 사용할 수 있게 제공하던 구현체  WebMvcConfigurerAdapter 는 java8의 default 메소드 등장 이후, @Deprecated 되었다.
 * -
 * */
public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("james");
        names.add("lucy");
        names.add("thomas");
        names.add("jacson");
        names.add("alice");
        names.add(null);

        /**
         * comparator
         * - reversed
         * - thenComparing
         * - static reverseOrder, naturalOrder
         * - static nullsFirst, nullsLast
         * - static comparing
         * */
//        names.sort(String::compareToIgnoreCase);
//
//        Comparator<String> comparator = String::compareToIgnoreCase;
//        Comparator<String> comparator2 = (s, t1) -> s.length() - t1.length();
//        Comparator<String> comparator3 = Comparator.comparingInt(String::length);
//        names.sort(comparator.reversed().thenComparing(comparator2));
//
//        Comparator<String> comparator4 = Comparator.nullsFirst(comparator);
//        names.sort(comparator4);
//        names.forEach(System.out::println);

        /**
         * collection
         * - stream, parallelStream
         * - removeIf
         * - spliterator
         * */
//        names.stream()
//                .filter(s -> s.contains("s"))
//                .forEach(System.out::println);

//        names.removeIf(s -> s.equalsIgnoreCase("THOMAS"));
//        names.forEach(System.out::println);

        /**
         * iterable - spliterator
        * */
//        Spliterator<String> spliterator = names.spliterator();
//        Spliterator<String> spliterator2 = spliterator.trySplit();
//        while (spliterator.tryAdvance(System.out::println));
//        System.out.println("===");
//        while (spliterator2.tryAdvance(System.out::println));

        /**
         * iterable - forEach
         * */
//        names.forEach(System.out::println);

    }
}
