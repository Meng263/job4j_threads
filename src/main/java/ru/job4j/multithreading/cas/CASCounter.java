package ru.job4j.multithreading.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCounter {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        count.getAndUpdate(integer -> ++integer);
    }

    public int get() {
        return count.get();
    }
}
