package concurrent.myservice;

import java.util.concurrent.Semaphore;

/**
 * Created by FengSi on 2016/09/08 at 16:06.
 */
public class MyService {

    private Semaphore semaphore = new Semaphore(10);

    public void testMethod() {
        try {
            semaphore.acquire();
            System.out.println(semaphore.availablePermits());
            System.out.println(semaphore.drainPermits() + " "
                    + semaphore.availablePermits());
            System.out.println(semaphore.drainPermits() + " "
                    + semaphore.availablePermits());
            System.out.println(semaphore.drainPermits() + " "
                    + semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
