package deque;

import java.util.Iterator;

public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    int size();
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);

    default boolean isEmpty() {
        return size() == 0;
    }

    //public Iterator<T> iterator();
    public boolean equals(Object o);
}
