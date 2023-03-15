package me.whiteship.java8to11._02_lambda;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;

public class LambdaTest {

    @Test
    void variableCapture() {
        int baseNum = 100;

        class NestedInnerClassTest {
            int baseNum = 1;    // 참조하는 변수
            @Test
            void plus10() {
                System.out.println(baseNum + 10);
            }
        }

        Consumer<Integer> consumer = new Consumer<Integer>() {
            int baseNum = 5;    // 참조하는 변수
            @Override
            public void accept(Integer integer) {
                System.out.println(baseNum + integer);
            }
        };

        // lambda
        Function<Integer, Integer> function = num -> baseNum + num;

        // baseNum++;      //compile error : not final!
    }
}
