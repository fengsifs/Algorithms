package satellite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by FengSi on 2016/12/13 at 15:26.
 */
public class CountLines {
    public static void main(String[] args) {
        try {
            File f = new File(args[0]);
            BufferedReader reader;
            File[] fs = f.listFiles();
            for (File file : fs) {
                reader = new BufferedReader(new FileReader(file));
                System.out.println(file.getName().split("\\.")[0] + " : " + reader.lines().count());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
