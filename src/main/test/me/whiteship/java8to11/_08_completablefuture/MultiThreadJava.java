package me.whiteship.java8to11._08_completablefuture;

import org.junit.jupiter.api.Test;

public class MultiThreadJava {

    @Test
    void Thread를_상속받아_구현하는_방법() {
        HelloThread helloThread = new HelloThread();
        helloThread.start();

        System.out.println("Current Thread : " + Thread.currentThread().getName());
        //New Thread : Thread-0
        //Current Thread : main

        //Current Thread : main
        //New Thread : Thread-0
    }

    static class HelloThread extends Thread {
        @Override
        public void run() {
            System.out.println("New Thread : " + Thread.currentThread().getName());
        }
    }

    @Test
    void Runnable로_구현하거나_람다를_쓰는_방법() {
        Thread thread = new Thread(() -> System.out.println("New Thread" + Thread.currentThread().getName()));
        thread.start();

        System.out.println("Current Thread : " + Thread.currentThread().getName());
        //New ThreadThread-0
        //Current Thread : main

        //Current Thread : main
        //New ThreadThread-0
    }

    @Test
    void 쓰레드_깨우기() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("New Thread" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("wake!");
                    return;
                }
            }
        });
        thread.start();

        System.out.println("Current Thread : " + Thread.currentThread().getName());
        Thread.sleep(3000L);
        thread.interrupt();
    }

    @Test
    void 쓰레드_기다리기() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("New Thread" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                System.out.println("wake!");
                return;
            }
        });
        thread.start();

        System.out.println("Current Thread : " + Thread.currentThread().getName());
        thread.join();
        System.out.println("thread finished! = " + thread.getName());

        //New ThreadThread-0
        //Current Thread : main
        //thread finished! = Thread-0
    }
}
