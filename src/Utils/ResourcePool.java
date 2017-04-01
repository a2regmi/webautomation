package Utils;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/** Modified version of ResourcePool http://www.javamex.com/tutorials/synchronization_concurrency_semaphore2.shtml */
public class ResourcePool<T> {

    private Semaphore sem;
    private final Queue<T> resources = new ConcurrentLinkedQueue<>();

    public ResourcePool(int maxResources) {
        sem = new Semaphore(maxResources, true);
    }

    public T getResource(long maxWaitMillis)
            throws InterruptedException {
        sem.tryAcquire( maxWaitMillis, TimeUnit.SECONDS);
        return resources.remove();
    }

    public void returnResource(T res) {
        resources.add(res);
        sem.release();
    }

    public boolean contains(T res){
        return resources.contains(res);
    }
}
