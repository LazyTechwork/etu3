package me.lazytechwork.algods.utils;

import org.jetbrains.annotations.Nullable;

public class LinkedList<T> implements List<T> {
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
        Node<T> newNode = new Node<>(element);
        if (this.last == null)
            this.first = this.last = newNode;
        else {
            this.last.setNext(newNode);
            this.last = newNode;
        }
        ++this.size;
        return true;
    }

    @Override
    public boolean remove(Filter filter) {
        Node<T> current = this.first;
        if (this.size() - 1 == 0) {
            this.first = this.last = null;

            --this.size;
            return true;
        }

        while (current != null && current.getNext() != null && !filter.prove(current.getNext().getData())) {
            current = current.getNext();
        }

        if (current != null && current.getNext() != null && filter.prove(current.getNext().getData())) {
            current.setNext(current.getNext().getNext());

            --this.size;
            return true;
        }

        return false;
    }

    @Override
    public boolean remove(int index) {
        if (this.size() - 1 == 0) {
            this.first = this.last = null;
            --this.size;
            return true;
        }

        Node<T> current = getPrevious(index);

        if (current != null && current.getNext() != null) {
            current.setNext(current.getNext().getNext());
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
                return current.getData();

            current = current.getNext();
        }
        return null;
    }

    @Override
    public T set(int index, @Nullable T element) {
        Node<T> current = getPrevious(index);

        if (current != null && current.getNext() != null) {
            T previous = current.getNext().getData();
            current.getNext().setData(element);
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
            current = current.getNext();
        }

        return current;
    }

    @Override
    public boolean contains(Filter filter) {
        return this.indexOf(filter) != -1;
    }

    @Override
    public int indexOf(Filter filter) {
        Node<T> current = this.first;
        int index = 0;
        while (current != null && !filter.prove(current.getData())) {
            current = current.getNext();
            ++index;
        }

        return current != null && filter.prove(current.getData()) ? index : -1;
    }

    @Override
    public void clear() {
        this.first = this.last = null;
        this.size = 0;
    }

    public int recalculateSize() {
        Node<T> current = this.first;
        int result = current == null ? 0 : 1;

        while (current != null && current.getNext() != null) {
            current = current.getNext();
            ++result;
        }

        this.size = result;

        return result;
    }

    private static class Node<T> {
        private Node<T> next;
        private T data;

        public Node(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public Node<T> setNext(@Nullable Node<T> next) {
            this.next = next;
            return this;
        }

        public T getData() {
            return data;
        }

        public Node<T> setData(@Nullable T data) {
            this.data = data;
            return this;
        }
    }
}
