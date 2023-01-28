package me.whiteship.java8to11._04_interface;

/**
 * 기본 메소드
 * - 인터페이스에 메소드 선언이 아니라, 구현체를 제공하는 방법
 * - 해당 인터페이스를 구현한 클래스를 깨트리지 않고 새로운 기능을 추가할 수 있다.
 * - 기본 메소드는 구현체가 추가된 기능으로 그만큼 리스트가 있다.
 *      - 컴파일 에러는 아닐지라도, 구현체에 따라서 런타임 에러가 발생할 가능성이 있다.
 *      - 반드시 @implSpec 을 통해 문서화한다.
 *      - 항상 제대로 동작할 것이라는 보장이 없다.
 *          - 어떤 구현체가 null 을 리턴하게 되면, 동작하지 않게 될 것이다.
 *          - 보완 : @implSpec 을 통해 상세 구현 내용을 통해서 자세히 문서화한다.
 * - 구현체에서 재정의하는 것도 물론 가능하다.
 *      - Object 에서 제공하는 기본 메소드들(equals, hashCode 등)은 기본 메소드로 제공할 수 없다.
 *      - 구현체가 재정의를 해야한다.
 * - 인터페이스를 상속받는 인터페이스에서 다시 (기본 ->) 추상 메소드로 변경할 수 있다.
 * - 인터페이스 구현체가 재정의할 수도 잇다.
 * 스태틱 메소드
 * - 해당 타입 관련 헬퍼 혹은 유틸리티 메소드를 제공할 때, 인터페이스에 스태틱 메소드를 제공할 수 있다.
 * */
public interface Foo {

    String getCity();

    void printCity();

    /**
     * @implSpec 이 구현체는 getCity() 로 가져온 문자열을 대문자로 출력한다.
     * */
    default void toUpperCase() {
        System.out.println(getCity().toUpperCase());
    }

    default void putBok() {
        System.out.println(getCity() + "🐡");
    }

    static void printhello() {
        System.out.println("help from Static");
    }

}
