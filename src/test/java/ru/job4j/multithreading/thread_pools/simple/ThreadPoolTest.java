package ru.job4j.multithreading.thread_pools.simple;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreadPoolTest {
    @Test
    public void whenAdd100TasksAllShouldBeFinished() throws InterruptedException {
        final var threadPoll = new ThreadPool();
        final var counter = new AtomicInteger(0);

        for (int i = 0; i < 100; i++) {
            threadPoll.work(counter::incrementAndGet);
        }
        Thread.sleep(500);
        assertEquals(100, counter.get());
    }

    @Test
    public void shouldDownShouldBeWork() {
        final var threadPoll = new ThreadPool();
        final var counter = new AtomicInteger(0);

        for (int i = 0; i < 100; i++) {
            threadPoll.work(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        threadPoll.work(counter::incrementAndGet);

        threadPoll.shutdown();

        assertEquals(0, counter.get());
    }
}