package concurrent.myservice;

import concurrent.tools.ListPool;

/**
 * Created by FengSi on 2016/09/08 at 18:14.
 */
public class MyThread extends Thread {

    private ListPool listPool;

    public MyThread(ListPool listPool) {
        super();
        this.listPool = listPool;
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            String getString = listPool.get();
            System.out.println(Thread.currentThread().getName() + " 取得 " + getString);
            listPool.put(getString);
        }
    }
}
