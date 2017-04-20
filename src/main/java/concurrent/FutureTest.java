package concurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by FengSi on 2017/04/20 at 14:29.
 */
public class FutureTest {
    public static void main(String[] args) {
        System.out.println("main Thread starts at: " + System.nanoTime());
        ExecutorService executor = Executors.newCachedThreadPool();
        HandleCallable task1 = new HandleCallable("1");
        HandleCallable task2 = new HandleCallable("2");
        HandleCallable task3 = new HandleCallable("3");
        Future<Integer> result1 = executor.submit(task1);
        Future<Integer> result2 = executor.submit(task2);
        Future<Integer> result3 = executor.submit(task3);
        executor.shutdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("task1运行结果:" + result1.get());
            System.out.println("task2运行结果:" + result2.get());
            System.out.println("task3运行结果:" + result3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main Thread finishes at: " + System.nanoTime());
    }
}

class HandleCallable implements Callable<Integer> {
    private String name;

    public HandleCallable(String name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("task" + name + "开始计算");
        Thread.sleep(3000);
        int sum = new Random().nextInt();
        int result = 0;
        for (int i = 0; i < sum; i++) {
            result += i;
        }
        return result;
    }
}
