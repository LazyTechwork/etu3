package me.lazytechwork.algods.utils.tree;

import me.lazytechwork.algods.utils.ArrayList;
import org.jetbrains.annotations.Nullable;

public class BinaryTree<T extends Comparable<T>> {
    private TreeNode<T> root;

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    public void insert(T data) {
        TreeNode<T> node = new TreeNode<>(data);

        if (root == null) {
            setRoot(node);
        } else {
            TreeNode<T> nodeIterator = root;
            while (true) {
                int comparatorResult = node.data.compareTo(nodeIterator.data);

                if (comparatorResult == 0) {
                    node.parent = nodeIterator;
                    node.rightChild = nodeIterator.rightChild;
                    if (node.rightChild != null)
                        node.rightChild.parent = node;
                    nodeIterator.rightChild = node;
                    break;
                } else if (comparatorResult > 0) {
                    if (nodeIterator.rightChild != null) {
                        nodeIterator = nodeIterator.rightChild;
                        continue;
                    } else {
                        node.parent = nodeIterator;
                        nodeIterator.rightChild = node;
                        break;
                    }
                } else {
                    if (nodeIterator.leftChild != null) {
                        nodeIterator = nodeIterator.leftChild;
                        continue;
                    } else {
                        node.parent = nodeIterator;
                        nodeIterator.leftChild = node;
                        break;
                    }
                }
            }
        }
    }

    public @Nullable ArrayList<T> preorder() {
        return preorder(root, new ArrayList<>());
    }

    public @Nullable ArrayList<T> preorder(TreeNode<T> node, ArrayList<T> array) {
        if (node == null)
            return null;
        array.add(node.data);
        preorder(node.leftChild, array);
        preorder(node.rightChild, array);

        return array;
    }

    public static class TreeNode<T> {
        private T data;
        private TreeNode<T> leftChild;
        private TreeNode<T> rightChild;
        private TreeNode<T> parent;

        public TreeNode(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public TreeNode<T> getLeftChild() {
            return leftChild;
        }

        public TreeNode<T> getRightChild() {
            return rightChild;
        }
    }
}
