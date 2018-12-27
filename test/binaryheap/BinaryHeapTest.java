package binaryheap;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryHeapTest {

    public BinaryHeapTest() {
        random = new Random();
    }

    private <T extends Comparable<T>> boolean invariant(List<T> heap) {
        for (int i = 0; i < heap.size() / 2; i++) {
            T parent = heap.get(i);

            if (2 * i + 1 < heap.size()) {
                T left = heap.get(2 * i + 1);
                if (parent.compareTo(left) < 0)
                    return false;
            }

            if (2 * i + 2 < heap.size()) {
                T right = heap.get(2 * i + 2);
                if (parent.compareTo(right) < 0)
                    return false;
            }
        }

        return true;
    }

    @Test
    public void invariantTest() {
        List<Integer> test = new ArrayList<>(Arrays.asList(100, 90, 26, 19, 36, 5, 25, 9, 3, 17, 34, 1, 4, 7, 8, 2));
        assertTrue(invariant(test));

        test.add(30);
        assertFalse(invariant(test));

        test.clear();
        for (int i = 0; i < 1000; i++) {
            int rand = random.nextInt();
            if (!test.contains(rand))
                test.add(rand);
        }
        Collections.sort(test);
        Collections.reverse(test);
        assertTrue(invariant(test));
    }

    @Test
    public void add() {
       for (int i = 0; i < 1000; i++) {
           BinaryHeap<Integer> heap = new BinaryHeap<>();
           int elemSize = random.nextInt(1000);

           for (int j = 0; j < elemSize; j++)
               heap.add(random.nextInt());

           assertTrue(invariant(heap));
           assertEquals(heap.size(), elemSize);
       }

        for (int i = 0; i < 1000; i++) {
            BinaryHeap<Integer> heap = new BinaryHeap<>();
            int elemSize = random.nextInt(1000);
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < elemSize; j++)
                list.add(random.nextInt());

            heap.addAll(list);
            assertTrue(invariant(heap));
            assertEquals(heap.size(), elemSize);
        }
    }

    @Test
    public void remove() {
        for (int i = 0; i < 10000; i++) {
            BinaryHeap<Integer> heap = new BinaryHeap<>();

            for (int j = 0; j < random.nextInt(1000); j++)
                heap.add(random.nextInt());

            String start = heap.toString();
            int heapSize = heap.size();
            if (heapSize == 0)
                continue;

            int elem = heap.get(random.nextInt(heapSize));
            assertTrue(heap.contains(elem));

            heap.remove((Object) elem);

            if (!invariant(heap)) {
                System.out.println(start);
                System.out.println(elem);
                System.out.println(heap);
            }

            assertFalse(heap.contains(elem));
            assertTrue(invariant(heap));
            assertEquals(heap.size() + 1, heapSize);
        }
    }


    private Random random;
}
