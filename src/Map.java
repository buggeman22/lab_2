class Node<K extends Comparable<K>, V> {
    K key;
    V value;
    int height;
    Node<K, V> left;
    Node<K, V> right;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    Node(K key, V value, int height) {
        this.key = key;
        this.value = value;
        this.height = height;
    }
}

public class Map<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public Map() {
    }

    public Map(Map<K, V> map) {
        if (map.root != null) {
            root = new Node<>(map.root.key, map.root.value, map.root.height);
            recursiveCopy(map.root, root);
        }
    }

    private void recursiveCopy(Node<K, V> src, Node<K, V> dest) {
        if (src.left != null) {
            dest.left = new Node<>(src.left.key, src.left.value, src.left.height);
            recursiveCopy(src.left, dest.left);
        }

        if (src.right != null) {
            dest.right = new Node<>(src.right.key, src.right.value, src.right.height);
            recursiveCopy(src.right, dest.right);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    public void insert(K key, V value) {
        root = recursiveInsert(root, key, value);
    }

    public V getValue(K key) {
        Node<K, V> node = findNodeByKey(key);

        if (node == null) {
            System.out.println("This key does not exist in map!");
            return null;
        }

        return node.value;
    }

    public void setValue(K key, V value) {
        Node<K, V> node = findNodeByKey(key);

        if (node == null) {
            System.out.println("This key does not exist in map!");
            return;
        }

        node.value = value;
    }

    public boolean isExist(K key) {
        return findNodeByKey(key) != null;
    }

    public void delete(K key) {
        root = recursiveDelete(root, key);
    }

    private Node<K, V> findNodeByKey(K key) {
        Node<K, V> node = root;

        while (node != null && key.compareTo(node.key) != 0) {
            if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else if (key.compareTo(node.key) > 0) {
                node = node.right;
            }
        }

        return node;
    }

    private int getNodeHeight(Node<K, V> node) {
        return node != null ? node.height : -1;
    }

    private int getBalanceFactor(Node<K, V> node) {
        return node != null ? getNodeHeight(node.right) - getNodeHeight(node.left) : 0;
    }

    private void fixNodeHeight(Node<K, V> node) {
        node.height = Math.max(getNodeHeight(node.left), getNodeHeight(node.right)) + 1;
    }

    private Node<K, V> rotateRight(Node<K, V> p) {
        Node<K, V> q = p.left;
        p.left = q.right;
        q.right = p;
        fixNodeHeight(p);
        fixNodeHeight(q);

        return q;
    }

    private Node<K, V> rotateLeft(Node<K, V> q) {
        Node<K, V> p = q.right;
        q.right = p.left;
        p.left = q;
        fixNodeHeight(q);
        fixNodeHeight(p);

        return p;
    }

    private Node<K, V> balanceNode(Node<K, V> node) {
        fixNodeHeight(node);

        if (getBalanceFactor(node) == 2) {
            if (getBalanceFactor(node.right) < 0) {
                node.right = rotateRight(node.right);
            }

            return rotateLeft(node);
        }

        if (getBalanceFactor(node) == -2) {
            if (getBalanceFactor(node.left) > 0) {
                node.left = rotateLeft(node.left);
            }

            return rotateRight(node);
        }

        return node;
    }

    private Node<K, V> recursiveInsert(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = recursiveInsert(node.left, key, value);
        } else {
            node.right = recursiveInsert(node.right, key, value);
        }

        return balanceNode(node);
    }

    private Node<K, V> findMinRight(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            return node;
        }

        return findMinRight(node.left);
    }

    private Node<K, V> recursiveDelete(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = recursiveDelete(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = recursiveDelete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node<K, V> minRight = findMinRight(node.right);
                node.key = minRight.key;
                node.value = minRight.value;
                node.right = recursiveDelete(node.right, minRight.key);
            }
        }

        return node != null ? balanceNode(node) : null;
    }

    public void printTree() {
        int level = 1;

        while (printLevel(root, level)) {
            level++;
            System.out.println();
        }
        System.out.println();
    }

    private boolean printLevel(Node<K, V> node, int level)
    {
        if (node == null) {
            return false;
        }

        if (level == 1) {
            System.out.print("[" + node.key + "] ");

            return true;
        }

        boolean isLevelExist = printLevel(node.left, level - 1);
        isLevelExist |= printLevel(node.right, level - 1);

        return isLevelExist;
    }
}