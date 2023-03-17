package me.whiteship.java8to11._09_annotation;

import java.util.List;

@Chicken("양념")
@Chicken("후라이드")
public class App {

    public static void main(String[] args) throws RuntimeException {
        List<String> names = List.of("puradak");
    }

    static class Cola<T> {
        public  static  <C> void print(C c) {
            System.out.println(c);
        }
    }
}
