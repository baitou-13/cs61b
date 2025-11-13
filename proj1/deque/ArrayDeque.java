package deque;

public class ArrayDeque<T> {
    private Object[] items;
    private static final int DEFAULT_CAPACITY = 8;
    private int capacity =  DEFAULT_CAPACITY;

    //index
    //tail_index - head_index + 1 = size;
    private int size;
    private int head_index;

    private int tail_index(){
        if(size == 0){
            return 0;
        }
        return (head_index + size - 1 + capacity) % capacity;
    }

    //Constructor
    public ArrayDeque(int capacity) {
        items = new Object[capacity];
        size = 0;
        head_index = 0;
    }

    public ArrayDeque() {
        this(DEFAULT_CAPACITY);
    }

    //Dynamic Size
    private void ensureSize(){
        if (size <=  capacity / 4 && capacity > DEFAULT_CAPACITY) {//minimize
            resize(capacity / 4);
        } else if (size + 1 > capacity) {//maximize
            resize(capacity * 4);
        }
    }

    private void resize(int newCapacity) {
        Object[] temp = items;
        items = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            items[i] = temp[(head_index + i) % capacity];
        }

        capacity = newCapacity;
        head_index = 0;
    }

    //API
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void addFirst(T t) {
        ensureSize();
        if (isEmpty()) {
            addLast(t);
            return;
        }

        head_index = (head_index + capacity - 1) % capacity;
        items[head_index] = t;
        size++;
    }

    public void addLast(T t) {
        ensureSize();
        if (isEmpty()) {
            items[head_index] = t;
            size++;
            return;
        }
        int index = tail_index() + 1;
        items[index] = t;
        size++;
    }

    public T removeFirst() {
        T temp = (T) items[head_index];
        items[head_index] = null;
        head_index = (head_index + 1) % capacity;
        ensureSize();
        size--;
        return temp;
    }

    public T removeLast() {
        T temp = (T) items[tail_index()];
        items[tail_index()] = null;
        ensureSize();
        size--;
        return temp;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return (T) items[(index + head_index) % capacity];
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
    }
}
