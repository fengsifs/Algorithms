import java.util.Scanner;

public class Main {
    public static void main(String[] agrs) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int sum = 0;
        int temp = 0;
        while (k-- > 0) {
            int s = scanner.nextInt();
            if (temp + s > 0)
                temp += s;
            else
                temp = 0;
            if (temp > sum)
                sum = temp;
        }
        System.out.println(sum);
    }
}