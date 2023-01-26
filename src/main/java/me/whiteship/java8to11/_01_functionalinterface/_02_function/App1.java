package me.whiteship.java8to11._01_functionalinterface._02_function;

/**
 * Function<T, R>
 * - T 타입을 받아서 R타입을 리턴하는 함수 인터페이스
 * - 함수 조합용 메소드
 *      - andThen
 *      - compose
 * */
public class App1 {
    public static void main(String[] args) {
        IntToString intToString = new IntToString();
        System.out.println(intToString.apply(123));
    }
}
