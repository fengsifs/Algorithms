package concurrent.test;

import concurrent.extthread.ThreadA;
import concurrent.extthread.ThreadB;
import concurrent.service.Service;

/**
 * Created by FengSi on 2016/09/08 at 15:30.
 */
public class Run {

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();

        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();

        Thread.sleep(1000);

        b.interrupt();
        System.out.println("main中断了a");
    }
}
