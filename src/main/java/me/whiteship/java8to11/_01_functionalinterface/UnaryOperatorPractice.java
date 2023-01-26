package me.whiteship.java8to11._01_functionalinterface;

import java.util.function.UnaryOperator;

/**
 * UnaryOperator<T>
 * - Function<T, R>의 특수한 형태로, 입력값 하나를 받아서 동일한 타입을 리턴하는 함수 인터페이스
 * */
public class UnaryOperatorPractice {
    public static void main(String[] args) {
//        UnaryOperator<Integer> operator = (i) -> "String";  //error

        UnaryOperator<Integer> operator = (i) -> i * 100;
        System.out.println(operator.apply(10));     //1000
    }
}
