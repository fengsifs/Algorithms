package concurrent.service;

import java.util.concurrent.Semaphore;

/**
 * Created by FengSi on 2016/09/08 at 15:25.
 */
public class Service {
    private Semaphore semaphore = new Semaphore(10);

    public void testMethod() {
        try {
            semaphore.acquire(2);
            System.out.println(Thread.currentThread().getName() +
                    " begin timer= " + System.currentTimeMillis());
            int sleepValue = (int) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() +
                    " 停止了 " + (sleepValue / 1000) + " 秒 ");
            Thread.sleep(sleepValue);
            System.out.println(Thread.currentThread().getName() +
                    " end timer= " + System.currentTimeMillis());
            semaphore.release(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
