package concurrent.test;

import concurrent.extthread.ThreadA;
import concurrent.extthread.ThreadB;
import concurrent.myservice.MyService;
import concurrent.myservice.MyThread;
import concurrent.service.Service;

/**
 * Created by FengSi on 2016/09/08 at 15:30.
 */
public class Run {

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();

        MyThread myThread = new MyThread(service);
        myThread.start();

        MyThread[] threads = new MyThread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new MyThread(service);
            threads[i].start();
        }
    }
}
