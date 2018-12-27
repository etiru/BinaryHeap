package binaryheap;

import java.util.*;

import static java.lang.Math.min;

public class BinaryHeap<T extends Comparable<T>> extends LinkedList<T> {


    public void heapify(int i){
            int left, right;
            T temp;
            left = 2*i+1;
            right = 2*i+2;
            if (left < super.size()) {
                if (super.get(i).compareTo(super.get(left)) < 0) {
                    temp = super.get(i);
                    super.set(i, super.get(left));
                    super.set(left, temp);
                }
                heapify(left);
            }
            if (right < super.size()) {
                if (super.get(i).compareTo(super.get(right)) < 0) {
                    temp = super.get(i);
                    super.set(i, super.get(right));
                    super.set(right, temp);
                }
                heapify(right);
            }

        }


    // O(log n)
    @Override
    public boolean add(T t) {
        if (t == null || this.contains(t))
            return false;

        int i, middle;
        i = super.size();
        super.add(t);
        middle = (i - 1) / 2;

        while(middle >= 0 && i > 0) {
            T curr = super.get(i);
            T mid = super.get(middle);

            int comp = curr.compareTo(mid);
            if(comp > 0) {
                super.set(i, mid);
                super.set(middle, curr);
            }
            i = middle;
            middle = (i - 1) / 2;
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int i = super.indexOf(o);
        if (i < 0) return false;
        super.set(i, super.getLast());
        super.remove(super.size() - 1);

        heapify(i);
        return true;
    }


    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean state = true;

        for (T element : c)
            if (!this.add(element))
                state = false;

        return state;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();

        int i = 0;
        while (i < super.size() - 1) {
            int j = i;
            i = min(i * 2, super.size() - 1);

            for (T element : super.subList(j, i + 1))
                result.append(element.toString() + " ");

            result.append('\n');
            i++;
        }

        return result.toString();
    }

    @Override
    public boolean offer(T t) {
        return add(t);
    }

    @Override
    public T remove() {
        T result = super.getFirst();
        if(!remove(super.getFirst()))
            throw new NoSuchElementException();
        return result;
    }

    @Override
    public T poll() {
        if(super.isEmpty()) return null;
        T obj = super.get(0);
        remove(obj);
        return obj;
    }

    @Override
    public T element() {
        if(super.isEmpty()) throw new  NoSuchElementException();
        return super.get(0);
    }

    @Override
    public T peek() {
        if(super.isEmpty()) return null;
        return super.get(0);
    }
}