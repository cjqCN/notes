package queue;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@BenchmarkMode(Mode.Throughput)
@State(Scope.Benchmark)
@Fork(1)
@Threads(1)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class CQueueTest {
    CQueue<Integer> cQueue = new CQueue<>(16);
    Queue<Integer> bQueue = new BQueue<>(131072);
    Queue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(131072);

    @Benchmark
    public void testCQueue() {
        for (int j = 0; j < 100000; j++) {
            cQueue.offer(j);
        }
        int j = 0;
        try {
            for ( j = 0; j < 100000; j++) {
                if (cQueue.poll() != j) {
                    throw new IllegalStateException(cQueue.poll().toString() + "   " + j);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(j);
            System.out.println(cQueue.head);
            System.out.println(cQueue.poll());
        }
    }

    @Benchmark
    public void testBQueue() {
        for (int j = 0; j < 100000; j++) {
            bQueue.offer(j);
        }
        for (int j = 0; j < 100000; j++) {
            if (bQueue.poll() != j) {
                throw new IllegalStateException(bQueue.poll().toString() + "   " + j);
            }
        }
    }

    @Benchmark
    public void testArrayBlockingQueue() {
        for (int j = 0; j < 100000; j++) {
            arrayBlockingQueue.offer(j);
        }
        for (int j = 0; j < 100000; j++) {
            if (arrayBlockingQueue.poll() != j) {
                throw new IllegalStateException();
            }
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                // 导入要测试的类
                .include(CQueueTest.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
