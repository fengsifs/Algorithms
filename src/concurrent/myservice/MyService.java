package concurrent.myservice;

import java.util.concurrent.Semaphore;

/**
 * Created by FengSi on 2016/09/08 at 16:06.
 */
public class MyService {

    private boolean isFair = true;
    private Semaphore semaphore = new Semaphore(1, isFair);

    public void testMethod() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
