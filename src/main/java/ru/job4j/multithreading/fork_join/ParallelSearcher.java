package ru.job4j.multithreading.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

public class ParallelSearcher<T> {
    public int findElement(T[] array, Predicate<T> predicate) {
        int result;
        if (array.length < 10) {
            result = findSerial(array, predicate);
        } else {
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            result = forkJoinPool.invoke(new ParallelSearchTask<>(array, predicate, 0, array.length - 1));
        }
        return result;
    }

    private int findSerial(T[] array, Predicate<T> predicate) {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (predicate.test(array[i])) {
                result = i;
                break;
            }
        }
        return result;
    }
}
