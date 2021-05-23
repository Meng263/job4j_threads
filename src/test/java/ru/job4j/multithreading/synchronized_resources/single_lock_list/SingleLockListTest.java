package ru.job4j.multithreading.synchronized_resources.single_lock_list;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SingleLockListTest {
    @Test
    public void add() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>();
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertEquals(rsl, Set.of(1, 2));
    }
}