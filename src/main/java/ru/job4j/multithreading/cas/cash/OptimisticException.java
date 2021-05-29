package ru.job4j.multithreading.cas.cash;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}

