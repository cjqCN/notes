package sort;

/**
 * 冒泡排序
 * 时间复杂度 O(n^2)
 * 空间复杂度 O(1)
 * 稳定
 */
public class BubbleSort<T extends Comparable> implements Sort<T> {

    @Override
    public void sort(T[] elements) {
        if (!shouldSort(elements)) {
            return;
        }
        for (int i = 1; i < elements.length; i++) {
            for (int j = 0; j < elements.length - i; j++) {
                if (elements[j].compareTo(elements[j + 1]) > 0) {
                    swap(elements, j, j + 1);
                }
            }
        }
    }
}
