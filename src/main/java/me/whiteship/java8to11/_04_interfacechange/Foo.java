package me.whiteship.java8to11._04_interfacechange;

import java.util.Locale;

public interface Foo {

    String getName();

    String greeting();

    static String koreanGreeting() {
        return "안녕하세요!";
    }

    /**
     * @implSpec
     * getName() 을 호출하여 대문자로 바꿔주는 기능을 한다.
     * */
    default String getUpperCaseName() {
        return getName().toUpperCase(Locale.ROOT);
    }

//    default String toString() {       //compile error!
//
//    }
}
