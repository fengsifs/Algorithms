package concurrent.producersAndConsumers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by FengSi on 2017/04/20 at 16:50.
 */
public class UseBlockingQueue {
    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        Storehouse warehouse = new Storehouse(1000);
        list.add(new Consumer(200, warehouse));
        list.add(new Consumer(800, warehouse));
        list.add(new Consumer(500, warehouse));
        list.add(new Producer(200, warehouse));
        list.add(new Producer(200, warehouse));
        list.add(new Producer(100, warehouse));
        list.add(new Producer(300, warehouse));
        list.add(new Producer(400, warehouse));
        list.add(new Producer(200, warehouse));
        list.add(new Producer(500, warehouse));
        for (Thread thread : list) {
            thread.start();
        }
    }
}

class Storehouse {
    private int capacity;
    private BlockingQueue<Object> blockingQueue;
    private AtomicInteger curNum = new AtomicInteger(0);

    public Storehouse(int capacity) {
        this.capacity = capacity;
        this.blockingQueue = new ArrayBlockingQueue<>(capacity);
    }

    public void produce(int num) {
        while (num + curNum.get() > capacity) {
            System.out.println("仓库满，现有产品数量：" + curNum.get() + "，无法再容纳" + num + "件产品。");
        }

        System.out.println("仓库现有产品数量：" + blockingQueue.size() + "，准备生产" + num + "件产品");
        for (int i = 0; i < num; i++) {
            try {
                blockingQueue.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            curNum.incrementAndGet();
        }
        System.out.println("完成生产，仓库现有产品数量：" + blockingQueue.size());
    }

    public void consume(int num) {
        while (num > curNum.get()) {
            System.out.println("仓库现有产品数量：" + curNum.get() + "，无法提供" + num + "件产品。");
        }

        System.out.println("仓库现有产品数量：" + curNum.get() + "，准备消费" + num + "件产品");
        for (int i = 0; i < num; i++) {
            try {
                blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            curNum.decrementAndGet();
        }
        System.out.println("完成消费，仓库现有产品数量：" + curNum.get());
    }
}

class Producer extends Thread {
    private int num;
    private Storehouse storehouse;

    public Producer(int num, Storehouse storehouse) {
        this.num = num;
        this.storehouse = storehouse;
    }

    @Override
    public void run() {
        storehouse.produce(num);
    }
}

class Consumer extends Thread {
    private int num;
    private Storehouse storehouse;

    public Consumer(int num, Storehouse storehouse) {
        this.num = num;
        this.storehouse = storehouse;
    }

    @Override
    public void run() {
        storehouse.consume(num);
    }
}