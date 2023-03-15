package me.whiteship.java8to11._04_ic;

import me.whiteship.java8to11._04_interfacechange.Boo;
import me.whiteship.java8to11._04_interfacechange.DefaultBoo;
import me.whiteship.java8to11._04_interfacechange.DefaultFoo;
import me.whiteship.java8to11._04_interfacechange.Foo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InterfaceDefaultMethodTest {

    @Test
    void 구현체_기본메소드를_제공한다() {
        DefaultFoo defaultFoo = new DefaultFoo("flash");

        assertThat(defaultFoo.getName()).isEqualTo("flash");
        assertThat(defaultFoo.getUpperCaseName()).isEqualTo("FLASH");
    }

    @Test
    void 인터페이스를_상속받은_인터페이스에서_다시추상메소드로_변경가능() {
        DefaultBoo defaultBoo = new DefaultBoo("kildong");
        assertThat(defaultBoo.greeting()).isEqualTo("hello from Boo!");
    }

    @Test
    void 인터페이스_구현체가_재정의할수도있다() {
        DefaultBoo defaultBoo = new DefaultBoo("kildong");
        assertThat(defaultBoo.getUpperCaseName()).isEqualTo("KILDONG!!");
    }

    @Test
    void 스태틱메소드를_제공할수있다() {
        assertThat(Foo.koreanGreeting()).isEqualTo("안녕하세요!");
    }
}
