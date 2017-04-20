package concurrent.producersAndConsumers;

import satellite.anomalyDetection.Test;

import java.util.concurrent.Semaphore;

/**
 * Created by FengSi on 2017/04/20 at 17:53.
 */
public class UseSemaphore {
    int count = 0;
    final Semaphore put = new Semaphore(5); // 初始令牌个数
    final Semaphore get = new Semaphore(0);
    final Semaphore mutex = new Semaphore(1);

    public static void main(String[] args) {
        UseSemaphore test = new UseSemaphore();
        new Thread(test.new Producer()).start();
        new Thread(test.new Consumer()).start();
        new Thread(test.new Consumer()).start();
        new Thread(test.new Producer()).start();
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    put.acquire();
                    mutex.acquire();
                    count++;
                    System.out.println(Thread.currentThread().getName() + " produce:" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                    get.release();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    get.acquire();
                    mutex.acquire();
                    count--;
                    System.out.println(Thread.currentThread().getName() + " consume: " + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                    put.release();
                }
            }
        }
    }

}


