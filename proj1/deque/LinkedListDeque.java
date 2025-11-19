package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {

    //inner class, don't directly use it
    private class Node {
        private T data;
        private Node next;
        private Node last;

        private Node(T data, Node last, Node next) {
            this.data = data;
            this.last = last;
            this.next = next;
        }

        private T remove() {
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

    public LinkedListDeque(T x) {
        head = new Node(null, null, null);
        tail = new Node(null, null, null);
        Node newNode = new Node(x, head, tail);
        head.next = newNode;
        tail.last = newNode;
        size = 1;
    }

    @Override
    public void addFirst(T x) {
        Node newNode = new Node(x, head, head.next);
        head.next.last = newNode;
        head.next = newNode;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(x, tail.last, tail);
        tail.last.next = newNode;
        tail.last = newNode;
        size++;
    }

    @Override
    public T removeFirst() {
        return head.next.remove();
    }

    @Override
    public T removeLast() {
        return tail.last.remove();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
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

    public T getFirst() {
        return head.next.data;
    }
    public T getLast() {
        return tail.last.data;
    }

    @Override
    public void printDeque() {
        Node current = head.next;
        while (current != tail) {
            System.out.print(current.data);
            if (current.next != tail) {
                System.out.print(" ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(head.next, index);
    }

    private T getRecursiveHelper(Node p, int i) {
        if (i == 0) {
            return p.data;
        }
        return getRecursiveHelper(p.next, i - 1);
    }

    public Iterator<T> iterator() {
        return null;
    }

    public boolean equals(Object o) {
        return true;
    }
}
