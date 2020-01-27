package list;

import java.util.*;

public class QArrayList<T> implements List<T>, RandomAccess {

    private static final int DEFAULT_INIT_CAPACITY = 8;
    private static final Object[] EMPTY_ELEMENT = new Object[0];
    private Object[] elements;
    private int size;

    public QArrayList() {
        this(DEFAULT_INIT_CAPACITY);
    }

    public QArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            elements = EMPTY_ELEMENT;
        } else {
            throw new IllegalArgumentException("initialCapacity < 0, initialCapacity:" + initialCapacity);
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] res = new Object[size];
        System.arraycopy(elements, 0, res, 0, size);
        return res;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a != null) {
            System.arraycopy(elements, 0, a, 0, Math.min(size, a.length));
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        ensureCapacity(size + 1);
        elements[size++] = t;
        return true;
    }

    private void ensureCapacity(int needCapacity) {
        int nowCapacity = elements.length;
        if (nowCapacity > needCapacity) {
            return;
        }
        Object[] newElements = new Object[nowCapacity << 1];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
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
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
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
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public T get(int index) {
        return (T) elements[index];
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i > 0; i--) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i > 0; i--) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
