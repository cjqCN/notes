package thread;

import java.util.concurrent.Semaphore;

public class Limiter extends Semaphore {

    private int maxPermit;
    private int concurrentPermit;

    public Limiter(int permits, int initPermits) {
        this(permits, false, initPermits);
    }

    public Limiter(int totalPermit, boolean fair, int initPermit) {
        super(totalPermit, fair);
        if (initPermit > totalPermit) {
            throw new IllegalArgumentException("initPermits > permits");
        }
        maxPermit = totalPermit;
        concurrentPermit = totalPermit;
        refresh(initPermit);
    }

    @Override
    public void acquire() throws InterruptedException {
        super.acquire();
    }

    private void reducePermit(int n) {
        super.reducePermits(n);
    }

    private void addPermit(int n) {
        super.release(n);
    }

    public void refresh(int permit) {
        if (permit > maxPermit) {
            throw new IllegalArgumentException("totalPermit > maxPermits");
        }
        int permitChange = concurrentPermit - permit;
        if (permitChange == 0) {
            return;
        }
        if (permitChange > 0) {
            reducePermit(permitChange);
        } else {
            addPermit(Math.abs(permitChange));
        }
        concurrentPermit = permit;
    }

    public int concurrentPermit() {
        return concurrentPermit;
    }


}
