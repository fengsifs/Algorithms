package interview;

/**
 * Created by FengSi on 2017/03/03 at 15:17.
 */
public class ThreadTest {
    public static void main(String args[]) {
        Thread t = new Thread(ThreadTest::pong);
        // start和run的区别，run是立即执行
        t.start();
        t.run();
        System.out.print("ping");
    }
    static void pong() {
        System.out.print("pong");
    }
}
