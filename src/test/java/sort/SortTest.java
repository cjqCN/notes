package sort;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ThreadLocalRandom;

@BenchmarkMode(Mode.Throughput)
@State(Scope.Benchmark)
@Fork(1)
@Threads(1)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
public class SortTest {


    @Benchmark
    public void testCreateRandomTestData() {
        createRandomTestData();
    }

    @Benchmark
    public void quickSort() {
        testSort(new QuickSort<>(), createRandomTestData());
    }

    @Benchmark
    public void bubbleSort() {
        testSort(new BubbleSort<>(), createRandomTestData());
    }

    @Benchmark
    public void insertionSort() {
        testSort(new InsertionSort<>(), createRandomTestData());
    }

    @Benchmark
    public void shellSort() {
        testSort(new ShellSort<>(), createRandomTestData());
    }

    @Benchmark
    public void selectionSort() {
        testSort(new SelectionSort<>(), createRandomTestData());
    }

//    @Benchmark
//    public void mergeSort() {
//        testSort(new MergeSort<>(), createRandomTestData());
//    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                // 导入要测试的类
                .include(SortTest.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }

    public static <T extends Comparable> void testSort(Sort<T> sorter, T[] testData) {
        sorter.sort(testData);
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
