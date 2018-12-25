import java.util.*;

public class BinaryHeap<T extends Comparable<T>> extends LinkedList<T> {

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
        return false;
    }


    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }


    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }
}
