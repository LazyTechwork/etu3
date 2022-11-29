package ru.lazytechwork.algods.utils.tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.lazytechwork.algods.utils.ArrayList;
import ru.lazytechwork.algods.utils.Stack;

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

    private AVLTreeNode<T> getMinimal(AVLTreeNode<T> node) {
        return node.leftChild != null ? getMinimal(node.leftChild) : node;
    }

    private AVLTreeNode<T> removeMinimal(AVLTreeNode<T> node) {
        if (node.leftChild == null)
            return node.rightChild;
        node.leftChild = removeMinimal(node.leftChild);
        return balance(node);
    }

    public AVLTreeNode<T> find(T needle) {
        return findNode(root, needle);
    }

    private AVLTreeNode<T> findNode(AVLTreeNode<T> root, T needle) {
        if (root == null || root.data.equals(needle))
            return root;
        else
            return findNode(
                    needle.compareTo(root.data) < 0 ? root.leftChild : root.rightChild,
                    needle
            );
    }

    public void remove(T needle) {
        remove(getRoot(), needle);
    }

    private AVLTreeNode<T> remove(AVLTreeNode<T> root, T needle) {
        if (root == null) return null;
        int comparatorResult = needle.compareTo(root.data);
        if (comparatorResult < 0)
            root.leftChild = remove(root.leftChild, needle);
        else if (comparatorResult > 0)
            root.rightChild = remove(root.rightChild, needle);
        else {
            AVLTreeNode<T> left = root.leftChild;
            AVLTreeNode<T> right = root.rightChild;
            root.rightChild = root.leftChild = null;

            if (right == null)
                return left;

            AVLTreeNode<T> minimal = getMinimal(right);
            minimal.rightChild = removeMinimal(right);
            minimal.leftChild = left;

            return balance(minimal);
        }

        return balance(root);
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

    public @Nullable ArrayList<T> prefixTraverse() {
        return prefixTraverse(root, new ArrayList<>());
    }

    public @Nullable ArrayList<T> prefixTraverse(AVLTreeNode<T> node, ArrayList<T> array) {
        if (node == null)
            return null;
        array.add(node.getData());
        prefixTraverse(node.getLeftChild(), array);
        prefixTraverse(node.getRightChild(), array);

        return array;
    }

    public @Nullable ArrayList<T> infixTraverse() {
        return infixTraverse(root, new ArrayList<>());
    }

    public @Nullable ArrayList<T> infixTraverse(AVLTreeNode<T> node, ArrayList<T> array) {
        if (node == null)
            return null;
        infixTraverse(node.getLeftChild(), array);
        array.add(node.getData());
        infixTraverse(node.getRightChild(), array);

        return array;
    }

    public @Nullable ArrayList<T> postfixTraverse() {
        return postfixTraverse(root, new ArrayList<>());
    }

    public @Nullable ArrayList<T> postfixTraverse(AVLTreeNode<T> node, ArrayList<T> array) {
        if (node == null)
            return null;
        postfixTraverse(node.getLeftChild(), array);
        postfixTraverse(node.getRightChild(), array);
        array.add(node.getData());

        return array;
    }

    public @Nullable ArrayList<T> breadthTraverse(AVLTreeNode<T> node) {
        ArrayList<T> values = new ArrayList<>();
        Stack<AVLTreeNode<T>> queue = new Stack<>();
        queue.push(node);

        while (queue.getCount() > 0) {
            AVLTreeNode<T> queuedNode = queue.pop();
            values.add(queuedNode.getData());

            if (queuedNode.getLeftChild() != null) queue.push(queuedNode.getLeftChild());
            if (queuedNode.getRightChild() != null) queue.push(queuedNode.getRightChild());
        }

        return values;
    }

    public static class AVLTreeNode<T> {
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

        public T getData() {
            return data;
        }

        public short getHeight() {
            return height;
        }

        public AVLTreeNode<T> getLeftChild() {
            return leftChild;
        }

        public AVLTreeNode<T> getRightChild() {
            return rightChild;
        }
    }
}
