package concurrent.test;

import concurrent.extthread.ThreadA;
import concurrent.service.Service;

/**
 * Created by FengSi on 2016/09/08 at 15:30.
 */
public class Run {

    public static void main(String[] args) {
        Service service = new Service();

        ThreadA[] a = new ThreadA[10];
        for (int i = 0; i < 10; i++) {
            a[i] = new ThreadA(service);
            a[i].start();
        }
    }
}
