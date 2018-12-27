package binaryheap;

import java.util.*;

import static java.lang.Math.min;

public class BinaryHeap<T extends Comparable<T>> extends ArrayList<T> implements Queue<T> {

    private void swap(int indexLeft, int indexRight) {
        T temp = super.get(indexLeft);
        super.set(indexLeft, super.get(indexRight));
        super.set(indexRight, temp);
    }

    // O(n)
    public void heapify() {
        if (super.isEmpty())
            return;

        for (int i = super.size() / 2; i >= 0; i--) {
            int leftIndex = 2 * i + 1;
            int rightIndex = leftIndex + 1;

            T parent = super.get(i);

            if (leftIndex < super.size()) {
                T left = super.get(leftIndex);
                if (left.compareTo(parent) > 0)
                    swap(leftIndex, i);
            }
            if (rightIndex < super.size()) {
                T right = super.get(rightIndex);
                if (right.compareTo(parent) > 0)
                    swap(rightIndex, i);
            }
        }
    }


    // O(log size)
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
        return i >= 0 && this.remove(i) != null;
    }

    @Override
    public T remove(int index) {
        if (index >= super.size())
            return null;

        T removeElement = super.get(index);

        while (index < super.size() / 2) {
            int leftIndex = 2 * index + 1;
            int rightIndex = leftIndex + 1;

            if (rightIndex >= super.size())
                break;

            T left = super.get(leftIndex);
            T right = super.get(rightIndex);

            if (left.compareTo(right) > 0) {
                swap(index, leftIndex);
                index = leftIndex;
            }
            else {
                swap(index, rightIndex);
                index = rightIndex;
            }
        }
        super.remove(index);
        heapify();
        return removeElement;
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
    public boolean removeAll(Collection<?> c) {
        boolean result = true;

        for (Object element : c)
            if (!super.remove(element))
                result = false;

        heapify();
        return result;
    }


    @Override
    public String toString(){
        List<String> strList = new ArrayList<>();

        int index = 0;
        while (index < super.size() - 1) {
            int j = index;
            index = min(index * 2, super.size() - 1);

            StringBuilder builder = new StringBuilder();

            for (T element : super.subList(j, index + 1))
                builder.append(element.toString()).append(" ");

            strList.add(builder.toString());
            index++;
        }

        int maxLength = 0;
        for (String line : strList)
            if (line.length() > maxLength)
                maxLength = line.length();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < strList.size() - 1; i++) {
            int diff = maxLength - strList.get(i).length();
            for (int j = 0; j < diff / 2; j++) {
                result.append(' ');
            }
            result.append(strList.get(i));
            result.append('\n');
        }

        if (strList.isEmpty())
            return "";

        result.append(strList.get(strList.size() - 1));
        return result.toString();
    }

    @Override
    public boolean offer(T t) {
        return add(t);
    }

    @Override
    public T remove() {
        T result = this.remove(0);
        if(result == null)
            throw new NoSuchElementException();

        return result;
    }

    @Override
    public T poll() {
        if(super.isEmpty())
            return null;

        return this.remove(0);
    }

    @Override
    public T element() {
        if(super.isEmpty())
            throw new NoSuchElementException();

        return super.get(0);
    }

    @Override
    public T peek() {
        if(super.isEmpty())
            return null;

        return super.get(0);
    }
}