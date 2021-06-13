package ru.job4j.multithreading.mailing;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification implements AutoCloseable {
    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final EmailTemplate template = new EmailTemplate();

    public void emailTo(User user) {
        pool.submit(() -> send(template.getSubject(user), template.getBody(user), user.getEmail()));
    }

    public void send(String subject, String body, String email) {
    }

    public void close() {
        pool.shutdown();
    }
}
