package me.lazytechwork.algods.utils.sorts;

import me.lazytechwork.algods.utils.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortTestHelper {
    public static void assertArrayListSorted(ArrayList<Integer> array, int[] reference, String message) {
        for (int i = 0, l = array.size(); i < l; i++)
            assertEquals(array.get(i), reference[i], message + " at " + i);
    }
}
