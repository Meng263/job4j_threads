package ru.job4j.multithreading.common_resources.singleton;

public final class DCLSingleton {
    private static DCLSingleton inst;

    public static DCLSingleton instOf() {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        return inst;
    }


    private DCLSingleton() {
    }
}
