package designPattern;

/**
 * Created by FengSi on 2017/04/19 at 12:16.
 */
public class SingletonClass {
    private static class SingletonClassInstance {
        private static final SingletonClass instance = new SingletonClass();
    }

    public static SingletonClass getInstance() {
        return SingletonClassInstance.instance;
    }

    private SingletonClass() {

    }
}
