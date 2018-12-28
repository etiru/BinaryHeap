package binaryheap;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryHeapTest {

    public BinaryHeapTest() {
        random = new Random();
    }
    //метод, который проверяет кучу, на верное расположение элементов
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

            int heapSize = heap.size();
            if (heapSize == 0)
                continue;

            int elem = heap.get(random.nextInt(heapSize));
            assertTrue(heap.contains(elem));

            heap.remove((Object) elem);

            assertFalse(heap.contains(elem));
            assertTrue(invariant(heap));
            assertEquals(heap.size() + 1, heapSize);
        }
    }
    @Test
    //remove(): возвращает с удалением элемент из начала очереди.
    // Если очередь пуста, генерирует исключение NoSuchElementException
    public void removeQueue(){
        BinaryHeap<Integer> typeNull = new BinaryHeap<Integer>();
        typeNull.add(null);
        assertThrows(NoSuchElementException.class, () -> typeNull.remove());

        for (int i = 0; i < 10000; i++) {
            BinaryHeap<Integer> heap = new BinaryHeap<>();

            for (int j = 0; j < random.nextInt(1000); j++)
                heap.add(random.nextInt());

            int heapSize = heap.size();
            if (heapSize == 0)
                continue;

            int elem = heap.get(0);
            int remove = heap.remove();

            assertEquals(elem, remove);
            assertTrue(invariant(heap));
        }

    }
    //poll(): возвращает с удалением элемент из начала очереди. Если очередь пуста, возвращает значение null
    @Test
    public void queuePoll(){
        BinaryHeap<Integer> testNull = new BinaryHeap<>();
        assertEquals(null, testNull.poll());

        for (int i = 0; i < 10000; i++) {
            BinaryHeap<Integer> heap = new BinaryHeap<>();

            for (int j = 0; j < random.nextInt(1000); j++)
                heap.add(random.nextInt());

            int heapSize = heap.size();
            if (heapSize == 0)
                continue;

            int elem = heap.get(0);
            int pool = heap.poll();

            assertEquals(elem, pool);
            assertTrue(invariant(heap));
        }
    }
    //peek(): возвращает без удаления элемент из начала очереди. Если очередь пуста, возвращает значение null
    @Test
    public void queuePeek(){
        BinaryHeap<Integer> testNull = new BinaryHeap<>();
        assertEquals(null, testNull.peek());

        for (int i = 0; i < 10000; i++) {
            BinaryHeap<Integer> heap = new BinaryHeap<>();

            for (int j = 0; j < random.nextInt(1000); j++)
                heap.add(random.nextInt());

            int heapSize = heap.size();
            if (heapSize == 0)
                continue;

            int elem = heap.get(0);
            int peek = heap.peek();

            assertEquals(elem, peek);
            heap.peek();
            assertTrue(heap.contains(elem));
            assertTrue(invariant(heap));
        }


    }
    //element(): возвращает, но не удаляет, элемент из начала очереди. Если очередь пуста,
    //генерирует исключение NoSuchElementException
    @Test
    public void queueElement(){
        BinaryHeap<Integer> typeNull = new BinaryHeap<Integer>();
        typeNull.add(null);
        assertThrows(NoSuchElementException.class, () -> typeNull.element());

        for (int i = 0; i < 10000; i++) {
            BinaryHeap<Integer> heap = new BinaryHeap<>();

            for (int j = 0; j < random.nextInt(1000); j++)
                heap.add(random.nextInt());

            String start = heap.toString();
            int heapSize = heap.size();
            if (heapSize == 0)
                continue;

            int elem = heap.get(0);
            int elemQueue = heap.element();

            assertEquals(elem, elemQueue);
            assertTrue(invariant(heap));
        }

    }

    private Random random;
}
