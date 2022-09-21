package me.lazytechwork.algods.utils;

public interface List<T> {
    /**
     * @return size of a list
     */
    int size();

    /**
     * @return is this list empty
     */
    boolean isEmpty();

    /**
     * Adds element to the end or beginning of the list implementation
     *
     * @param element element to add
     * @return is element added
     */
    boolean add(T element);

    /**
     * @param element need to find and remove from the list
     * @return is element removed
     */
    boolean remove(Object element);

    /**
     * Remove element by it's index
     *
     * @param index index of an element in the list
     * @return true
     */
    boolean remove(int index);

    /**
     * Get the element of the list by it's index
     *
     * @param index index of element
     * @return element stands on index
     */
    T get(int index);

    /**
     * Set an element on the position
     *
     * @param index   index of an element
     * @param element element to add
     * @return previous element
     */
    T set(int index, T element);

    /**
     * Check is the list contains at least one equals element
     *
     * @param element element need to find
     * @return is the list contains at least one equals element
     */
    boolean contains(Object element);

    /**
     * Get index of the first concurrency of the object in the list
     *
     * @param element element need to find
     * @return index of element
     */
    int indexOf(Object element);

    /**
     * Clears the list
     */
    void clear();

    /**
     * Sorts the list on place
     */
    void sort();
}
