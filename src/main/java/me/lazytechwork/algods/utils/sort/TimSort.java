package me.lazytechwork.algods.utils.sort;


import me.lazytechwork.algods.utils.ArrayList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

public class TimSort<T> {
    private static final int MIN_MERGE = 64;
    private static final int GALLOP_SIZE = 7;
    private final InsertionSort<T> INSERTION_SORT_INSTANCE = new InsertionSort<>();

    @Contract(mutates = "param1")
    public void sort(@NotNull ArrayList<T> array, Comparator<T> comparator) {

    }

    @Contract(mutates = "param1")
    private void reverseArrayList(@NotNull ArrayList<T> array, int fromIndex, int toIndex) {
        int n = (toIndex - fromIndex) / 2;
        for (int i = 0; i <= n; ++i) {
            T temp = array.get(toIndex - i);
            array.set(toIndex - i, array.get(toIndex + i));
            array.set(toIndex + i, temp);
        }
    }

    @Contract(mutates = "param1")
    private @Nullable Run nextRun(@NotNull ArrayList<T> array, int currentIndex, int minRun, Comparator<T> comparator) {
        int diff = array.size() - currentIndex;

        if (diff < 0 || diff == 0)
            return null;

        if (diff == 1)
            return new Run(currentIndex, 1);

        int startIndex = currentIndex;

        T el1 = array.get(currentIndex++);
        T el2 = array.get(currentIndex++);

        int size = 2;

        boolean invert = comparator.compare(el1, el2) < 0;

        if (invert) {
            for (
                    T prev = el2;
                    currentIndex < array.size()
                            && comparator.compare(prev, array.get(currentIndex)) >= 0;
                    prev = array.get(currentIndex++), size++
            )
                ;
            reverseArrayList(array, startIndex, currentIndex - 1);
        } else
            for (
                    T prev = el2;
                    currentIndex < array.size()
                            && comparator.compare(prev, array.get(currentIndex)) >= 0;
                    prev = array.get(currentIndex++), size++
            )
                ;


        if (size < minRun) {
            while (currentIndex++ < array.size() && size++ < minRun) ;

            if (currentIndex - 1 == array.size())
                size++;
            size--;
        }

        INSERTION_SORT_INSTANCE.sort(array, startIndex, startIndex + size - 1, comparator);
        return new Run(startIndex, size);
    }

    private static class Run {
        public int startIndex;
        public int size;

        public Run(int startIndex, int size) {
            this.startIndex = startIndex;
            this.size = size;
        }
    }

    private static int getMinRun(int n) {
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= n & 1;
            n >>= 1;
        }
        return n + r;
    }
}
