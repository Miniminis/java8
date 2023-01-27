package me.whiteship.java8to11._03_methodreference;

import java.util.function.BiFunction;

/**
 * 만약 람다가 하는 일이 기존 메소드 혹은 생성자를 호출하는 일이라면,
 * 메소드 레퍼런스를 사용해서 매우 간결하게 표현할 수 있다.
 * */
public class MethodReferenceExample {
    public static void main(String[] args) {
        MethodReferenceExample example = new MethodReferenceExample();
        System.out.println(MethodReferenceExample.mergeThings(10, 20, (a, b) -> a * b));

        System.out.println(MethodReferenceExample.mergeThings("hello", "java8", MethodReferenceExample::appendStrings));
        System.out.println(MethodReferenceExample.mergeThings("hello", "java8", example::appendString2));

        System.out.println(MethodReferenceExample.mergeThings("Hello", "java8", String::concat));
    }

    public static <T> T mergeThings(T a, T b, BiFunction<T, T, T> merger) {
        return merger.apply(a, b);
    }

    public static String appendStrings(String a, String b) {
        return a + b;
    }

    public String appendString2 (String a, String b) {
        return a + b + " 2";
    }
}


