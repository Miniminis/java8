package me.whiteship.java8to11._08_completablefuture;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CallableAndFutureTest {

    @Test
    void get으로_결과값_가져오기() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> helloFuture = executorService.submit(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, Future!";
        });

        System.out.println("Started!");

        String message = helloFuture.get();
        assertThat(message).isEqualTo("Hello, Future!");

        System.out.println("Ended!");
        executorService.shutdown();
    }

    @Test
    void get은_타임아웃을_설정할_수_있다() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> helloFuture = executorService.submit(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, Future!";
        });

        //future.get(timeout, timeunit) : timeout 시간이 지나면, TimeoutException
        assertThatThrownBy(() -> helloFuture.get(2, TimeUnit.SECONDS))
                .isInstanceOf(TimeoutException.class);
    }

    @Test
    void isDone으로_작업상태를_확인할_수_있다() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> helloFuture = executorService.submit(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, Future!";
        });

        assertThat(helloFuture.isDone()).isFalse();
        System.out.println("Started!");

        assertThat(helloFuture.get()).isEqualTo("Hello, Future!");

        assertThat(helloFuture.isDone()).isTrue();
        System.out.println("Ended!");

        executorService.shutdown();
    }

    @Test
    void cancel로_작업을_취소할_수_있다() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> helloFuture = executorService.submit(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, Future!";
        });

        assertThat(helloFuture.isCancelled()).isFalse();
        assertThat(helloFuture.isDone()).isFalse();
        System.out.println("Started!");

        boolean cancel = helloFuture.cancel(false);     //true : 진행중인 스레드 interrupt, false : 현재 진행중인 작업 끝날때까지 wait
        System.out.println("cancel = " + cancel);

        assertThat(helloFuture.isCancelled()).isTrue();
        assertThat(helloFuture.isDone()).isTrue();
        System.out.println("Ended!");

        executorService.shutdown();
    }

    @Test
    void isCanceled로_한번_취소된_서비스는_다시_get_할수없다() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> helloFuture = executorService.submit(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, Future!";
        });

        helloFuture.cancel(false);

        assertThatThrownBy(() -> helloFuture.get())
                .isInstanceOf(CancellationException.class);
    }

    @Test
    void invokeAll로_여러작업을_동시에_실행한다() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(3000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(1000L);
            return "Java";
        };

        Callable<String> spring = () -> {
            Thread.sleep(2000L);
            return "Spring";
        };

        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, spring));
        for (Future<String> f : futures) {
            System.out.println(f.get());
        }
        //동시에 출력 : 가장 시간이 긴 Hello 의 종료시간까지 기다렸다가 같이 출력된다.

        //Hello
        //Java
        //Spring
    }

    @Test
    void invokeAny로_여러작업중_하나라도_먼저_응답이_오면_끝낸다() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Callable<String> hello = () -> {
            Thread.sleep(3000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(1000L);
            return "Java";
        };

        Callable<String> spring = () -> {
            Thread.sleep(2000L);
            return "Spring";
        };

        String result = executorService.invokeAny(Arrays.asList(hello, java, spring));
        assertThat(result).isEqualTo("Java");   //가장 수행시간이 짧은 Java만 출력된다. 블로킹콜이다.
    }
}
