package concurrent.test;

import concurrent.extthread.ThreadA;
import concurrent.extthread.ThreadB;
import concurrent.myservice.MyService;
import concurrent.service.Service;

/**
 * Created by FengSi on 2016/09/08 at 15:30.
 */
public class Run {

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        service.testMethod();
    }
}
