package ru.job4j.multithreading.cas;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CASCounterTest {
    @Test
    public void singleThreadTest() {
        CASCounter counter = new CASCounter();
        counter.increment();
        counter.increment();
        counter.increment();

        assertEquals(3, counter.get());
    }

    @Test
    public void multiThreadTest() throws InterruptedException {
        CASCounter counter = new CASCounter();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    counter.increment();
                }
            }
            );
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(100, counter.get());
    }

}