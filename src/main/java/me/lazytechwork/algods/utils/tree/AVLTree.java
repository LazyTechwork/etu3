package me.lazytechwork.algods.utils.tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AVLTree<T extends Comparable<T>> implements IBinaryTree<T> {
    private AVLTreeNode<T> root;

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

    public void insert(T data) {
        root = insert(data, root);
    }

    private AVLTreeNode<T> insert(T data, AVLTreeNode<T> root) {
        if (root == null)
            return new AVLTreeNode<>(data);
        else if (data.compareTo(root.data) < 0)
            root.leftChild = insert(data, root.leftChild);
        else
            root.rightChild = insert(data, root.rightChild);
        return balance(root);
    }

    public AVLTreeNode<T> getRoot() {
        return root;
    }

    private static class AVLTreeNode<T> {
        T data;
        short height;
        AVLTreeNode<T> leftChild;
        AVLTreeNode<T> rightChild;

        public AVLTreeNode(T data) {
            this.data = data;
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
