package ru.job4j.multithreading.thread_interrupt;

import java.time.Duration;
import java.util.List;

public class ConsoleProgress implements Runnable {
    List<Character> characters = List.of('|', '/', '-', '\\');

    @Override
    public void run() {
        int charCounter = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Character animationChar = characters.get(charCounter);
                if (charCounter == characters.size() - 1) {
                    charCounter = 0;
                } else {
                    charCounter++;
                }
                System.out.print("\r Loading ... " + animationChar);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ConsoleProgress());
        thread.start();
        Thread.sleep(Duration.ofSeconds(30).toMillis());
        thread.interrupt();
    }
}
