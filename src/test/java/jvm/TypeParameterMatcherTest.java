package jvm;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.Throughput)
@State(Scope.Benchmark)
@Fork(1)
@Threads(1)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
public class TypeParameterMatcherTest<T> {

    private AbstractListener<Integer> abstractListener = new AbstractListener<Integer>() {
        @Override
        public void accept0(Integer event) {
        }
    };

    @Benchmark
    public void test() {
        abstractListener.accept(1);
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                // 导入要测试的类
                .include(TypeParameterMatcherTest.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}