package concurrent.service;

import java.util.concurrent.Semaphore;

/**
 * Created by FengSi on 2016/09/08 at 15:25.
 */
public class Service {
    private Semaphore semaphore = new Semaphore(4);

    public void testMethod() {
        if (semaphore.tryAcquire(2)) {
            System.out.println("进入" + Thread.currentThread().getName());
            semaphore.release(2);
        } else {
            System.out.println("失败" + Thread.currentThread().getName());
        }
    }
}
