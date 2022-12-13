package ru.lazytechwork.algods.utils.tree;

import org.junit.jupiter.api.Test;
import ru.lazytechwork.algods.utils.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeTest {
    @Test
    void canInsert() {
        BinaryTree<Integer> bt = new BinaryTree<>();
        bt.insert(1);
        assertEquals(1, bt.getRoot().getData());
        bt.insert(3);
        assertNotNull(bt.getRoot().getRightChild());
        assertEquals(3, bt.getRoot().getRightChild().getData());
        bt.insert(2);
        assertNotNull(bt.getRoot().getRightChild().getLeftChild());
        assertEquals(2, bt.getRoot().getRightChild().getLeftChild().getData());
    }

    @Test
    void isPreorderCorrect() {
        BinaryTree<Integer> bt = new BinaryTree<>();

        Integer[] arr = new Integer[]{2, 5, 1, 7, 3, 9, 4, 6, 5};
        for (Integer integer : arr)
            bt.insert(integer);

        ArrayList<Integer> preorder = bt.prefixTraverse();
        assertNotNull(preorder);

        for (Integer integer : arr)
            assertTrue(preorder.contains(integer));
    }
}
