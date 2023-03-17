package me.whiteship.java8to11._09_others;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ParallelSortTest {

    @Test
    void sort와_parallelSort를_비교한다() {
        int size = 2000;
        int[] numbers = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

        //sort
        long start = System.nanoTime();
        Arrays.sort(numbers);
        System.out.println("sort() : " + (System.nanoTime() - start));

        //parallelSort
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.sort(numbers);
        System.out.println("parallelSort() : " + (System.nanoTime() - start));

        //result : 3번 이상 돌려본 결과, parallelSort() 가 압도적으로 속도가 빨랐다.
        //sort() : 795048
        //parallelSort() : 163185

        //sort() : 728581
        //parallelSort() : 137470

        //sort() : 1070314
        //parallelSort() : 142133
    }
}
