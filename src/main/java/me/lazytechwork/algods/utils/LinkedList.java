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
        return false;
    }

    @Override
    public boolean remove(@Nullable Object element) {
        Node<T> current = this.first;
        while (current != null && current.getNext() != null && !current.getNext().getData().equals(element)) {
            current = current.getNext();
        }
        // TODO Проверка на последний элемент в списке,
        //  если да, то first = last,
        //  если удаляем единственный элемент, то first = last = null
        if (current != null) {
            if (current.getNext() != null) {
                if (current.getNext().getData().equals(element)) {
                    --this.size;
                    current.setNext(current.getNext().getNext());
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean remove(int index) {
        Node<T> current = getPrevious(index);

        if (current != null && current.getNext() != null)
            current.setNext(current.getNext().getNext());

        --this.size;
        return false;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException(index);
        }
        Node<T> current = this.first;
        for (int i = 0; i <= index; i++) {
            if (current == null)
                break;

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
            if (current == null)
                break;

            current = current.getNext();
        }

        return current;
    }

    @Override
    public boolean contains(@Nullable Object element) {
        return this.indexOf(element) != -1;
    }

    @Override
    public int indexOf(@Nullable Object element) {
        Node<T> current = this.first;
        int index = 0;
        while (current != null && !current.getData().equals(element)) {
            current = current.getNext();
            ++index;
        }

        return current != null && current.getData().equals(element) ? index : -1;
    }

    @Override
    public void clear() {
        this.first = this.last = null;
    }

    @Override
    public void sort() {

    }

    private void recalculateSize() {
        Node<T> current = this.first;
        int result = 0;

        while (current.getNext() != null) {
            current = current.getNext();
            ++result;
        }

        this.size = result;
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
