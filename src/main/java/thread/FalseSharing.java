package thread;

import sun.misc.Contended;

/**
 * 伪共享测试例子
 */
public final class FalseSharing {

    public final static int NUM_THREADS = Runtime.getRuntime().availableProcessors();
    public final static long ITERATIONS = 100L * 1000L * 1000L;


    /**
     * NUM_THREADS:8
     * DefaultCounter cost:11292
     * PaddingCounter cost:1750
     * ContendedCounter cost:11592
     * <p>
     * -XX:-RestrictContended
     * NUM_THREADS:8
     * DefaultCounter cost:11292
     * PaddingCounter cost:1750
     * ContendedCounter cost:1997
     */
    public static void main(final String[] args) throws Exception {
        System.out.println("NUM_THREADS:" + NUM_THREADS);

        Counter[] counters = new Counter[NUM_THREADS];
        for (int i = 0; i < counters.length; i++) {
            counters[i] = new DefaultCounter();
        }
        runTest(counters);

        for (int i = 0; i < counters.length; i++) {
            counters[i] = new PaddingCounter();
        }
        runTest(counters);

        for (int i = 0; i < counters.length; i++) {
            counters[i] = new ContendedCounter();
        }
        runTest(counters);

    }

    private static void runTest(Counter[] counters) throws InterruptedException {
        final long start = System.nanoTime();
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    counters[index].incr();
                }
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println(counters[0].getClass().getSimpleName() + " cost:" + (System.nanoTime() - start) / 1000_000);
    }


    interface Counter {
        void incr();
    }

    public final static class PaddingCounter implements Counter {
        public volatile long counter = 0L;
        public long p1, p2, p3, p4, p5, p6; // padding

        @Override
        public void incr() {
            counter++;
        }
    }

    /**
     * -XX:-RestrictContended
     */
    public final static class ContendedCounter implements Counter {
        @Contended
        public volatile long counter = 0L;

        @Override
        public void incr() {
            counter++;
        }
    }

    public final static class DefaultCounter implements Counter {
        public volatile long counter = 0L;

        @Override
        public void incr() {
            counter++;
        }
    }

}