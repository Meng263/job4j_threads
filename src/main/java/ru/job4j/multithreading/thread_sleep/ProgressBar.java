package ru.job4j.multithreading.thread_sleep;

import java.time.Duration;

public class ProgressBar {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 100; i++) {
                            System.out.print("\rLoading " + i + "%");
                            Thread.sleep(Duration.ofSeconds(1).toMillis());
                        }
                        System.out.println("Loaded.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
    }
}
