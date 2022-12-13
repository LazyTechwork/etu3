package ru.lazytechwork.algods.utils.sorts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.lazytechwork.algods.utils.ArrayList;
import ru.lazytechwork.algods.utils.sort.InsertionSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static ru.lazytechwork.algods.utils.sorts.SortTestHelper.assertArrayListSorted;

public class InsertionSortTest {
    static final int COUNT = 5000;
    static final int[] UNSORTED_LIST = new int[COUNT];
    static int[] SORTED_LIST;
    static final Random RANDOM = new Random();
    static final InsertionSort<Integer> INSERTION_SORT = new InsertionSort<>();

    @BeforeAll
    static void beforeAll() {
        for (int i = 0; i < COUNT; i++)
            UNSORTED_LIST[i] = RANDOM.nextInt(0, 100000);
        SORTED_LIST = Arrays.stream(UNSORTED_LIST).sorted().toArray();
    }

    @Test
    void isSortingRight() {
        ArrayList<Integer> array = new ArrayList<>(COUNT);
        for (int i = 0; i < COUNT; i++)
            array.add(UNSORTED_LIST[i]);

        INSERTION_SORT.sort(array, Comparator.comparingInt(a -> a));
        assertArrayListSorted(array, SORTED_LIST, "Insertion sort failed");
    }
}
