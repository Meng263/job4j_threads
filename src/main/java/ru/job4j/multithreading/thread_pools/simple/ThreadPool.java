package ru.job4j.multithreading.thread_pools.simple;

import ru.job4j.multithreading.wait_notify.produser_consumer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            threads.add(new Runner(tasks));
        }
        threads.forEach(Thread::start);
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    private static class Runner extends Thread {
        private final SimpleBlockingQueue<Runnable> tasks;

        private Runner(SimpleBlockingQueue<Runnable> tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {
            Runnable job;
            while (!Thread.currentThread().isInterrupted()) {
                if ((job = tasks.poll()) != null) {
                    job.run();
                }
            }
        }
    }
}
