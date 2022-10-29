package me.lazytechwork.algods.utils;

import org.jetbrains.annotations.Nullable;

public class Stack<T> {
    private Node<T> head;

    /**
     * Pushes an item onto the top of this stack
     *
     * @param item the item to be pushed onto this stack
     * @return the item argument
     */
    public T push(@Nullable T item) {
        if (head == null) {
            head = new Node<>(item, null);
        } else {
            head = new Node<>(item, head);
        }
        return item;
    }

    /**
     * Removes the object at the top of this stack and returns that object as the value of this function
     *
     * @return the object at the top of this stack
     */
    public T pop() {
        if (head == null)
            return null;
        T data = head.data;
        head = head.next;
        return data;
    }

    /**
     * Looks at the object at the top of this stack without removing it from the stack
     *
     * @return the object at the top of this stack
     */
    public T peek() {
        return head != null ? head.data : null;
    }

    /**
     * Tests if this stack is empty
     *
     * @return true if and only if this stack contains no items; false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    private static class Node<T> {
        final T data;
        Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
}
