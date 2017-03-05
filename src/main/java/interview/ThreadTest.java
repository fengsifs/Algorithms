package interview;

/**
 * Created by FengSi on 2017/03/03 at 15:17.
 */
public class ThreadTest {
    public static void main(String args[]) {
        Thread t = new Thread(() -> pong());
        t.start();
        System.out.print("ping");
    }
    static void pong() {
        System.out.print("pong");
    }
}
