package binaryheap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryHeapTest {
    @Test
    public void add() {
//        Создано с помощью сайта https://visualgo.net/en/heap
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
        Integer[] testArr2 = {2, 7, 26, 25, 19, 17, 1, 90, 3, 36};
        heap.addAll(Arrays.asList(testArr2));

        Integer[] testArr = {90, 36, 17, 25, 26, 7, 1, 2, 3, 19};
        List<Integer> testHeap = Arrays.asList(testArr);
        assertEquals(testHeap, heap);


        BinaryHeap<Integer> heap2 = new BinaryHeap<Integer>();
        Integer[] testArr3 = {2, 7, 48, 1, 19, 40, 1, 12, 3, 36};
        heap2.addAll(Arrays.asList(testArr3));

        Integer[] testArr4 = {48, 36, 40, 19, 2, 7, 12, 1, 3};
        List<Integer> testHeap1 = Arrays.asList(testArr4);


        assertEquals(testHeap1, heap2);

    }

    @Test
    public void remove() {
        //Создано с помощью сайта https://visualgo.net/en/heap
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
        Integer[] testArr2 = {2, 7, 26, 25, 19, 17, 1, 90, 3, 36};
        heap.addAll(Arrays.asList(testArr2));
        System.out.println(heap.toString());

        heap.remove((Object) 1);
        System.out.println(heap.toString());


    }



}
