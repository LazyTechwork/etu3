package me.lazytechwork.algods.utils.tree;

import me.lazytechwork.algods.utils.ArrayList;
import me.lazytechwork.core.exceptions.InvalidTreeSequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static java.lang.Integer.parseInt;

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {
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
                int comparatorResult = node.getData().compareTo(nodeIterator.getData());

                if (comparatorResult == 0) {
                    node.setParent(nodeIterator);
                    node.setRightChild(nodeIterator.getRightChild());
                    if (node.getRightChild() != null)
                        node.getRightChild().setParent(node);
                    nodeIterator.setRightChild(node);
                    break;
                } else if (comparatorResult > 0) {
                    if (nodeIterator.getRightChild() != null) {
                        nodeIterator = nodeIterator.getRightChild();
                    } else {
                        node.setParent(nodeIterator);
                        nodeIterator.setRightChild(node);
                        break;
                    }
                } else {
                    if (nodeIterator.getLeftChild() != null) {
                        nodeIterator = nodeIterator.getLeftChild();
                    } else {
                        node.setParent(nodeIterator);
                        nodeIterator.setLeftChild(node);
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
        array.add(node.getData());
        preorder(node.getLeftChild(), array);
        preorder(node.getRightChild(), array);

        return array;
    }

    public static BinaryTree<Integer> fromString(String sequence) throws InvalidTreeSequence, NumberFormatException {
        BinaryTree<Integer> bt = new BinaryTree<>();
        sequence = sequence.replace(" ", "");
        bt.root = nodeBuilder(sequence, null);
        return bt;
    }

    private static TreeNode<Integer> nodeBuilder(@NotNull String sequence, @Nullable TreeNode<Integer> parent)
            throws InvalidTreeSequence, NumberFormatException {
        TreeNode<Integer> node = new TreeNode<>(null);
        node.setParent(parent);

        if (sequence.charAt(0) != '(' || sequence.charAt(sequence.length() - 1) != ')')
            throw new InvalidTreeSequence();
        sequence = sequence.substring(1, sequence.length() - 1);

        int openingParentheses = sequence.indexOf('(');
        if (openingParentheses == -1) {
            node.setData(sequence.isEmpty() ? null : parseInt(sequence));
            return node;
        }

        String strData = sequence.substring(0, openingParentheses);
        node.setData(strData.isEmpty() ? null : parseInt(strData));

        Integer closingParentheses = findClosingParentheses(sequence, openingParentheses);
        if (closingParentheses == null)
            throw new InvalidTreeSequence();

        node.setLeftChild(nodeBuilder(sequence.substring(openingParentheses, closingParentheses + 1), node));

        openingParentheses = sequence.indexOf('(', closingParentheses);
        if (openingParentheses != -1) {
            closingParentheses = findClosingParentheses(sequence, openingParentheses);
            if (closingParentheses == null)
                throw new InvalidTreeSequence();

            node.setRightChild(nodeBuilder(sequence.substring(openingParentheses, closingParentheses + 1), node));
        }
        return node;
    }

    private static @Nullable Integer findClosingParentheses(String sequence, int openingParentheses) {
        for (int i = openingParentheses, l = sequence.length(), opened = 0; i < l; i++)
            if (sequence.charAt(i) == '(')
                opened++;
            else if (sequence.charAt(i) == ')' && --opened == 0)
                return i;

        return null;
    }

    public String toString() {
        return toString(root);
    }

    private String toString(TreeNode<T> node) {
        StringBuilder stringBuilder = new StringBuilder("(");
        if (node.getData() != null)
            stringBuilder.append(node.getData().toString());

        if (node.getLeftChild() != null)
            stringBuilder.append(toString(node.getLeftChild()));

        if (node.getRightChild() != null)
            stringBuilder.append(toString(node.getRightChild()));

        stringBuilder.append(')');
        return stringBuilder.toString();
    }
}
