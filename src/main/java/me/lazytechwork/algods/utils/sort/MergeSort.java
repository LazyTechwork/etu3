package me.lazytechwork.algods.utils.sort;

import me.lazytechwork.algods.utils.ArrayList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class MergeSort<T> {
    private static final int GALLOP_SIZE = 7;

    @Contract(mutates = "param1")
    public void sort(@NotNull ArrayList<T> array, Comparator<T> comparator) {
        sort(array, 0, array.size() - 1, comparator);
    }

    @Contract(mutates = "param1")
    public void sort(@NotNull ArrayList<T> array, int fromIndex, int toIndex, Comparator<T> comparator) {

    }
}
