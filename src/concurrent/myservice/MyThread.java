package concurrent.myservice;

/**
 * Created by FengSi on 2016/09/08 at 18:14.
 */
public class MyThread extends Thread {

    private MyService myService;

    public MyThread(MyService myService) {
        super();
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}
