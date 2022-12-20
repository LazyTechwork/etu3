package ru.lazytechwork.algods.utils;

public class GraphBuilder {
    public static Graph fromString(String s) {
        Graph graph = new Graph();
        String[] lines = s.replace("\r", "").split("\n");
        String[] keys = lines[0].trim().split(" +");
        for (int i = 0; i < keys.length; i++)
            graph.addKey(keys[i], i);

        ArrayList<ArrayList<Integer>> graphMatrix = new ArrayList<>(keys.length);
        for (int i = 1; i < lines.length; i++) {
            ArrayList<Integer> weights = new ArrayList<>(keys.length);
            String[] lineValues = lines[i].trim().split(" +");
            for (String value : lineValues)
                weights.add(Integer.parseInt(value));
            graphMatrix.add(weights);
        }

        for (int i = 0, il = graphMatrix.size(); i < il; i++) {
            ArrayList<Integer> row = graphMatrix.get(i);
            for (int j = 0, jl = row.size(); j < jl; j++) {
                Integer weight = row.get(j);
                if (weight != 0)
                    graph.addEdge(new Edge(i, j, weight));
            }
        }
        return graph;
    }
}
