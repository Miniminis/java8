package me.whiteship.java8to11._04_interface;

public class App {
    public static void main(String[] args) {
        //basic
        Foo foo = new DefaultFoo("seoul");
        foo.printCity();
        foo.toUpperCase();

        //기본메소드로 인한 런타임 오류
        Foo foo2 = new DefaultFoo(null);
        foo2.printCity();
//        foo2.toUpperCase();       //NullPointerException

        //구현체에서 재정의
        BasicFoo basicFoo = new BasicFoo(null);
        basicFoo.toUpperCase();

        //인터페이스에서 change default -> abstract method
        Foo defaultBoo = new DefaultBoo("HongKong");
        defaultBoo.printCity();
        defaultBoo.toUpperCase();

        //overriding default
        foo.putBok();
        defaultBoo.putBok();

        //static method
        Foo.printhello();
    }
}
