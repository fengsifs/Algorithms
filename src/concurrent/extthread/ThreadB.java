package concurrent.extthread;

import concurrent.service.Service;

/**
 * Created by FengSi on 2016/09/08 at 15:35.
 */
public class ThreadB extends Thread {

    private Service service;

    public ThreadB(Service service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}
