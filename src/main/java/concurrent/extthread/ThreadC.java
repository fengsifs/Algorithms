package concurrent.extthread;

import concurrent.service.Service;

/**
 * Created by FengSi on 2016/09/08 at 15:37.
 */
public class ThreadC extends Thread {

    private Service service;

    public ThreadC(Service service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}
