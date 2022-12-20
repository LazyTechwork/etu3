package ru.lazytechwork.algods.utils;

import org.jetbrains.annotations.NotNull;

public class Edge implements Comparable<Edge> {
    /**
     * From vertex
     */
    int u;
    /**
     * To vertex
     */
    int v;
    int w;
    String uKey;
    String vKey;

    public Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public Edge(int u, int v, int w, String uKey, String vKey) {
        this.u = u;
        this.v = v;
        this.w = w;
        this.uKey = uKey;
        this.vKey = vKey;
    }

    @Override
    public int compareTo(@NotNull Edge edge) {
        if (w != edge.w) return w < edge.w ? -1 : 1;
        return 0;
    }
}
