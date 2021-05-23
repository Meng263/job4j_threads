package ru.job4j.multithreading.synchronized_resources.user_storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        if (users.get(user.getId()) != null) return false;

        return users.put(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        if (users.get(user.getId()) == null) return false;

        return users.put(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return users.remove(user.getId()) != null;
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User userFrom = users.get(fromId);
        User userTo = users.get(toId);
        if (userFrom == null || userTo == null) throw new IllegalStateException("userFrom and userTo should not be null!");

        int amountFrom = userFrom.getAmount();

        int userFromWithoutDiff = amountFrom - amount;
        if (userFromWithoutDiff < 0) {
            throw new IllegalStateException("Not enough money for transfer from user " + userFrom.getId());
        }

        userFrom.setAmount(userFromWithoutDiff);
        userTo.setAmount(userTo.getAmount() + amount);
    }
}
