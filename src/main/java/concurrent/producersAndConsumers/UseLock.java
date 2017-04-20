package concurrent.producersAndConsumers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by FengSi on 2017/04/20 at 15:30.
 */
public class UseLock {
    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        Warehouse1 warehouse = new Warehouse1(1000);
        list.add(new ConsumeThread(100, warehouse));
        list.add(new ConsumeThread(200, warehouse));
        list.add(new ConsumeThread(800, warehouse));
        list.add(new ConsumeThread(600, warehouse));
        list.add(new ProduceThread(300, warehouse));
        list.add(new ProduceThread(200, warehouse));
        list.add(new ProduceThread(100, warehouse));
        list.add(new ProduceThread(500, warehouse));
        list.add(new ProduceThread(400, warehouse));
        list.add(new ProduceThread(600, warehouse));
        list.add(new ProduceThread(700, warehouse));
        for (Thread thread : list) {
            thread.start();
        }
    }
}

class Warehouse1 {
    private int capacity;
    private List<Object> list = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();

    public Warehouse1(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void produce(int num) throws InterruptedException {
        try {
            lock.lock();
            while (num + list.size() > this.capacity) {
                System.out.println("仓库满，现有产品数量：" + list.size() + "，无法再容纳" + num + "件产品。");
                empty.await();
            }
            System.out.println("仓库现有产品数量：" + list.size() + "，准备生产" + num + "件产品");
            for (int i = 0; i < num; i++) {
                list.add(new Object());
            }
            System.out.println("完成生产，仓库现有产品数量：" + list.size());
            empty.signalAll();
            full.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void consume(int num) throws InterruptedException {
        try {
            lock.lock();
            while (num > list.size()) {
                System.out.println("仓库现有产品数量：" + list.size() + "，无法提供" + num + "件产品。");
                full.await();
            }
            System.out.println("仓库现有产品数量：" + list.size() + "，准备消费" + num + "件产品");
            for (int i = 0; i < num; i++) {
                list.remove(0);
            }
            System.out.println("完成消费，仓库现有产品数量：" + list.size());
            full.signalAll();
            empty.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

class ProduceThread extends Thread {
    private int num;
    private Warehouse1 warehouse;

    public ProduceThread(int num, Warehouse1 warehouse) {
        this.num = num;
        this.warehouse = warehouse;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        try {
            warehouse.produce(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ConsumeThread extends Thread {
    private int num;
    private Warehouse1 warehouse;

    public ConsumeThread(int num, Warehouse1 warehouse) {
        this.num = num;
        this.warehouse = warehouse;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        try {
            warehouse.consume(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}