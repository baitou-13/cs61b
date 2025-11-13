package deque;

public class LinkedListDeque<Item> {

    //inner class, don't directly use it
    private class Node {
        private Item data;
        private Node next;
        private Node last;

        public Node(Item data, Node last, Node next) {
            this.data = data;
            this.last = last;
            this.next = next;
        }

        private Item remove() {
            if (size == 0) {
                return null;
            }
            this.last.next = this.next;
            this.next.last = this.last;
            size--;
            return this.data;
        }
    }

    //sentinel
    private Node head;
    private Node tail;
    private int size;

    /* Creates an empty LinkedListDeque */
    public LinkedListDeque() {
        head = new Node(null, null, null);
        tail = new Node(null, null, null);
        head.next = tail;
        tail.last = head;
        size = 0;
    }

    public LinkedListDeque(Item x) {
        head = new Node(null, null, null);
        tail = new Node(null, null, null);
        Node newNode = new Node(x, head, tail);
        head.next = newNode;
        tail.last = newNode;
        size = 1;
    }

    public void addFirst(Item x) {
        Node newNode = new Node(x, head, head.next);
        head.next.last = newNode;
        head.next = newNode;
        size++;
    }

    public void addLast(Item x) {
        Node newNode = new Node(x, tail.last, tail);
        tail.last.next = newNode;
        tail.last = newNode;
        size++;
    }

    public Item removeFirst() {
        return head.next.remove();
    }

    public Item removeLast() {
        return tail.last.remove();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Item get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node current = head.next;
        while (index > 0) {
            current = current.next;
            index--;
        }
        return current.data;
    }

    public Item getFirst() {
        return head.next.data;
    }
    public Item getLast() {
        return tail.last.data;
    }

    public void printDeque() {
        Node current = head.next;
        while (current != tail) {
            System.out.print(current.data);
            if (current.next != tail) System.out.print(" ");
            current = current.next;
        }
        System.out.println();
    }

    public Item getRecursive(int index) {
        if (index < 0 || index >= size) return null;
        return getRecursiveHelper(head.next, index);
    }

    private Item getRecursiveHelper(Node p, int i) {
        if (i == 0) return p.data;
        return getRecursiveHelper(p.next, i - 1);
    }
}
