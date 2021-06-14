package ru.job4j.multithreading.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Integer> {
    private final int number;

    public FactorialTask(int number) {
        this.number = number;
    }

    @Override
    protected Integer compute() {
        if (number == 0 || number == 1) return 1;
        FactorialTask ft = new FactorialTask(number - 1);
        ft.fork();
        return number * ft.join();
    }

    public static int evaluateFactorial(int number) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new FactorialTask(number));
    }
}
