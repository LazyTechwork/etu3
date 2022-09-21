package me.lazytechwork.algods.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    private LinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new LinkedList<>();
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
                "Excepted ArrayIndexOutOfBounds, but it didn't"
        );

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(-1),
                "Excepted ArrayIndexOutOfBounds, but it didn't"
        );
    }

    @Test
    @Disabled
    void isListEmptyAfterRemovingLastElement() {
        assertTrue(list.isEmpty());

        list.add(1);
        list.remove(0);
        assertTrue(list.isEmpty());
    }
}
