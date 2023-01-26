package me.whiteship.java8to11._01_functionalinterface;

import java.util.function.Predicate;

/**
 * Predicate<T>
 * - T타입을 받아서 booelan 리턴하는 함수 인터페이스
 *      - test
 *      - And
 *      - Or
 *      - Negate
 * */
public class PredicatePractice {
    public static void main(String[] args) {
        Predicate<Integer> predicate = (num) -> num == 123;
        System.out.println(predicate.test(222));    //false
        System.out.println(predicate.test(123));    //true

        Predicate<Integer> predicate2 = (num) -> num > 100;
        System.out.println(predicate.and(predicate2).test(123));    //true

        System.out.println(predicate.negate().test(110));  //true
    }
}
