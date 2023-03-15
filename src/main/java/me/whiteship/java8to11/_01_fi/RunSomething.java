package me.whiteship.java8to11._01_fi;

@FunctionalInterface    // 어노테이션을 달아서 명시해준다.
public interface RunSomething {

    int plusTen(int num);

    // java8부터 interface 에 쓰일 수 있는 함수의 형태가 다양해졌다.
    // static method, default method 는 functional interface의 메소드로 카운트하지 않아서 아래와 같이 사용 가능!
    static void printName(String name) {
        System.out.println(name);
    }

    default void printAge(int age) {
        System.out.println(age);
    }
}
