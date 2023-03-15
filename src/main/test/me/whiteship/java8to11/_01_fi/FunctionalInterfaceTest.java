package me.whiteship.java8to11._01_fi;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctionalInterfaceTest {

    @Test
    void 익명내부클래스로_표현한다() {
        RunSomething runSomething = new RunSomething() {
            @Override
            public int plusTen(int num) {
                return num + 10;
            }
        };

        assertThat(runSomething.plusTen(10)).isEqualTo(20);
    }

    @Test
    void 람다로_표현한다() {
        RunSomething runSomething = num -> num + 10;
        assertThat(runSomething.plusTen(10)).isEqualTo(20);
    }

    @Test
    void 일급함수로사용할수있다() {
        RunSomething runSomething = num -> num + 10;
        assertThat(runSomething.plusTen(10)).isEqualTo(20);
    }

    @Test
    void 순수함수이다() {
        int baseNum = 10;
//        RunSomething runSomething = num -> baseNum += num;   // 컴파일 에러
//        runSomething.plusTen(10);
    }

    @Test
    void 고차함수이다() {
        RunFactory runFactory = new RunFactory();
        RunSomething runSomething = runFactory.runSomething(num -> num + 100);
        assertThat(runSomething.plusTen(10)).isEqualTo(110);
    }

    @Test
    void 상태가없어야한다() {
        int baseNum = 15;
        RunSomething runSomething = num -> baseNum + num;
        assertThat(runSomething.plusTen(10)).isEqualTo(25);
    }
}
