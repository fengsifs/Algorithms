package concurrent.test;

import concurrent.extthread.ThreadA;
import concurrent.extthread.ThreadB;
import concurrent.extthread.ThreadC;
import concurrent.service.Service;

/**
 * Created by FengSi on 2016/09/08 at 15:30.
 */
public class Run {

    public static void main(String[] args) {
        Service service = new Service();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        ThreadB b = new ThreadB(service);
        b.setName("B");
        ThreadC c = new ThreadC(service);
        c.setName("C");
        a.start();
        b.start();
        c.start();
    }
}
