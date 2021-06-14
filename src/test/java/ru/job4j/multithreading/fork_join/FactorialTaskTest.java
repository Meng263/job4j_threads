package ru.job4j.multithreading.fork_join;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorialTaskTest {
    @Test
    public void factorialTest() {
        int result = FactorialTask.evaluateFactorial(8);
        assertEquals(result, 40320);
    }
}