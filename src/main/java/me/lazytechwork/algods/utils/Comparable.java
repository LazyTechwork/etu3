package me.lazytechwork.algods.utils;

import org.jetbrains.annotations.NotNull;

public interface Comparable<T> {
    public int compareTo(@NotNull T o);
}
