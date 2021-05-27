package ru.job4j.multithreading.wait_notify.parralel_serach;

import ru.job4j.multithreading.wait_notify.produser_consumer.SimpleBlockingQueue;

public class ParallelSearch {
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        final Thread consumer = new Thread(
                () -> {
                    Integer value;
                    while ((value = queue.poll()) != null) {
                        System.out.println(value);
                    }
                }
        );

        final Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    consumer.interrupt();
                }

        );
        producer.start();
        consumer.start();
    }
}
