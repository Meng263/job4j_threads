package ru.job4j.multithreading.synchronized_resources.single_lock_list;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

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

    @Test
    public void iteratorTest() {
        SingleLockList<Integer> list = new SingleLockList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> iterator = list.iterator();
        list.add(4);

        List<Integer> expectedList = List.of(1, 2, 3);
        List<Integer> resultList = new ArrayList<>();
        iterator.forEachRemaining(resultList::add);
        assertIterableEquals(expectedList, resultList);
    }
}