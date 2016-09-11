package concurrent.test;

import concurrent.extthread.ThreadA;
import concurrent.extthread.ThreadB;
import concurrent.myservice.MyService;
import concurrent.myservice.MyThread;
import concurrent.service.Service;
import concurrent.tools.ListPool;

/**
 * Created by FengSi on 2016/09/08 at 15:30.
 */
public class Run {

    public static void main(String[] args) throws InterruptedException {
        ListPool listPool = new ListPool();

        MyThread[] threads = new MyThread[12];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(listPool);
        }
        for (MyThread thread : threads) {
            thread.start();
        }
    }
}
