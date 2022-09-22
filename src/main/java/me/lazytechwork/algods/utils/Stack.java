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
        if (this.head == null) {
            this.head = new Node<>(item);
        } else {
            this.head = new Node<>(item).setPrevious(this.head);
        }
        return item;
    }

    /**
     * Removes the object at the top of this stack and returns that object as the value of this function
     *
     * @return the object at the top of this stack
     */
    public T pop() {
        T data = this.head.getData();
        this.head = this.head.getPrevious();
        return data;
    }

    /**
     * Looks at the object at the top of this stack without removing it from the stack
     *
     * @return the object at the top of this stack
     */
    public T peek() {
        return this.head.getData();
    }

    /**
     * Tests if this stack is empty
     *
     * @return true if and only if this stack contains no items; false otherwise
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    private static class Node<T> {
        private Node<T> previous;
        private T data;

        public Node(T data) {
            this.data = data;
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public Node<T> setPrevious(@Nullable Node<T> previous) {
            this.previous = previous;
            return this;
        }

        public T getData() {
            return data;
        }
    }
}
