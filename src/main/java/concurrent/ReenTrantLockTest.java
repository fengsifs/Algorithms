package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by FengSi on 2017/04/20 at 13:23.
 */
public class ReenTrantLockTest {
    static class NumberWrapper {
        public int value = 1;
    }

    public static void main(String[] args) {
        // 初始化可重入锁
        final Lock lock = new ReentrantLock();

        // 第一个条件当屏幕上输出到3
        final Condition reachThreeCondition = lock.newCondition();
        // 第二个条件当屏幕上输出到6
        final Condition reachSixCondition = lock.newCondition();

        // NumberWrapper只是为了封装一个数字，一边可以将数字对象共享，并可以设置为final
        // 注意这里不要用Integer，因为Integer是不可变对象
        final NumberWrapper num = new NumberWrapper();
        // 初始化线程A
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                lock.lock();
                    System.out.println("threadA start");
                    // A线程先输出3个数
                    while (num.value <= 3) {
                        System.out.println(num.value);
                        num.value++;
                    }
                    // 输出到3时要signal，告诉线程B可以开始了
                    reachThreeCondition.signal();
                } finally {
                    lock.unlock();
                }
                try {
                lock.lock();
                    reachSixCondition.await();
                    System.out.println("threadA start");
                    while (num.value <= 9) {
                        System.out.println(num.value);
                        num.value++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (num.value <= 3) {
                        reachThreeCondition.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                try {
                    lock.lock();
                    System.out.println("threadB start");
                    while (num.value <= 6) {
                        System.out.println(num.value);
                        num.value++;
                    }
                    reachSixCondition.signal();
                } finally {
                    lock.unlock();
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
