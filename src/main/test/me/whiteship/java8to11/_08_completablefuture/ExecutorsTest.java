package me.whiteship.java8to11._08_completablefuture;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {

    @Test
    void 싱글_쓰레드_만들기() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(runHello("Java"));
        executorService.submit(runHello("Spring"));
        executorService.submit(runHello("JPA"));
        executorService.submit(runHello("Http"));
        executorService.submit(runHello("MySQL"));
        //Java : pool-1-thread-1
        //Spring : pool-1-thread-1
        //JPA : pool-1-thread-1
        //Http : pool-1-thread-1
        //MySQL : pool-1-thread-1
    }

    @Test
    void 쓰레드_종료() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> System.out.println("Hello : " + Thread.currentThread().getName()));

        executorService.shutdown(); //graceful shutdown : thread 내부의 일을 모두 처리하고 꺼짐
        executorService.shutdownNow();  //thread 실행 중 언제라도 꺼질 수 있음
    }

    @Test
    void 쓰레드풀_만들기() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(runHello("Java"));
        executorService.submit(runHello("Spring"));
        executorService.submit(runHello("JPA"));
        executorService.submit(runHello("Http"));
        executorService.submit(runHello("MySQL"));
        //Java : pool-1-thread-1
        //Spring : pool-1-thread-2      //2번 쓰레드에서 실행됨!
        //JPA : pool-1-thread-1
        //Http : pool-1-thread-1
        //MySQL : pool-1-thread-1
    }

    private Runnable runHello(String message) {
        return () -> System.out.println(message + " : " + Thread.currentThread().getName());
    }
}
