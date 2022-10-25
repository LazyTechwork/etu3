package me.lazytechwork.algods.utils.sort;


import me.lazytechwork.algods.utils.List;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TimSort {
    private static int MIN_MERGE = 64;

    @Contract(pure = true)
    public static @Nullable List sort(@NotNull List list) {
        return null;
    }

    private static int getMinRunLength(int n) {
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= n & 1;
            n >>= 1;
        }
        return n + r;
    }
}
