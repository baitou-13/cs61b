package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> implements Deque<T> {
    private Object[] items;
    private static final int DEFAULT_CAPACITY = 8;
    private int capacity;
    private int size;
    private int headIndex;
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c, int capacity) {
        this.capacity = capacity;
        size = 0;
        headIndex = 0;
        items = new Object[capacity];
        this.comparator = c;
    }

    public MaxArrayDeque(Comparator<T> c) {
        this(c, DEFAULT_CAPACITY);
    }

    private int tail_index() {
        if (size == 0) {
            return 0;
        }
        return (headIndex + size - 1 + capacity) % capacity;
    }

    public int getSize() {
        return size;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int newCapacity) {
        Object[] temp = items;
        items = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            items[i] = temp[(headIndex + i) % capacity];
        }

        capacity = newCapacity;
        headIndex = 0;
    }

    private void renewSize(int expectedSize) {
        if (expectedSize <= capacity / 4 && capacity > DEFAULT_CAPACITY) {
            resize(capacity / 4);
        } else if (expectedSize > capacity) {// when size = capacity and remove; a bug
            resize(capacity * 4);
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void addFirst(T item) {
        if (isEmpty()) {
            items[headIndex] = item;
        } else {
            renewSize(getSize() + 1);
            headIndex = (headIndex + capacity - 1) % capacity;
            items[headIndex] = item;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        if (isEmpty()) {
            items[headIndex] = item;
        } else {
            renewSize(getSize() + 1);
            int index = tail_index() + 1;
            items[index] = item;
        }
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = (T) items[headIndex];
        items[headIndex] = null;
        headIndex = (headIndex + 1) % capacity;
        size--;
        renewSize(getSize() - 1);
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = (T) items[tail_index()];
        items[tail_index()] = item;
        size--;
        renewSize(getSize() - 1);
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return (T) items[(headIndex + index) % capacity];
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public T max() {
        return max(this.comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) return null;
        T maxElement = null;
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            T element = (T) items[(headIndex + i) % capacity];
            if (maxElement == null || c.compare(element, maxElement) > 0) {
                maxElement = element;
            }
        }
        return maxElement;
    }

    //undo
    @Override
    public Iterator<T> iterator() {
        return null;
    }
    //undo
    @Override
    public boolean equals(Object o) {
        return true;
    }
}
