package me.lazytechwork.algods.utils.sort;


import me.lazytechwork.algods.utils.List;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class TimSort {
    private static int MIN_MERGE = 64;

    @Contract(pure = true)
    public static @Nullable List<?> sort(@NotNull List<?> list) {
        Iterator<?> iterator = list.iterator();
        return null;
    }

    private static int getMinrun(int n) {
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= n & 1;
            n >>= 1;
        }
        return n + r;
    }
}
