package sort;

/**
 * 希尔排序
 * 时间复杂度 O(n^1.3)
 * 空间复杂度 O(1)
 * 不稳定
 */
public class ShellSort<T extends Comparable> implements Sort<T> {

    @Override
    public void sort(T[] elements) {
        if (!shouldSort(elements)) {
            return;
        }
        for (int step = elements.length >> 1; step > 0; step >>= 1) {
            for (int i = step; i < elements.length; i += step) {
                for (int j = i; j >= 0; j -= step) {
                    if (j - step < 0) {
                        break;
                    }
                    if (elements[j].compareTo(elements[j - step]) < 0) {
                        swap(elements, j, j - step);
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
