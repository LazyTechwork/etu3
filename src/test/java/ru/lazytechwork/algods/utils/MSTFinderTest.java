package ru.lazytechwork.algods.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MSTFinderTest {

    @Test
    void mstKruskal() {
        final String input = "A  B  C\n" +
                "0  3  1\n" +
                "3  0  2\n" +
                "1  2  0";
        Graph graph = GraphBuilder.fromString(input);

        MSTFinder.MinimalSpanningTree mst = MSTFinder.mstKruskal(graph.getEdges());
        assertEquals("A C" + System.lineSeparator() +
                "B C" + System.lineSeparator() +
                "3", MSTFinder.kruskalResult(graph, mst));
    }
}