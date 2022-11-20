package me.lazytechwork.algods.utils.sort;

import me.lazytechwork.algods.utils.ArrayList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class InsertionSort<T> {

    @Contract(mutates = "param1")
    public void sort(@NotNull ArrayList<T> array, Comparator<T> comparator) {
        sort(array, 0, array.size() - 1, comparator);
    }

    @Contract(mutates = "param1")
    public void sort(@NotNull ArrayList<T> array, int fromIndex, int toIndex, Comparator<T> comparator) {
        int n = toIndex - fromIndex + 1;
        for (var i = 1; i < n; ++i) {
            T key = array.get(fromIndex + i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(array.get(fromIndex + j), key) > 0) {
                array.set(fromIndex + j + 1, array.get(fromIndex + j));
                --j;
            }
            array.set(fromIndex + j + 1, key);
        }
    }
}
