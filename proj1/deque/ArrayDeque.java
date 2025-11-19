package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private Object[] items;
    private static final int DEFAULT_CAPACITY = 8;
    private int capacity =  DEFAULT_CAPACITY;

    //index
    //tail_index - head_index + 1 = size;
    private int size;
    private int headIndex;

    private int tailIndex(){
        if (size == 0){ 
            return 0;
        }
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
    private void ensureSize(){
        if (size <=  capacity / 4 && capacity > DEFAULT_CAPACITY) { //minimize
            resize(capacity / 4);
        } else if (size + 1 > capacity) { //maximize
            resize(capacity * 4);
        }
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
        int index = tailIndex() + 1;
        items[index] = t;
        size++;
    }

    @Override
    public T removeFirst() {
        T temp = (T) items[headIndex];
        items[headIndex] = null;
        headIndex = (headIndex + 1) % capacity;
        ensureSize();
        size--;
        return temp;
    }

    @Override
    public T removeLast() {
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

    public Iterator<T> iterator(){
        return null;
    }

    public boolean equals(Object o){
        return true;
    }
}
