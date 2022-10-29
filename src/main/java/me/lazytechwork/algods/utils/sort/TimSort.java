package me.lazytechwork.algods.utils.sort;


import me.lazytechwork.algods.utils.ArrayList;
import me.lazytechwork.algods.utils.List;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

public class TimSort<T> {
    private static final int MIN_MERGE = 64;
    private static final int GALLOP_SIZE = 7;

    @Contract(pure = true)
    public @Nullable List<?> sort(@NotNull ArrayList<T> array) {
        return null;
    }

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

        if (invert)
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
        currentIndex = startIndex + size;

        return new Run(startIndex, size);
        // InsertionSort(array, startIndex, startIndex + size - 1, comparator);
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
