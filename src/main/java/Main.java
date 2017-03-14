import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> inputs = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while(line != null && !line.isEmpty()) {
            int value = Integer.parseInt(line.trim());
            if(value == 0) break;
            inputs.add(value);
            line = in.nextLine();
        }
        int[] A = new int[inputs.size()];
        for(int i=0; i<inputs.size(); i++) {
            A[i] = inputs.get(i).intValue();
        }
        int res = resolve(A);

        System.out.println(String.valueOf(res));
    }

    // write your code here
    public static int resolve(int[] A) {
        int[][] nums = new int[4][8];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                nums[i][j] = -1;
            }
        }
        for (int i : A) {
            int l = i / 100;
            i %= 100;
            int p = i / 10;
            i %= 10;
            nums[l-1][p-1] = i;
        }
        int sum = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 2; j++) {
                if (nums[1][j] == -1) {
                    continue;
                }
                for (int k = j * 2; k < j * 2 + 2; k++) {
                    if (k == j * 2 + 1 && nums[2][k - 1] == -1 && nums[2][k] == -1) {
                        sum += nums[0][0] + nums[1][j];
                        continue;
                    } else if (nums[2][k] == -1) {
                        continue;
                    }
                    int l = k * 2;
                    if (nums[3][l] == -1 && nums[3][l + 1] == -1) {
                        sum += nums[0][0] + nums[1][j] + nums[2][k];
                    } else if (nums[3][l] == -1) {
                        sum += nums[0][0] + nums[1][j] + nums[2][k] + nums[3][l + 1];
                    } else if (nums[3][l + 1] == -1) {
                        sum += nums[0][0] + nums[1][j] + nums[2][k] + nums[3][l];
                    } else {
                        sum += (nums[0][0] + nums[1][j] + nums[2][k]) * 2 + nums[3][l] + nums[3][l] + nums[3][l + 1];
                    }
                }
            }
        }
        if (sum == 0 && nums[0][0] > 0) {
            sum += nums[0][0];
        }
        return sum;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int i) {
            this.val = i;
        }
    }

}