import java.util.Comparator;

public class MyBinaryTree<T> {
    private BNode<T> root;
    private Comparator<T> c;

    public MyBinaryTree(Comparator<T> compare) {
        root = null;
        c = compare;
    }

    public void add(T item) {
        root = add(root, new BNode<>(item));
    }

    private BNode<T> add(BNode<T> tree, BNode<T> newNode) {
        if (tree == null) {
            tree = newNode;
        } else if (c.compare(newNode.data, tree.data) < 0) {
            tree.left = add(tree.left, newNode);
        } else {
            tree.right = add(tree.right, newNode);
        }
        return tree;
    }

    public void print() {
        print(root);
    }

    private void print(BNode<T> node) {
        if (node != null) {
            print(node.left);
            System.out.println(node.data);
            print(node.right);
        }
    }

    // RemovalResult class to hold the node to be removed and the remaining tree
    private class RemovalResult {
        BNode<T> node;
        BNode<T> tree;

        RemovalResult(BNode<T> node, BNode<T> tree) {
            this.node = node;
            this.tree = tree;
        }
    }

    // Public remove method
    public boolean remove(T item) {
        RemovalResult result = remove(root, item);
        if (result == null) { // Item was not found in tree
            return false;
        } else {
            root = result.tree;
            return true;
        }
    }

    // Private recursive remove method
    private RemovalResult remove(BNode<T> tree, T item) {
        if (tree == null) { // Item not found
            return null;
        }

        int cmp = c.compare(item, tree.data);

        if (cmp < 0) {
            // Remove item from the left sub-tree
            RemovalResult result = remove(tree.left, item);
            if (result == null) {
                return null;
            }
            tree.left = result.tree;
            result.tree = tree;
            return result;
        } else if (cmp > 0) {
            // Remove item from the right sub-tree
            RemovalResult result = remove(tree.right, item);
            if (result == null) {
                return null;
            }
            tree.right = result.tree;
            result.tree = tree;
            return result;
        }

        // This is the node to remove
        // Is it a leaf?
        if (tree.left == null && tree.right == null) {
            return new RemovalResult(tree, null);
        }
        // Does it have two children?
        if (tree.right != null && tree.left != null) {
            RemovalResult result = removeLargest(tree.left);
            BNode<T> newRoot = result.node;
            newRoot.left = result.tree;
            newRoot.right = tree.right;
            tree.left = null;
            tree.right = null;
            return new RemovalResult(tree, newRoot);
        }
        // Single child case
        BNode<T> node = tree;
        BNode<T> newTree = tree.right;
        if (tree.left != null) {
            newTree = tree.left;
        }
        node.left = null;
        node.right = null;
        return new RemovalResult(node, newTree);
    }

    // Private method to remove the largest node from the subtree
    private RemovalResult removeLargest(BNode<T> tree) {
        if (tree == null) {
            return null;
        }
        if (tree.right == null) { // This is the largest Node
            BNode<T> newTree = tree.left;
            tree.left = null;
            return new RemovalResult(tree, newTree);
        }
        // Keep searching in right subtree
        RemovalResult result = removeLargest(tree.right);
        tree.right = result.tree;
        result.tree = tree;
        return result;
    }

    // BNode class
    private static class BNode<T> {
        T data;
        BNode<T> left;
        BNode<T> right;

        BNode(T value) {
            this.data = value;
            this.left = null;
            this.right = null;
        }
    }
}



