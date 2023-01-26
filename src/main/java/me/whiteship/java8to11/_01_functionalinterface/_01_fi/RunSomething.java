package me.whiteship.java8to11._01_functionalinterface._01_fi;

/**
 * 함수형 인터페이스
 * - first class object 로 사용할 수 있다.
 *      - 매개변수, 리턴타입, 변수 등으로 만들어서 사용할 수 있다.
 * - 순수함수 : pure function
 *      - 수학적 함수
 *      - 입력받은 값이 동일한 경우, 결과값이 같아야 한다.
 *      - 예를 들면 1을 넣었다면, 이를 반복해서 호출하면 계속 같은 값이 나와야 한다.
 *      - 순수함수가 깨지는 경우는 어떤 경우인가?
 *          - 함수나 매소드 외부에서 정의된 변수의 값을 가져와서 변경하는 경우
 * - 고차합수 : high-order function
 *      - 함수가 함수를 매개변수로 받을 수 잇고, 함수를 리턴할 수도 있다.
 * - 불변성
 * */
@FunctionalInterface
public interface RunSomething {

    abstract int run(int num);      //인터페이스에서 abstract 는 생략가능

//    int runSomething(int num);        //다른 추상매소드가 있다면 함수형 인터페이스가 아니다.

    //추상 메소드가 아닌 다른 메소드는 와도 됨
    static void print() {
        System.out.println("print something");
    }
}
