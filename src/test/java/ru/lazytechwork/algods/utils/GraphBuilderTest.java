package ru.lazytechwork.algods.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphBuilderTest {

    @Test
    void fromString() {
        final String input = "A  B  C\n" +
                "0  3  1\n" +
                "3  0  2\n" +
                "1  2  0";
        Graph graph = GraphBuilder.fromString(input);

        Edge e = graph.getEdge("A", "B");
        assertNotNull(e);
        assertEquals(3, e.w);

        e = graph.getEdge("B", "A");
        assertNotNull(e);
        assertEquals(3, e.w);

        e = graph.getEdge("C", "A");
        assertNotNull(e);
        assertEquals(1, e.w);

        e = graph.getEdge("B", "C");
        assertNotNull(e);
        assertEquals(2, e.w);
    }
}