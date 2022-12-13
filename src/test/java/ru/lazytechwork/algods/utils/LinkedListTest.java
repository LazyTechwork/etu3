package ru.lazytechwork.algods.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    private final LinkedList<Integer> list = new LinkedList<>();

    @AfterEach
    void tearDown() {
        list.clear();
    }

    @Test
    void canAddItemToTheList() {
        list.add(1);
        assertEquals(1, list.size());
    }

    @Test
    void canRemoveItemFromTheListByIndex() {
        for (int i = 0; i <= 5; i++)
            list.add(i);

        assertEquals(6, list.size());
        assertEquals(3, list.get(3));
        assertEquals(5, list.get(5));

        list.remove(3);

        assertEquals(5, list.size());
        assertEquals(4, list.get(3));
    }

    @Test
    void getOutOfBoundsThrowsException() {
        for (int i = 0; i <= 5; i++)
            list.add(i);

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(list.size()),
                "Excepted IndexOutOfBounds, but it didn't"
        );

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(-1),
                "Excepted IndexOutOfBounds, but it didn't"
        );
    }

    @Test
    void isListEmptyAfterRemovingLastElement() {
        assertTrue(list.isEmpty());

        assertTrue(list.add(1));
        assertTrue(list.remove(0));
        assertTrue(list.isEmpty());
    }

    @Test
    void canRemoveItemFromTheListWithFilter() {
        for (int i = 0; i <= 5; i++)
            list.add(i);

        assertTrue(list.add(67));

        for (int i = 0; i <= 5; i++)
            list.add(i);

        assertEquals(13, list.size());
        assertEquals(67, list.get(6));

        assertTrue(list.remove(Integer.valueOf(67)));

        assertEquals(12, list.size());
        assertEquals(0, list.get(6));

        list.clear();

        assertEquals(0, list.size());
        list.add(11);
        assertTrue(list.remove(Integer.valueOf(11)));
        assertEquals(0, list.size());
    }

    @Test
    void findIndexOfElement() {
        for (int i = 0; i <= 25; i++)
            list.add(i);

        assertEquals(21, list.indexOf(21));
        assertEquals(9, list.indexOf(9));

        assertEquals(-1, list.indexOf(100));
    }

    @Test
    void isListContainsElement() {
        for (int i = 0; i <= 25; i++)
            list.add(i);

        assertTrue(list.contains(24));
        assertFalse(list.contains(27));
    }

    @Test
    void recalculateSize() {
        for (int i = 0; i <= 25; i++)
            list.add(i);

        assertEquals(26, list.size());
        assertEquals(26, list.recalculateSize());
    }

    @Test
    void setItem() {
        for (int i = 0; i <= 25; i++)
            list.add(i);

        assertEquals(16, list.set(16, 77));
        assertEquals(77, list.get(16));
        assertEquals(26, list.recalculateSize());
    }
}
