package me.whiteship.java8to11._02_lamda;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * lamda expression
 * - 인자의 타입은 생략 가능하다. 컴파일러가 추론한다.
 * - 바디 : 한 줄인 경우에 생략 가능하다. 여러 줄인 경우에 {} 에 묶어서 사용한다.
 * - 변수캡쳐
 *      - 로컬변수 : final or effective final 인 경우만 참조 가능.
 *          - 그렇지 않을 경우는 concurrency 이슈가 발생할 수 있기에 컴파일러가 방지한다.
 *      - effective final
 *          - final 키워드가 붙지는 않았지만, 사실상 final 인 변수
 *          - final 키워드를 사용하지 않는 변수를 익명클래스 구현체 혹은 람다에서 참조할 수 있다.
 *      - shadowing : 람다 내에서는 새로운 스콥을 만들지 않기 때문에 익명 클래스와 달리 쉐도잉 할 수 없다.
 * */
public class LamdaPractice {
    public static void main(String[] args) {
//        BinaryOperator<Integer> sum = Integer::sum;
        BinaryOperator<Integer> sum = (n1, n2) -> n1 + n2;
        sum.apply(10, 20);

        int base = 100;

        //내부 클래스
        class Sum {
            int base = 200;     //shadowing

            public int consumer(int a) {
                return base + a;
            }
        }
        System.out.println(new Sum().consumer(10));

        //익명클래스
        Consumer<Integer> intConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                int base = 200;     //shadowing
                System.out.println(base + integer);
            }
        };
        intConsumer.accept(10);


        //lamda
        IntConsumer consumer = (i) -> {
            System.out.println(base + i);
        };
        consumer.accept(10);
    }
}
