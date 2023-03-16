package me.whiteship.java8to11._08_completablefuture;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CompletableFutureTest {

    @Test
    void completableFuture1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("flash");

        assertThat(future.get()).isEqualTo("flash");
    }

    @Test
    void completableFuture2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.completedFuture("flash");
        assertThat(future.get()).isEqualTo("flash");
    }

    @Test
    void runAsync() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Hello : " + Thread.currentThread().getName());
        });
        //Hello : ForkJoinPool.commonPool-worker-19
    }

    @Test
    void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello : " + Thread.currentThread().getName());
            return "Hello";
        });

        System.out.println(stringCompletableFuture.get());
        //Hello : ForkJoinPool.commonPool-worker-19
        //Hello
    }

    @Test
    void anotherExecutor() {
        Executor executor = Executors.newSingleThreadExecutor();
        CompletableFuture.runAsync(() -> {
            System.out.println("Hello : " + Thread.currentThread().getName());
        }, executor);
        //Hello : pool-1-thread-1   -> 지정된 executor 로 실행된다.
    }

    @Test
    void thenApply_리턴값을받아_새로운값을_리턴() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newSingleThreadExecutor();
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello : " + Thread.currentThread().getName());
            return "Hello";
        }, executor).thenApply((s) -> {
            System.out.println("thenApply : " + Thread.currentThread().getName());
            return s.toUpperCase(Locale.ROOT);
        });
        System.out.println(stringCompletableFuture.get());
        //Hello : pool-1-thread-1
        //thenApply : pool-1-thread-1
        //HELLO
    }

    @Test
    void thenAccept_리턴값을받아_리턴없이_처리() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newSingleThreadExecutor();
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello : " + Thread.currentThread().getName());
            return "Hello";
        }, executor).thenAccept((s) -> {
            System.out.println("thenApply : " + Thread.currentThread().getName());
        });
        System.out.println(voidCompletableFuture.get());
        //Hello : pool-1-thread-1
        //thenApply : pool-1-thread-1
        //null
    }

    @Test
    void thenRun_리턴값을_받아_다른작업을_처리하는_콜백() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello : " + Thread.currentThread().getName());
            return "Hello";
        }).thenRun(() -> System.out.println(Thread.currentThread().getName()));
        System.out.println("voidCompletableFuture.get() = " + voidCompletableFuture.get());
        //Hello : ForkJoinPool.commonPool-worker-19
        //ForkJoinPool.commonPool-worker-19
        //voidCompletableFuture.get() = null
    }

    @Test
    void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello : " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> stringCompletableFuture = helloFuture.thenCompose(CompletableFutureTest::getWorldFuture);
        System.out.println(stringCompletableFuture.get());
        //Hello : ForkJoinPool.commonPool-worker-19
        //World : ForkJoinPool.commonPool-worker-5
        //Hello World
    }

    private static CompletableFuture<String> getWorldFuture(String word) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World : " + Thread.currentThread().getName());
            return word + " World";
        });
    }

    @Test
    void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello : " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("World : " + Thread.currentThread().getName());
            return "World";
        });

        CompletableFuture<String> result = helloFuture.thenCombine(worldFuture, (s1, s2) -> s1 + " " + s2);
        System.out.println(result.get());
        //Hello : ForkJoinPool.commonPool-worker-19
        //World : ForkJoinPool.commonPool-worker-5
        //Hello World
    }

    @Test
    void allOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello : " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("World : " + Thread.currentThread().getName());
            return "World";
        });

        List<CompletableFuture<String>> futures = Arrays.asList(helloFuture, worldFuture);
        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        CompletableFuture<List<String>> listCompletableFuture = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));

        List<String> strings = listCompletableFuture.get();
        strings.forEach(System.out::println);
        //World : ForkJoinPool.commonPool-worker-5
        //Hello : ForkJoinPool.commonPool-worker-19
        //Hello
        //World
    }

    @Test
    void anyOf() {
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello : " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("World : " + Thread.currentThread().getName());
            return "World";
        });

        CompletableFuture.anyOf(helloFuture, worldFuture)
                .thenAccept(System.out::println);
        //World : ForkJoinPool.commonPool-worker-5
        //Hello : ForkJoinPool.commonPool-worker-19
        //World
    }

    @Test
    void exceptionally() throws ExecutionException, InterruptedException {
        boolean isError = true;

        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> {
            if (isError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello : " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally((ex) -> {
            System.out.println(ex);
            return "Default Value";
        });

        System.out.println(helloFuture.get());
        //java.util.concurrent.CompletionException: java.lang.IllegalArgumentException
        //Default Value
    }

    @Test
    void handle() throws ExecutionException, InterruptedException {
        boolean isError = true;

        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> {
            if (isError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello : " + Thread.currentThread().getName());
            return "Hello";
        }).handle((normalResult, exception) -> {
            if (exception != null) {
                System.out.println(exception);
                return "Error!";
            }
            return normalResult;
        });

        System.out.println(helloFuture.get());
        //java.util.concurrent.CompletionException: java.lang.IllegalArgumentException
        //Error!
    }
}
