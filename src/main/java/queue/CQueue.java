package queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class CQueue<E> implements Queue<E> {

    public final Object[] elements;
    public final int t;
    public final int capacity;
    public long head = 0;
    public long tail = 0;

    public CQueue(int twoHead) {
        this.t = twoHead + 1;
        this.capacity = 2 << twoHead;
        this.elements = new Object[capacity];
    }


    @Override
    public int size() {
        return (int) (tail - head);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
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
    public void clear() {

    }

    @Override
    public boolean offer(E e) {
        if (tail - head >= capacity) {
            return false;
        }
        elements[(int) (tail - (tail >> t << t))] = e;
        tail++;
        return true;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        if (head >= tail) {
            return null;
        }
        E e = (E) elements[(int) (head - (head >> t << t))];
        head++;
        return e;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    public static void main(String[] args) {
        CQueue<Integer> cQueue = new CQueue(16);
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 100000; j++) {
                cQueue.offer(j);
            }
            for (int j = 0; j < 100000; j++) {
                if (cQueue.poll() != j) {
                    throw new IllegalStateException(cQueue.poll().toString() + "   " + j);
                }
            }
        }
        System.out.println(cQueue.head + "   " + cQueue.tail);

    }
}
