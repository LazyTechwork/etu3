package ru.lazytechwork.algods.utils;

import java.util.Map;

public class Graph {
    ArrayList<Edge> edges;
    Map<Integer, String> keys;

    public void addEdge(String fromKey, String toKey, Edge e) {
        edges.add(e);
        keys.put(e.u, fromKey);
        keys.put(e.v, toKey);
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public void addKeys(Map<Integer, String> keys) {
        this.keys.putAll(keys);
    }

    public void addKey(String key, Integer id) {
        this.keys.put(id, key);
    }

    public String getKey(Integer id) {
        return this.keys.get(id);
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
}
