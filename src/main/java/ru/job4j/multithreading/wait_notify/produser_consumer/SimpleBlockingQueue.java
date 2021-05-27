package ru.job4j.multithreading.wait_notify.produser_consumer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private final int size;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public void offer(T value) {
        synchronized (this) {
            while (size <= queue.size()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            queue.offer(value);
            this.notify();
        }
    }

    public T poll() {
        T value;
        synchronized (this) {
            try {
                while (queue.isEmpty()) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            value = queue.poll();
            this.notify();
        }
        return value;
    }
}
