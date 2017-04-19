package course;

import java.util.Arrays;

/**
 * Created by FengSi on 2017/03/14 at 12:35.
 */
public class Reflect {
    public static void main(String[] args) throws ClassNotFoundException {
        Class class1 = null;
        class1 = Class.forName("course.Prime");
        System.out.println(Arrays.toString(class1.getDeclaredMethods()));
    }
}
