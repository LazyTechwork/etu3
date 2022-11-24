package me.lazytechwork.algods.utils.tree;

import me.lazytechwork.algods.utils.ArrayList;
import me.lazytechwork.core.exceptions.InvalidTreeSequence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeFromStringTest {
    @Test
    void canBuildTreeFromString() throws InvalidTreeSequence {
        BinaryTree<Integer> bt = BinaryTree.fromString("(8 (9 (5)) (1))");

        ArrayList<Integer> array = bt.preorder();
        assertNotNull(array);
        assertTrue(array.contains(8));
        assertTrue(array.contains(9));
        assertTrue(array.contains(5));
        assertTrue(array.contains(1));
        assertEquals(4, array.size());
    }
}
