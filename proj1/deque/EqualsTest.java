package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class EqualsTest {
    @Test
    public void testEqualsNull() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<>();
        assertEquals(arrayDeque, linkedListDeque);

        arrayDeque.addLast(1);
        linkedListDeque.addLast(1);
        assertEquals(arrayDeque, linkedListDeque);
    }

}
