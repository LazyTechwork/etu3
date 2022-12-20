package ru.lazytechwork.algods.utils;

public class Graph {
    ArrayList<Edge> edges = new ArrayList<>();

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public Edge getEdge(String from, String to) {
        return edges.get(edges.indexOfFirst(e -> e.uKey.equals(from) && e.vKey.equals(to)));
    }

    public Edge getEdge(Integer from, Integer to) {
        return edges.get(edges.indexOfFirst(e -> e.u == from && e.v == to));
    }
}
