package ru.lazytechwork.algods.utils;

import java.util.function.Predicate;

public interface List<T> {
    /**
     * Returns the number of elements in this list
     *
     * @return the number of elements in this list
     */
    int size();

    /**
     * Returns true if this list contains no elements
     *
     * @return true if this list contains no elements
     */
    boolean isEmpty();

    /**
     * Appends the specified element to the end of this list
     *
     * @param element element to be appended to this list
     * @return true
     */
    boolean add(T element);

    /**
     * Removes the first occurrence of the specified element filter from this list, if it is present.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     *
     * @param predicate element filter to be removed from this list, if present ( it -> it.equals(2) )
     * @return true if this list contained the specified element
     */
    boolean removeIf(Predicate<T> predicate);

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     *
     * @param o element to be removed from this list, if present
     * @return true
     */
    boolean remove(Object o);

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     *
     * @param index index of the element to remove
     * @return true
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    boolean remove(int index);

    /**
     * Returns the element at the specified position in this list
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    T get(int index);

    /**
     * Replaces the element at the specified position in this list with the specified element
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    T set(int index, T element);

    /**
     * Returns true if this list contains the specified element
     *
     * @param o object you need to find in
     * @return is the list contains at least one equals element
     */
    boolean contains(Object o);

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     *
     * @param predicate filter to find element ( it -> it.equals(2) )
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    int indexOfFirst(Predicate<T> predicate);

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     *
     * @param o element to find
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    int indexOf(Object o);

    /**
     * Removes all the elements from this list
     */
    void clear();
}
