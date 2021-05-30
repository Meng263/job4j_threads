package ru.job4j.multithreading.cas.cash;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public Base get(int key) {
        return memory.get(key);
    }

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        Base updatedValue = memory.computeIfPresent(model.getId(), (key, value) -> {
                    Base stored = memory.get(value.getId());
                    if (stored.getVersion() != value.getVersion()) {
                        throw new OptimisticException("Versions are not equal");
                    }
                    return model.copyAndIncVersion();
                }
        );
        return updatedValue != null && updatedValue.getVersion() != model.getVersion();
    }

    public boolean delete(Base model) {
        return memory.remove(model.getId(), model);
    }

}
