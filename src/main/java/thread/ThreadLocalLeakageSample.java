package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal 内存泄漏例子
 */
public class ThreadLocalLeakageSample {

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws InterruptedException {
        bs();
        Thread.sleep(1000L);
        executor.execute(() -> System.out.println("doSomething"));
        Thread.sleep(1000L);
        System.gc();
        Thread.sleep(1000L);
        executor.shutdown();
    }


    public static void bs() {
        FThreadLocal<FData> fDataFThreadLocal = new FThreadLocal<>();
        executor.execute(() -> {
            fDataFThreadLocal.set(new FData());
        });
    }


    static class FThreadLocal<T> extends ThreadLocal<T> {
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println(this + " gc");
        }
    }

    static class FData {
        private byte[] bytes = new byte[1024 * 1024];

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println(this + " gc");
        }
    }
}
