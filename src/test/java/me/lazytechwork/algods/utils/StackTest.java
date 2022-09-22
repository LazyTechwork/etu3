package me.lazytechwork.algods.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StackTest {
    private Stack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    @Test
    void canPush() {
        assertEquals(87, stack.push(87));
    }

    @Test
    void canPeek() {
        stack.push(87);
        assertEquals(87, stack.peek());
        stack.push(46);
        assertEquals(46, stack.peek());
        assertEquals(46, stack.peek());
    }

    @Test
    void canPop() {
        stack.push(87);
        assertEquals(87, stack.pop());
        assertTrue(stack.isEmpty());
        stack.push(87);
        stack.push(46);
        stack.push(99);
        assertEquals(99, stack.pop());
        assertEquals(46, stack.pop());
        assertEquals(87, stack.pop());
        assertTrue(stack.isEmpty());
    }
}
