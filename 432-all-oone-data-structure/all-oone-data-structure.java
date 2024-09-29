import java.util.HashMap;

class Node {
    int count;
    HashMap<String, Node> keys; // Store keys with the same count
    Node prev, next;

    public Node(int count) {
        this.count = count;
        this.keys = new HashMap<>();
    }
}

class AllOne {
    private Node head, tail;
    private HashMap<String, Integer> countMap; // Store count of each key
    private HashMap<Integer, Node> countKeys; // Store head nodes of each count

    public AllOne() {
        head = new Node(0); // Dummy head
        tail = new Node(Integer.MAX_VALUE); // Dummy tail
        head.next = tail;
        tail.prev = head;
        countMap = new HashMap<>();
        countKeys = new HashMap<>();
    }

    public void inc(String key) {
        // Get current count of the key
        int count = countMap.getOrDefault(key, 0);
        int newCount = count + 1;
        countMap.put(key, newCount);

        // Get the node for the current count
        Node currentNode = countKeys.get(count);
        // Get or create the node for the new count
        Node newNode = countKeys.computeIfAbsent(newCount, k -> {
            Node newNodeToAdd = new Node(newCount);
            if (currentNode != null) {
                addNodeAfter(newNodeToAdd, currentNode);
            } else {
                addNodeAfter(newNodeToAdd, head); // Attach to head if currentNode is null
            }
            return newNodeToAdd;
        });

        // Move the key from current node to the new node
        if (currentNode != null) {
            currentNode.keys.remove(key);
            if (currentNode.keys.isEmpty()) {
                removeNode(currentNode);
                countKeys.remove(count);
            }
        }
        newNode.keys.put(key, newNode);
    }

    public void dec(String key) {
        // Get current count of the key
        int count = countMap.get(key);
        if (count == 1) {
            countMap.remove(key);
        } else {
            countMap.put(key, count - 1);
        }

        // Get the current node
        Node currentNode = countKeys.get(count);
        Node newNode = null;
        if (count > 1) {
            // Get or create the node for the new count
            newNode = countKeys.computeIfAbsent(count - 1, k -> {
                Node newNodeToAdd = new Node(count - 1);
                if (currentNode != null) {
                    addNodeAfter(newNodeToAdd, currentNode.prev);
                } else {
                    addNodeAfter(newNodeToAdd, head); // Attach to head if currentNode is null
                }
                return newNodeToAdd;
            });
        }

        // Move the key from current node to the new node
        if (currentNode != null) {
            currentNode.keys.remove(key);
            if (currentNode.keys.isEmpty()) {
                removeNode(currentNode);
                countKeys.remove(count);
            }
        }
        if (newNode != null) {
            newNode.keys.put(key, newNode);
        }
    }

    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.keys.keySet().iterator().next(); // Correctly return the max key
    }

    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.keySet().iterator().next(); // Correctly return the min key
    }

    private void addNodeAfter(Node newNode, Node prevNode) {
        newNode.prev = prevNode;
        newNode.next = prevNode.next;
        prevNode.next.prev = newNode;
        prevNode.next = newNode;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

// Your AllOne object will be instantiated and called as such:
// AllOne obj = new AllOne();
// obj.inc(key);
// obj.dec(key);
// String param_3 = obj.getMaxKey();
// String param_4 = obj.getMinKey();
