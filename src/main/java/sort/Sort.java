package sort;

public interface Sort<T extends Comparable> {
    void sort(T[] elements);

    default boolean shouldSort(T[] elements) {
        return elements != null && elements.length > 1;
    }

    default void swap(T[] elements, int i, int j) {
        if (i == j) {
            return;
        }
        T tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }
}
