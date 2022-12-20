package ru.lazytechwork.algods.utils;

import java.util.HashMap;
import java.util.Map;

public class Graph {
    ArrayList<Edge> edges = new ArrayList<>();
    HashMap<Integer, String> keys = new HashMap<>();
    HashMap<String, Integer> reversedKeys = new HashMap<>();

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
        keys.forEach((id, key) -> this.reversedKeys.put(key, id));
    }

    public void addKey(String key, Integer id) {
        this.keys.put(id, key);
        this.reversedKeys.put(key, id);
    }

    public String getKey(Integer id) {
        return this.keys.get(id);
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public Edge getEdge(String from, String to) {
        return getEdge(reversedKeys.get(from), reversedKeys.get(to));
    }

    public Edge getEdge(Integer from, Integer to) {
        return edges.get(edges.indexOfFirst(e -> e.u == from && e.v == to));
    }
}
