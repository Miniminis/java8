package me.whiteship.java8to11._01_functionalinterface;

import java.util.function.BinaryOperator;

/**
 * BinaryOperator<T>
 * - BiFunction<T, U, R>의 특수한 형태. 동일한 타입의 입력값 두개를 받아, 동일한 타입을 리턴하는 함수 인터페이스
 * */
public class BinaryOperatorPractice {
    public static void main(String[] args) {
        BinaryOperator<String> operator = (s1, s2) -> s1 + s2 + " is delicious";
        System.out.println(operator.apply("김치", "볶음밥"));
        //김치볶음밥 is delicious
    }
}
