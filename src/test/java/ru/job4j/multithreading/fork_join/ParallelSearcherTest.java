package ru.job4j.multithreading.fork_join;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelSearcherTest {
    @Test
    public void searchWhenLessThan10Elements() {
        String[] strings = {"One", "Two", "Three", "Four", "Five"};

        ParallelSearcher<String> searcher = new ParallelSearcher<>();
        int index = searcher.findElement(strings, s -> s.startsWith("Th"));

        assertEquals(index, 2);
    }


    @Test
    public void searchWhenMoreThan10Elements() {
        String[] strings = IntStream.range(0, 999)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        ParallelSearcher<String> searcher = new ParallelSearcher<>();
        int index = searcher.findElement(strings, s -> s.equals("997"));

        assertEquals(index, 997);
    }

}