package me.whiteship.java8to11._05_stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream
 * - sequence of elements supporting sequential and parallel aggregate operations
 * - 데이터를 담고 있는 저장소가 아니다.
 * - Functional in nature, 스트림이 처리하는 데이터 소스를 변경하지 않는다.
 * - 스트림으로 처리하는 데이터는 오직 한번만 처리한다.
 * - 무제한일 수 있다.(short circuit 메소드를 사용해서 제한할 수 있다.)
 * - 중개 오퍼레이션은 기본적으로 lazy
 * - 손쉽게 병렬처리를 할 수 있다.
 *
 * 스트림 파이프라인
 * - 0 또는 다수의 중개 오퍼레이션과 한 개의 종료 오퍼레이션으로 구성한다.
 * - 스트림의 데이터 소스는 오직 터미널 오퍼레이션을 실행할 때에만 처리한다.
 *
 * 중개 오퍼레이션
 * - stream 을 리턴한다.
 * - stateless / stateful 오퍼레이션으로 더 상세하게 구분할 수 있다.
 *      - 기본적으로는 stateless.
 *      - distict, sorted 처럼 이전 상태를 기억해야하면, stateful
 * - filter, map, limit, skip, sorted
 *
 * 종료 오퍼레이션
 * - stream 을 리턴하지 않음
 * - collect, allMatch, count, forEach, min, max
 ** */
public class App {

    public static void main(String[] args) {
        //initialize
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> keesunEvents = new ArrayList<>();
        keesunEvents.add(springClasses);
        keesunEvents.add(javaClasses);

        System.out.println("filter - spring 으로 시작하는 수업");
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> System.out.println(oc.getTitle()));

        System.out.println("close 되지 않은 수업");
        springClasses.stream()
//                        .filter(oc -> !oc.isClosed())
                        .filter(Predicate.not(OnlineClass::isClosed))
                        .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("수업 이름만 모아서 스트림 만들기");
        List<String> titles = springClasses
                .stream()
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());
        titles.forEach(System.out::println);


        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        keesunEvents.stream()
//                        .flatMap(onlineClasses -> onlineClasses.stream())
                        .flatMap(Collection::stream)
                        .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10, i -> i+1).skip(10).limit(10).forEach(System.out::println);


        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream().anyMatch(jc -> jc.getTitle().contains("Test"));
        System.out.println("test = " + test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 것만 모아서 List로 만들기");
        List<String> springs = springClasses.stream().map(OnlineClass::getTitle).filter(oc -> oc.contains("spring")).collect(Collectors.toList());
        springs.forEach(System.out::println);

    }

}

class OnlineClass {
    private final int id;

    private final String title;

    private final boolean isClosed;

    public OnlineClass(int id, String title, boolean isClosed) {
        this.id = id;
        this.title = title;
        this.isClosed = isClosed;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isClosed() {
        return isClosed;
    }
}

