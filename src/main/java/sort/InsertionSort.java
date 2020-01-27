package sort;

/**
 * 插入排序
 * 时间复杂度 O(n^2)
 * 空间复杂度 O(1)
 * 稳定
 */
public class InsertionSort<T extends Comparable> implements Sort<T> {

    @Override
    public void sort(T[] elements) {
        if (!shouldSort(elements)) {
            return;
        }
        T tmp;
        for (int i = 1; i < elements.length; i++) {
            tmp = elements[i];
            int j = i - 1;
            while (j >= 0 && tmp.compareTo(elements[j]) < 0) {
                elements[j + 1] = elements[j];
                j--;
            }
            elements[j + 1] = tmp;
        }
    }
}
