package ru.job4j.multithreading.thread_pools.simple;

import ru.job4j.multithreading.wait_notify.produser_consumer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);
    private Boolean stopFlag = false;

    ThreadPool() {
        for (int i = 0; i < size; i++) {
            threads.add(new Runner(tasks, stopFlag));
        }
        threads.forEach(Thread::start);
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        stopFlag = true;
        threads.forEach(Thread::interrupt);
    }

    private static class Runner extends Thread {
        private final SimpleBlockingQueue<Runnable> tasks;
        private volatile Boolean stopFlag;

        private Runner(SimpleBlockingQueue<Runnable> tasks, Boolean stopFlag) {
            this.tasks = tasks;
            this.stopFlag = stopFlag;
        }

        @Override
        public void run() {
            Runnable job;
            while (!stopFlag && !Thread.currentThread().isInterrupted()) {
                if ((job = tasks.poll()) != null) {
                    job.run();
                }
            }
        }
    }
}
