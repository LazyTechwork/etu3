package me.lazytechwork.algods.utils;

import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
    /**
     * The coefficient of size incrementation when the array is overflown
     */
    public static final double INCREMENTATION_COEFFICIENT = 1.5D;
    public static final int DEFAULT_CAPACITY = 10;
    private transient Object[] data;
    private int size = 0;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int size) {
        this.data = new Object[size];
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * Get current data array capacity
     *
     * @return capacity
     */
    public int capacity() {
        return this.data.length;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean add(T element) {
        if (this.size + 1 > this.data.length) {
            Object[] newArray = new Object[(int) Math.round((this.size + 1) * INCREMENTATION_COEFFICIENT)];

            for (int i = 0; i < data.length; i++) {
                newArray[i] = data[i];
            }

            newArray[this.size] = element;
            ++this.size;
            this.data = newArray;
        } else {
            this.data[this.size] = element;
            ++this.size;
        }
        return true;
    }

    @Override
    public boolean removeIf(Predicate<T> predicate) {
        return remove(indexOfFirst(predicate));
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }

        for (int i = index + 1; i < data.length; i++)
            data[i - 1] = data[i];

        --this.size;
        return true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }

        return (T) this.data[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        T previous = (T) this.data[index];

        this.data[index] = element;
        return previous;
    }

    @Override
    public boolean contains(Object o) {
        return indexOfFirst(it -> it.equals(o)) != -1;
    }

    @Override
    public int indexOfFirst(Predicate<T> predicate) {
        // TODO generic type check
        for (int i = 0; i < this.size; i++)
            if (predicate.test((T) this.data[i]))
                return i;

        return -1;
    }

    @Override
    public boolean remove(Object o) {
        return remove(indexOfFirst(it -> it.equals(o)));
    }

    @Override
    public int indexOf(Object o) {
        return indexOfFirst(it -> it.equals(o));
    }

    @Override
    public void clear() {
        for (int i = 0; i < data.length; i++) {
            data[i] = null;
        }
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
}
