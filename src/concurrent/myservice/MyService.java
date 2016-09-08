package concurrent.myservice;

import java.util.concurrent.Semaphore;

/**
 * Created by FengSi on 2016/09/08 at 16:06.
 */
public class MyService {

    private Semaphore semaphore = new Semaphore(1);

    public void testMethod() {
        try {
            semaphore.acquire();
            Thread.sleep(1000);
            System.out.println(semaphore.getQueueLength());
            System.out.println(semaphore.hasQueuedThreads());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
