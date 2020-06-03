package queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class BQueue<E> implements Queue<E> {

    private final Object[] elements;
    private final int capacity;
    private long head = 0;
    private long tail = 0;

    public BQueue(int capacity) {
        this.capacity = capacity;
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
        elements[(int) (tail++ % capacity)] = e;
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
        return (E) elements[(int) (head++ % capacity)];
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

}
