package me.whiteship.java8to11._01_functionalinterface._02_function;

import java.util.function.Function;

/**
 * Function<T, R>
 * - T 타입을 받아서 R타입을 리턴하는 함수 인터페이스
 * - 함수 조합용 메소드
 *      - andThen
 *      - compose
 * */
public class App2 {
    public static void main(String[] args) {
        Function<Integer, String> func = (number) -> "toString->" + number;
        System.out.println(func.apply(100));

        Function<Integer, Integer> plus10 = (number) -> number + 10;
        Function<Integer, Integer> multiply10 = (number) -> number * 10;

        /**
         * compose : 뒤에있는 연산을 먼저 수행 후 -> 앞에있는 연산 수행
         * */
        Function<Integer, Integer> multiply10AndPlus10 = plus10.compose(multiply10);
        System.out.println(multiply10AndPlus10.apply(2));
        //30

        /**
         * andThen() : 앞의 연산을 수행 후 -> 뒤의 연산을 수행
         * */
        Function<Integer, Integer> plus10AndMultiply10 = plus10.andThen(multiply10);
        System.out.println(plus10AndMultiply10.apply(2));
        //120
    }
}
