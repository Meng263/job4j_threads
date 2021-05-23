package ru.job4j.multithreading.synchronized_resources.user_storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserStorageTest {
    @Test
    public void whenTransferFromFirstToSecondThenTransferred() {
        UserStorage userStorage = new UserStorage();
        User userFirst = new User(1, 100);
        User userSecond = new User(2, 200);
        userStorage.add(userFirst);
        userStorage.add(userSecond);

        userStorage.transfer(1, 2, 50);

        assertEquals(userFirst.getAmount(), 50);
        assertEquals(userSecond.getAmount(), 250);
    }

}