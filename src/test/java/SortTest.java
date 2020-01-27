package sort;

import java.util.concurrent.ThreadLocalRandom;

public class SortTest {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            testSort(new QuickSort<>(), createRandomTestData());
            testSort(new BubbleSort<>(), createRandomTestData());
            testSort(new InsertionSort<>(), createRandomTestData());
            testSort(new ShellSort<>(), createRandomTestData());
            testSort(new SelectionSort<>(), createRandomTestData());
            testSort(new MergeSort<>(), createRandomTestData());
            System.out.println("-------------------------------");
        }
    }


    public static <T extends Comparable> void testSort(Sort<T> sorter, T[] testData) {
        long start = System.nanoTime();
        sorter.sort(testData);
        System.out.println(sorter.getClass().getSimpleName() + "耗时：" + (System.nanoTime() - start) / 1000000.0f);
        verifySortRes(testData);
    }


    public static Integer[] createRandomTestData() {
        Integer[] res = new Integer[10000];
        for (int i = 0; i < res.length; i++) {
            res[i] = ThreadLocalRandom.current().nextInt(1000000);
        }
        return res;
    }

    public static <T extends Comparable> void verifySortRes(T[] res) {
        for (int i = 0; i < res.length - 1; i++) {
            if (res[i].compareTo(res[i + 1]) > 0) {
                throw new RuntimeException("sort error");
            }

        }
    }
}
