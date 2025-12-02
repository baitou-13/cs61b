package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> implements Deque<T> {
    private T[] items;
    private static final int DEFAULT_CAPACITY = 8;
    private int capacity;
    private int size;
    private int headIndex;
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c, int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.headIndex = 0;
        this.items = (T[]) new Object[capacity];
        this.comparator = c;
    }

    public MaxArrayDeque(Comparator<T> c) {
        this(c, DEFAULT_CAPACITY);
    }

    private int tailIndex() {
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

    @Override
    public boolean isEmpty() {
        return size == 0;
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
        return items[(headIndex + index) % capacity];
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public T max() {
        // 空队列直接返回 null
        if (isEmpty()) {
            return null;
        }
        // 确保 Comparator 非空（构造器已校验，此处避免逻辑漏洞）
        if (comparator == null) {
            throw new IllegalStateException("Comparator is not specified");
        }

        T maxElement = items[headIndex]; // 初始值设为第一个有效元素（避免 null 判断）
        // 遍历所有元素，用 Comparator 比较大小
        for (int i = 1; i < size; i++) {
            int actualIndex = (headIndex + i) % capacity;
            T currentElement = items[actualIndex];
            // 若当前元素比最大值大（Comparator 返回正数），更新最大值
            if (comparator.compare(currentElement, maxElement) > 0) {
                maxElement = currentElement;
            }
        }
        return maxElement;
    }

    //@Override
    public Iterator<T> iterator() {
        class NodeIterator implements Iterator<T> {
            private int index;

            NodeIterator() {
                index = headIndex;
            }

            @Override
            public boolean hasNext() {
                return index != tailIndex();
            }

            @Override
            public T next() {
                int prevIndex = index;
                index = (index + 1) % capacity;
                return items[prevIndex];
            }
        }
        return new NodeIterator();
    }
    //undo
    @Override
    public boolean equals(Object o) {
        return true;
    }
}
