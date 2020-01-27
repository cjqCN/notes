package sort;

/**
 * 快速排序
 * 时间复杂度 O(n*logn)
 * 空间复杂度 O(logn)
 * 不稳定
 */
public class QuickSort<T extends Comparable> implements Sort<T> {
    @Override
    public void sort(T[] elements) {
        if (!shouldSort(elements)) {
            return;
        }
        quickSort(elements, 0, elements.length - 1);
    }


    void quickSort(T[] elements, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int i = lo;
        int j = hi;
        T temp = elements[i];
        while (j > i) {
            while (j > i && elements[j].compareTo(temp) > 0) {
                --j;
            }
            if (j > i) {
                elements[i++] = elements[j];
            }
            while (i < j && elements[i].compareTo(temp) < 0) {
                ++i;
            }
            if (j > i) {
                elements[j--] = elements[i];
            }
        }
        elements[i] = temp;
        quickSort(elements, lo, i - 1);
        quickSort(elements, i + 1, hi);
    }


}
