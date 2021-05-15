package ru.job4j.multithreading.thread_state;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(ThreadState::printGreetings);
        Thread second = new Thread(ThreadState::printGreetings);
        first.start();
        second.start();
        while (isNotTerminated(first) || isNotTerminated(second)) {
            System.out.println(getMessage(first));
            System.out.println(getMessage(second));
        }
        System.out.println("Job completed");
    }

    private static void printGreetings() {
        System.out.println("Hello, this is message from " + Thread.currentThread().getName());
    }

    private static boolean isNotTerminated(Thread first) {
        return first.getState() != Thread.State.TERMINATED;
    }

    private static String getMessage(Thread thread) {
        return thread.getName() + "has state " + thread.getState();
    }
}