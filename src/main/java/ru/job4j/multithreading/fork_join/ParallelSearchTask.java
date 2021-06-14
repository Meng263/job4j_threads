package ru.job4j.multithreading.fork_join;

import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class ParallelSearchTask<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final Predicate<T> predicate;
    private final int from;
    private final int to;

    public ParallelSearchTask(T[] array, Predicate<T> predicate, int from, int to) {
        this.array = array;
        this.predicate = predicate;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        int result = -1;
        if (from == to) {
            if (predicate.test(array[from])) {
                result = from;
            }
            return result;
        }

        int mid = (from + to) / 2;
        ParallelSearchTask<T> leftTask = new ParallelSearchTask<>(array, predicate, from, mid);
        ParallelSearchTask<T> rightTask = new ParallelSearchTask<>(array, predicate, mid + 1, to);
        leftTask.fork();
        rightTask.fork();

        Integer rightJoin = rightTask.join();
        Integer leftJoint = leftTask.join();

        return Math.max(leftJoint, rightJoin);
    }
}
