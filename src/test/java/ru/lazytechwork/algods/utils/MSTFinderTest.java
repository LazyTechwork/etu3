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
                "3", MSTFinder.kruskalResult(mst));
    }

    @Test
    void moreComplicatedMstKruskal() {
        final String input = "A B C D E F G H\n" +
                "0 1 0 0 3 0 0 0\n" +
                "1 0 1 0 2 2 0 0\n" +
                "0 1 0 3 0 3 0 2\n" +
                "0 0 3 0 0 0 0 1\n" +
                "3 2 0 0 0 4 0 0\n" +
                "0 2 3 0 4 0 5 0\n" +
                "0 0 0 0 0 5 0 0\n" +
                "0 0 2 1 0 0 0 0";
        Graph graph = GraphBuilder.fromString(input);
        MSTFinder.MinimalSpanningTree mst = MSTFinder.mstKruskal(graph.getEdges());
        assertEquals("A B" + System.lineSeparator() +
                "B C" + System.lineSeparator() +
                "B E" + System.lineSeparator() +
                "B F" + System.lineSeparator() +
                "C H" + System.lineSeparator() +
                "D H" + System.lineSeparator() +
                "F G" + System.lineSeparator() +
                "14", MSTFinder.kruskalResult(mst));
    }
}