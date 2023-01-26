package me.whiteship.java8to11._01_functionalinterface;

import java.util.function.Consumer;

/**
 * Consumer<T>
 * - T 타입을 받아서 아무 값도 리턴하지 않는 함수 인터페이스
 *      - Accept(T)
 *      - andThen
 * */
public class ConsumerPractice {
    public static void main(String[] args) {
        Consumer<String> greeting = (s) -> System.out.println(s);
        Consumer<String> answer = (s) -> System.out.println(s);

        greeting.accept("hello from seoul");
        greeting.andThen(answer).accept("hello");
    }
}
