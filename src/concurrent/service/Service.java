package concurrent.service;

import java.util.concurrent.Semaphore;

/**
 * Created by FengSi on 2016/09/08 at 15:25.
 */
public class Service {
    private Semaphore semaphore = new Semaphore(1);

    public void testMethod() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() +
                    " begin timer= " + System.currentTimeMillis());
            for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
                String newString = new String();
                Math.random();
            }
            System.out.println(Thread.currentThread().getName() +
                    " end timer= " + System.currentTimeMillis());
            semaphore.release();
        } catch (InterruptedException e) {
            System.out.println(" 线程 " + Thread.currentThread().getName()
                    + " 进入了 catch");
            e.printStackTrace();
        }
    }
}
