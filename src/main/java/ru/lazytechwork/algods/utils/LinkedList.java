package ru.lazytechwork.algods.utils;

import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class LinkedList<T extends Comparable<T>> implements List<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    public LinkedList() {
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    @Override
    public boolean add(@Nullable T element) {
        Node<T> newNode = new Node<>(element, null);
        if (this.last == null)
            this.first = this.last = newNode;
        else {
            this.last.next = newNode;
            this.last = newNode;
        }
        ++this.size;
        return true;
    }

    @Override
    public boolean removeIf(Predicate<T> predicate) {
        Node<T> current = this.first;
        if (this.size() - 1 == 0) {
            this.first = this.last = null;

            --this.size;
            return true;
        }

        while (current != null && current.next != null && !predicate.test(current.next.data)) {
            current = current.next;
        }

        if (current != null && current.next != null && predicate.test(current.next.data)) {
            current.next = current.next.next;

            --this.size;
            return true;
        }

        return false;
    }

    @Override
    public boolean remove(Object o) {
        return removeIf(it -> it.equals(o));
    }

    @Override
    public boolean remove(int index) {
        if (this.size() - 1 == 0) {
            this.first = this.last = null;
            --this.size;
            return true;
        }

        Node<T> current = getPrevious(index);

        if (current != null && current.next != null) {
            current.next = current.next.next;
            --this.size;
            return true;
        }

        return false;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException(index);
        }
        Node<T> current = this.first;
        for (int i = 0; i <= index; i++) {
            if (i == index)
                return current.data;

            current = current.next;
        }
        return null;
    }

    @Override
    public T set(int index, @Nullable T element) {
        Node<T> current = getPrevious(index);

        if (current != null && current.next != null) {
            T previous = current.next.data;
            current.next.data = element;
            return previous;
        }

        return null;
    }

    private Node<T> getPrevious(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException(index);
        }

        Node<T> current = this.first;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        return current;
    }

    @Override
    public boolean contains(Object o) {
        return indexOfFirst(it -> it.equals(o)) != -1;
    }

    @Override
    public int indexOfFirst(Predicate<T> predicate) {
        Node<T> current = this.first;
        int index = 0;
        while (current != null && !predicate.test(current.data)) {
            current = current.next;
            ++index;
        }

        return current != null && predicate.test(current.data) ? index : -1;
    }

    @Override
    public int indexOf(Object o) {
        return indexOfFirst(it -> it.equals(o));
    }

    @Override
    public void clear() {
        this.first = this.last = null;
        this.size = 0;
    }

    public int recalculateSize() {
        Node<T> current = this.first;
        int result = current == null ? 0 : 1;

        while (current != null && current.next != null) {
            current = current.next;
            ++result;
        }

        this.size = result;

        return result;
    }

    private static class Node<T> {
        Node<T> next;
        T data;

        public Node(@Nullable T data, @Nullable Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
}
