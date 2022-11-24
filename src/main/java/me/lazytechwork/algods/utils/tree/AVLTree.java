package me.lazytechwork.algods.utils.tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AVLTree<T> {

    private AVLTreeNode<T> rotateRight(AVLTreeNode<T> node) {
        AVLTreeNode<T> left = node.leftChild;
        node.leftChild = left.rightChild;
        left.rightChild = node;
        node.fixHeight();
        left.fixHeight();

        return left;
    }

    private AVLTreeNode<T> rotateLeft(AVLTreeNode<T> node) {
        AVLTreeNode<T> right = node.rightChild;
        node.rightChild = right.leftChild;
        right.leftChild = node;
        node.fixHeight();
        right.fixHeight();

        return right;
    }

    private AVLTreeNode<T> balance(AVLTreeNode<T> node) {
        node.fixHeight();
        int nodeBalanceFactor = AVLTreeNode.balanceFactor(node);
        if (nodeBalanceFactor == 2) {
            if (AVLTreeNode.balanceFactor(node.rightChild) < 0)
                node.rightChild = rotateRight(node.rightChild);
            return rotateLeft(node);
        } else if (nodeBalanceFactor == -2) {
            if (AVLTreeNode.balanceFactor(node.leftChild) > 0)
                node.leftChild = rotateLeft(node.leftChild);
            return rotateRight(node);
        } else
            return node;
    }

    private static class AVLTreeNode<T> {
        int key;
        short height;
        AVLTreeNode<T> leftChild;
        AVLTreeNode<T> rightChild;

        public AVLTreeNode(int key) {
            this.key = key;
            height = 1;
        }

        public void fixHeight() {
            short hl = height(leftChild);
            short hr = height(rightChild);
            height = (short) (Math.max(hl, hr) + 1);
        }

        public static int balanceFactor(@NotNull AVLTreeNode node) {
            return height(node.rightChild) - height(node.leftChild);
        }

        public static short height(@Nullable AVLTreeNode node) {
            return node != null ? node.height : 0;
        }
    }
}
