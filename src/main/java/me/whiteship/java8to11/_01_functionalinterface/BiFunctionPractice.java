package me.whiteship.java8to11._01_functionalinterface;

import java.util.function.BiFunction;

/**
 * 두 개의 값을 받아서 리턴하는 함수 인터페이스
 * */
public class BiFunctionPractice {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, String> intToString = (i, j) -> i + " + " + j;
        System.out.println(intToString.apply(10, 20));
    }
}
