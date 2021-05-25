package ru.job4j.multithreading.wait_notify.produser_consumer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleBlockingQueueTest {
    @Test
    public void sendAndReceive10Int() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        List<Integer> source = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> target = new ArrayList<>();

        Thread producer = new Thread(() -> {
            source.forEach(integer -> {
                        queue.offer(integer);
                        System.out.println(integer + " produced");
                    }
            );
        }
        );

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Integer value = queue.poll();
                target.add(value);
                System.out.println(value + " consumed");
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.interrupt();
        consumer.join();

        assertEquals(source, target);
    }
}