import java.util.ArrayList;
import java.util.Iterator;
public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.Entry> {
    private Node root;
    private int size;
    private class Node {
        private K key;
        private V val;
        private Node left, right;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    public class Entry {
        private K key;
        private V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
    }
    public int size() {
        return size;
    }
    public void put(K key, V val) {
        Node newNode = new Node(key, val);
        if (root == null) {
            root = newNode;
            size++;
            return;
        }
        Node current = root;
        while (true) {
            int compare = key.compareTo(current.key);
            if (compare == 0) {
                current.val = val;
                return;
            }
            if (compare < 0) {
                if (current.left == null) {
                    current.left = newNode;
                    size++;
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    size++;
                    return;
                }
                current = current.right;
            }
        }
    }
    public V get(K key) {
        Node current = root;
        while (current != null) {
            int compare = key.compareTo(current.key);
            if (compare == 0) {
                return current.val;
            }
            if (compare < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }
    public void delete(K key) {
        Node parent = null;
        Node current = root;
        while (current != null && key.compareTo(current.key) != 0) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (current == null) {
            return;
        }
        if (current.left != null && current.right != null) {
            Node successorParent = current;
            Node successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            current.key = successor.key;
            current.val = successor.val;
            parent = successorParent;
            current = successor;
        }
        Node child;
        if (current.left != null) {
            child = current.left;
        } else {
            child = current.right;
        }
        if (parent == null) {
            root = child;
        } else if (parent.left == current) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        size--;
    }
    @Override
    public Iterator<Entry> iterator() {
        ArrayList<Entry> list = new ArrayList<>();
        ArrayList<Node> stack = new ArrayList<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.add(current);
                current = current.left;
            }
            current = stack.remove(stack.size() - 1);
            list.add(new Entry(current.key, current.val));
            current = current.right;
        }
        return list.iterator();
    }
}