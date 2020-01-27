package sort;

/**
 * 选择排序
 * 时间复杂度 O(n^2)
 * 空间复杂度 O(1)
 * 不稳定
 */
public class SelectionSort<T extends Comparable> implements Sort<T> {
    @Override
    public void sort(T[] elements) {
        if (!shouldSort(elements)) {
            return;
        }
        T temp;
        int index;
        for (int i = 0; i < elements.length; i++) {
            temp = elements[i];
            index = i;
            for (int j = i + 1; j < elements.length; j++) {
                if (temp.compareTo(elements[j]) > 0) {
                    temp = elements[j];
                    index = j;
                }
            }
            swap(elements, i, index);
        }
    }
}
