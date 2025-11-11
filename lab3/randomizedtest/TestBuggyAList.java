package randomizedtest;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;

public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();
        for (int i = 0; i < 3; i++) {
            correct.addLast(i+3);
            broken.addLast(i+3);
        }

        assertEquals(correct.size(),broken.size());

        for (int i = 0; i < 3; i++) {
            assertEquals(correct.removeLast(),broken.removeLast());
        }

    }

    @Test
    public void testThousandAddAndGet() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();
        for (int i = 0; i < 1000; i++) {
            correct.addLast(i);
            broken.addLast(i);
        }

        assertEquals(correct.size(),broken.size());

        for (int i = 0; i < 1000; i++) {
            assertEquals(correct.get(i),broken.get(i));
        }
    }

    @Test
    public void testRemoveLast() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int size = 1000;
        //16 T
        //17 java.lang.ArrayIndexOutOfBoundsException
        //BuggyAList.removeLast()
        //before:resize(size / 4 );
        //after:resize(items.length / 4);

        for (int i = 0; i < size; i++) {
            correct.addLast(i);
            broken.addLast(i);
        }
        assertEquals(correct.size(),broken.size());
        for (int i = 0; i < size; i++) {
            assertEquals(correct.removeLast(),broken.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                // getLast
                int size = L.size();
                if (size > 0){
                    int last = L.getLast();
                    System.out.println("getLast() returns " + last);
                }
            } else if (operationNumber == 3) {
                // removeLast
                int size = L.size();
                if (size > 0){
                    int removed = L.removeLast();
                    System.out.println("removeLast() returns " + removed);
                }
            }
        }
    }
}
