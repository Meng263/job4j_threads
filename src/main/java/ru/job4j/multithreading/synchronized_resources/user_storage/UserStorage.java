package ru.job4j.multithreading.synchronized_resources.user_storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final List<User> users = new ArrayList<>();

    public synchronized boolean add(User user) {
        return users.add(user);
    }

    public synchronized boolean update(User user) {
        User userFond = users.stream().filter(elem -> elem.getId() == user.getId())
                .findFirst().orElseThrow();

        users.remove(userFond);
        return users.add(user);
    }

    public synchronized boolean delete(User user) {
        return users.remove(user);
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User userFrom = users.stream().filter(user -> user.getId() == fromId)
                .findFirst().orElseThrow();
        User userTo = users.stream().filter(user -> user.getId() == toId)
                .findFirst().orElseThrow();

        int amountFrom = userFrom.getAmount();

        int userFromWithoutDiff = amountFrom - amount;
        if (userFromWithoutDiff < 0) {
            throw new IllegalStateException("Not enough money for transfer from user " + userFrom.getId());
        }

        userFrom.setAmount(userFromWithoutDiff);
        userTo.setAmount(userTo.getAmount() + amount);
    }

}
