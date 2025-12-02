package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private static final int DEFAULT_CAPACITY = 8;
    private int capacity;

    //index
    //tail_index - head_index + 1 = size;
    private int size;
    private int headIndex;

    private int tailIndex() {
        if (size == 0) {
            return 0;
        }
        return (headIndex + size - 1 + capacity) % capacity;
    }

    //Constructor
    public ArrayDeque() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayDeque(int capacity) {
        this.capacity = capacity;
        items = (T[]) new  Object[capacity];
        size = 0;
        headIndex = 0;
    }

    //Dynamic Size
    private void ensureSize() {
        if (size == 0 && capacity > DEFAULT_CAPACITY) {
            resize(DEFAULT_CAPACITY); // 如果数组为空且容量大于默认容量，缩小到默认容量
        } else if (size + 1 > capacity) {
            resize(Math.max(capacity * 4, DEFAULT_CAPACITY)); // 至少扩容到默认容量
        } else if (size < capacity / 4 && capacity > DEFAULT_CAPACITY) {
            resize(capacity / 4); // 缩小到容量的1/4，但不超过默认容量
        }
    }

    private void resize(int newCapacity) {
        Object[] temp = items;
        items = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            items[i] = (T) temp[(headIndex + i) % capacity];
        }

        capacity = newCapacity;
        headIndex = 0;
    }

    //API
    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(T t) {
        ensureSize();
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
        ensureSize();
        if (isEmpty()) {
            items[headIndex] = t;
            size++;
            return;
        }
        int index = (tailIndex() + 1) % capacity;
        items[index] = t;
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T temp = (T) items[headIndex];
        items[headIndex] = null;
        headIndex = (headIndex + 1) % capacity;
        ensureSize();
        size--;
        return temp;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T temp = (T) items[tailIndex()];
        items[tailIndex()] = null;
        ensureSize();
        size--;
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
        class NodeIterator implements Iterator<T> {
            private int iterIndex;
            NodeIterator() {
                this.iterIndex = 0;
            }

            @Override
            public boolean hasNext() {
                return iterIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException("迭代器已遍历到末尾，无更多元素");
                }
                int actualIndex = (headIndex + iterIndex) % capacity;
                T item = (T) items[actualIndex];
                iterIndex++;
                return item;
            }
        }
        return new NodeIterator();
    }


    //undo
    public boolean equals(Object o) {
        return true;
    }
}
