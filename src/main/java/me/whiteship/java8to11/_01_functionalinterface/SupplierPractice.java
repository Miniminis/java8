package me.whiteship.java8to11._01_functionalinterface;

import java.util.function.Supplier;

/**
 * Supplier<T>
 * - T 타입의 값을 제공하는 함수 인터페이스
 *      - get
 * */
public class SupplierPractice {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "hey";
        System.out.println(supplier.get());
    }
}
