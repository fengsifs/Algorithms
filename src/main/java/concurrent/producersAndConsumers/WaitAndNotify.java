package concurrent.producersAndConsumers;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by FengSi on 2017/04/20 at 14:58.
 */
public class WaitAndNotify {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse(1000);
        ProducerThread p1 = new ProducerThread(warehouse, 200);
        ProducerThread p2 = new ProducerThread(warehouse, 200);
        ProducerThread p3 = new ProducerThread(warehouse, 100);
        ProducerThread p4 = new ProducerThread(warehouse, 300);
        ProducerThread p5 = new ProducerThread(warehouse, 400);
        ProducerThread p6 = new ProducerThread(warehouse, 200);
        ProducerThread p7 = new ProducerThread(warehouse, 500);

        ConsumerThread c1 = new ConsumerThread(warehouse, 500);
        ConsumerThread c2 = new ConsumerThread(warehouse, 200);
        ConsumerThread c3 = new ConsumerThread(warehouse, 800);

        c1.start();
        c2.start();
        c3.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();

    }
}

class Warehouse {
    private int capacity;
    private List<Object> list = new LinkedList<>();

    public Warehouse(int capacity) {
        this.capacity = capacity;
        System.out.println("当前仓库产品数量： " + list.size());
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void produce(int num) throws InterruptedException {
        synchronized (list) {
            while (list.size() + num > capacity) {
                System.out.println("仓库空间不够再生产" + num + "个产品，当前仓库产品数量：" + list.size());
                list.wait();
            }

            System.out.println("仓库未满，可以生产" + num + "个产品，生产前仓库产品数量：" + list.size());
            for (int i = 0; i < num; i++) {
                list.add(new Object());
            }
            list.notifyAll();
        }
    }

    public void consume(int num) throws InterruptedException {
        synchronized (list) {
            while (list.size() < num) {
                System.out.println("仓库产品仅剩" + list.size() + "件，无法满足消费需求" + num + "件");
                list.wait();
            }
            System.out.println("仓库目前产品数量：" + list.size());
            for (int i = 0; i < num; i++) {
                list.remove(0);
            }
            System.out.println("仓库目前产品数量：" + list.size());
            list.notifyAll();
        }
    }
}

class ProducerThread extends Thread {
    private int num;
    private Warehouse warehouse;

    public ProducerThread(Warehouse warehouse, int num) {
        this.num = num;
        this.warehouse = warehouse;
    }

    public void run() {
        try {
            warehouse.produce(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ConsumerThread extends Thread {
    private int num;
    private Warehouse warehouse;

    public ConsumerThread(Warehouse warehouse, int num) {
        this.num = num;
        this.warehouse = warehouse;
    }

    public void run() {
        try {
            warehouse.consume(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}