package deque;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Optional;

public class ArrayDequeTest {
    @Test
    public void isEmpty() {
        ArrayDeque deque = new ArrayDeque();
        assertTrue(deque.isEmpty());
    }
    @Test
    public void isEmptyFull() {
        ArrayDeque deque = new ArrayDeque();
        assertFalse(deque.isFull());
    }

    @Test
    public void testAddFirst() {//ok
        ArrayDeque deque = new ArrayDeque();
        for(int i = 0; i < 10; i++) {
            deque.addFirst(i);
        }
    }

    @Test
    public void testAddLast() {//ok
        ArrayDeque deque = new ArrayDeque();
        for(int i = 0; i < 10; i++) {
            deque.addLast(i);
        }
    }

    @Test
    public void testRemoveFirst() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int N = 100;
        for(int i = 0; i < N; i++){
            deque.addLast(i);
        }
        assertEquals(N, deque.size());
        for(int i = 0; i < N; i++){
            assertEquals(Optional.of(i), Optional.of(deque.removeFirst()));
        }
    }

    @Test
    public void testRemoveLast() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int N = 100;
        for(int i = 0; i < N; i++){
            deque.addLast(i);
        }
        assertEquals(N, deque.size());
        for(int i = 0; i < N; i++){
            assertEquals(Optional.of(N - i - 1), Optional.of(deque.removeLast()));
        }
    }

    @Test
    public void randomAdd() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        int N = 500;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                deque.addFirst(i);
            } else if (operationNumber == 1) {
                deque.addLast(i);
            }
        }
    }

    @Test
    public void testGet(){
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < 10; i++) {
            deque.addLast(i);
        }
        for(int i = 0; i < 10; i++) {
            assertEquals(Optional.ofNullable(i), Optional.ofNullable(deque.get(i)));
        }
    }

    @Test
    public void testPrintDeque() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < 10; i++) {
            deque.addLast(i);
        }
        for(int i = 0; i < 10; i++) {
            deque.addFirst(i);
        }
        deque.printDeque();
    }
}
