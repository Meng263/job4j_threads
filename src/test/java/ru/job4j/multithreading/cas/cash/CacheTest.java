package ru.job4j.multithreading.cas.cash;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {
    List<Base> bases = List.of(
            new Base(1, "First"),
            new Base(2, "Second"),
            new Base(3, "Third")
    );

    @Test
    public void whenAddElementsThenAdded() {
        Cache cache = new Cache();
        bases.forEach(cache::add);

        assertEquals(bases.get(0), cache.get(1));
        assertEquals(bases.get(1), cache.get(2));
        assertEquals(bases.get(2), cache.get(3));
    }

    @Test
    public void whenRemoveElementThanRemoved() {
        Cache cache = new Cache();
        bases.forEach(cache::add);
        cache.delete(bases.get(0));

        assertNull(cache.get(1));
        assertEquals(bases.get(1), cache.get(2));
        assertEquals(bases.get(2), cache.get(3));
    }

    @Test
    public void whenUpdateElemThanVersionShouldBeIncremented() {
        Cache cache = new Cache();
        bases.forEach(cache::add);
        Base elemForUpdate = bases.get(0);
        String newName = "elemForUpdate";
        elemForUpdate.setName(newName);

        assertTrue(cache.update(elemForUpdate));
        Base extractedUpdated = cache.get(elemForUpdate.getId());
        assertEquals(newName, extractedUpdated.getName());
        assertEquals(1, extractedUpdated.getVersion());
    }

    @Test
    public void whenUpdateNotPresentElementThanShouldReturnFalse() {
        Cache cache = new Cache();
        bases.forEach(cache::add);
        Base elemForUpdate = new Base(10, "Tenth");
        assertFalse(cache.update(elemForUpdate));
    }

}