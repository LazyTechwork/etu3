package ru.lazytechwork.algods.utils.tree;

import org.junit.jupiter.api.Test;
import ru.lazytechwork.algods.utils.ArrayList;
import ru.lazytechwork.core.exceptions.InvalidTreeSequence;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeFromStringTest {
    @Test
    void canBuildTreeFromString() throws InvalidTreeSequence {
        BinaryTree<Integer> bt = BinaryTree.fromString("(8 (9 (5)) (1))");

        ArrayList<Integer> array = bt.prefixTraverse();
        assertNotNull(array);
        assertTrue(array.contains(8));
        assertTrue(array.contains(9));
        assertTrue(array.contains(5));
        assertTrue(array.contains(1));
        assertEquals(4, array.size());
    }
}
