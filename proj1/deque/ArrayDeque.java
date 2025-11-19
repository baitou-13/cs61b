package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private Object[] items;
    private static final int DEFAULT_CAPACITY = 8;
    private int capacity;

    //index
    //tail_index - head_index + 1 = size;
    private int size;
    private int headIndex;

    private int tailIndex() {
        return (headIndex + size - 1 + capacity) % capacity;
    }

    //Constructor
    public ArrayDeque(int capacity) {
        this.capacity = capacity;
        items = new Object[capacity];
        size = 0;
        headIndex = 0;
    }

    public ArrayDeque() {
        this(DEFAULT_CAPACITY);
    }

    //Dynamic Size
    private void ensureSize(int expectedSize) {
        if (expectedSize > capacity) {
            resize(capacity * 4);
        } else if (expectedSize <= capacity / 4 && capacity > DEFAULT_CAPACITY) {
            resize(capacity / 4);
        }
    }

    private void resize(int newCapacity) {
        Object[] newItems = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            int newIndex = (headIndex + i) % newCapacity; // 正确计算新索引
            newItems[newIndex] = items[i];
        }
        items = newItems;
        capacity = newCapacity;
        headIndex = 0;
    }

    //API
    @Override
    public int size() {
        return size;
    }

    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public void addFirst(T t) {
        ensureSize(size+1);
        if (isEmpty()) {
            addLast(t);
            return;
        }

        headIndex = (headIndex + capacity - 1) % capacity;
        items[headIndex] = t;
        size++;
    }

    @Override
    public void addLast(T t) {
        ensureSize(size+1);
        int index = tailIndex();
        items[index] = t;
        size++;
    }

    @Override
    public T removeFirst() {
        T temp = (T) items[headIndex];
        items[headIndex] = null;
        headIndex = (headIndex + 1) % capacity;
        ensureSize(size - 1);
        size--;
        return temp;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;
        int index = tailIndex();
        @SuppressWarnings("unchecked")
        T temp = (T) items[index];
        items[index] = null;
        size--;
        if (size == 0) {
            headIndex = 0; // 如果为空，重置头部索引
        } else {
            index = (index + capacity - 1) % capacity; // 正确计算新的尾部索引
        }
        return temp;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return (T) items[(index + headIndex) % capacity];
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public Iterator<T> iterator() {
        return null;
    }

    public boolean equals(Object o) {
        return true;
    }
}
