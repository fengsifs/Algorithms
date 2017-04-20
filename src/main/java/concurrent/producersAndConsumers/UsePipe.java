package concurrent.producersAndConsumers;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by FengSi on 2017/04/20 at 18:37.
 */
public class UsePipe {
    final PipedInputStream pis = new PipedInputStream();
    final PipedOutputStream pos = new PipedOutputStream();

    public static void main(String[] args) {
        UsePipe test = new UsePipe();
        new Thread(test.new Producer()).start();
        new Thread(test.new Consumer()).start();
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            try {
                pis.connect(pos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                while (true) {
                    int n = (int) (Math.random() * 255);
                    System.out.println(Thread.currentThread().getName() + " produce: " + n);
                    pos.write(n);
                    pos.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pis.close();
                    pos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable{
        @Override
        public void run() {
            int n;
            try {
                while (true) {
                    n = pis.read();
                    System.out.println(Thread.currentThread().getName() + " consume: " + n);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pis.close();
                    pos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

