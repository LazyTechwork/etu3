package ru.lazytechwork.algods.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MSTFinderTest {

    private final String input = "A  B  C\n" +
            "0  3  1\n" +
            "3  0  2\n" +
            "1  2  0";
    private final String output = "A C" + System.lineSeparator() +
            "B C" + System.lineSeparator() +
            "3";

    private final String complicatedInput = "A B C D E F G H\n" +
            "0 1 0 0 3 0 0 0\n" +
            "1 0 1 0 2 2 0 0\n" +
            "0 1 0 3 0 3 0 2\n" +
            "0 0 3 0 0 0 0 1\n" +
            "3 2 0 0 0 4 0 0\n" +
            "0 2 3 0 4 0 5 0\n" +
            "0 0 0 0 0 5 0 0\n" +
            "0 0 2 1 0 0 0 0";
    private final String complicatedOutput = "A B" + System.lineSeparator() +
            "B C" + System.lineSeparator() +
            "B E" + System.lineSeparator() +
            "B F" + System.lineSeparator() +
            "C H" + System.lineSeparator() +
            "D H" + System.lineSeparator() +
            "F G" + System.lineSeparator() +
            "14";

    @Test
    void mstKruskal() {
        Graph graph = GraphBuilder.fromString(input);

        MSTFinder.MinimalSpanningTree mst = MSTFinder.mstKruskal(graph.getEdges());
        assertEquals(output, MSTFinder.kruskalResult(mst));
    }

    @Test
    void moreComplicatedMstKruskal() {
        Graph graph = GraphBuilder.fromString(complicatedInput);
        MSTFinder.MinimalSpanningTree mst = MSTFinder.mstKruskal(graph.getEdges());
        assertEquals(complicatedOutput, MSTFinder.kruskalResult(mst));
    }

    @Test
    void fromFile() throws IOException {
        Graph graph = GraphBuilder.fromFile(Path.of("./kruskalTest.txt"));
        MSTFinder.MinimalSpanningTree mst = MSTFinder.mstKruskal(graph.getEdges());
        System.out.println(MSTFinder.kruskalResult(mst));
    }
}