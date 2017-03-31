import java.util.*;

public class Main {
    public static void main(String[] agrs) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] cabs = new int[n][2];
        for (int i = 0; i < n; i++) {
            cabs[i][0] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            cabs[i][1] = scanner.nextInt();
        }
        int[] g = new int[2];
        for (int i = 0; i < 2; i++) {
            g[i] = scanner.nextInt();
        }
        int walkTime = scanner.nextInt();
        int taxiTime = scanner.nextInt();
        System.out.println(min(cabs, g, walkTime, taxiTime));
    }

    private static int min(int[][] cabs, int[] g, int walkTime, int taxiTime) {
        Arrays.sort(cabs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Math.abs(o1[0]) + Math.abs(o1[1]) - Math.abs(o2[0]) - Math.abs(o2[1]);
            }
        });
        boolean[] records = new boolean[4];
        int count = 0;
        int min = walkTime * (Math.abs(g[0]) + Math.abs(g[1]));
        for (int i = 0; i < cabs.length; i++) {
            min = Math.min(min, (Math.abs(cabs[i][0]) + Math.abs(cabs[i][1])) * walkTime +
                    (Math.abs(cabs[i][0] - g[0]) + Math.abs(cabs[i][1] - g[1])) * taxiTime);
            int pos = pos(cabs[i]);
            if (!records[pos]) {
                count++;
                records[pos] = true;
                if (count == 4) {
                    return min;
                }
            }
        }
        return min;
    }

    private static int pos(int[] xy) {
        if (xy[0] > 0 && xy[1] >= 0) {
            return 1;
        } else if (xy[0] <= 0 && xy[1] > 0) {
            return 2;
        } else if (xy[0] < 0 && xy[1] <= 0) {
            return 3;
        } else {
            return 4;
        }
    }

    private static void magic(int[] nums, long k) {
        List<int[]> list = new ArrayList<>();
        int[] t = new int[nums.length];
        System.arraycopy(nums, 0, t, 0, nums.length);
        list.add(t);
        int step = 0;
        while (step < k) {
            step++;
            move(nums);
            for (int i = 0; i < list.size(); i++) {
                if (same(nums, list.get(i))) {
                    System.out.println(step);
                    int pos = (int) ((k - step) % (step - i) + i);
                    System.arraycopy(list.get(pos), 0, nums, 0, nums.length);
                    return;
                }
            }
            int[] x = new int[nums.length];
            System.arraycopy(nums, 0, x, 0, nums.length);
            list.add(x);
        }
    }

    private static boolean same(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    private static void move(int[] nums) {
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                temp[i] = nums[i] + nums[0];
            } else {
                temp[i] = nums[i] + nums[i + 1];
            }
            if (temp[i] >= 100) {
                temp[i] -= 100;
            }
        }
        System.arraycopy(temp, 0, nums, 0, nums.length);
    }

    private static int change(String s) {
        int res = 0;
        if (s == null || s.length() < 3 || check(s)) {
            return res;
        }
        int count = 0;
        int leftB = -1;
        int leftG = -1;
        int rightB = -1;
        int rightG = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'B') {
                count++;
                rightB = i;
                if (leftB == -1) {
                    leftB = i;
                }
            } else {
                rightG = i;
                if (leftG == -1) {
                    leftG = i;
                }
            }
        }
        if (count == 0 || count == s.length()) {
            return 0;
        }

        return res;
    }

    private static int countB(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'B') {
                count++;
            }
        }
        return count;
    }

    private static String exch(String s, int i) {
        if (i == 0) {
            return s.charAt(1) + s.charAt(0) + s.substring(2);
        } else if (i == s.length() - 2) {
            return s.substring(0, i) + s.charAt(s.length() - 1) + s.charAt(i);
        } else {
            return s.substring(0, i) + s.charAt(i + 1) + s.charAt(i) + s.substring(i + 2);
        }
    }

    // 检查一个字符串是否符合要求
    private static boolean check(String s) {
        int count = 0;
        char c = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != c) {
                c = s.charAt(i);
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                int t = c - '0';
                stack.push(t);
            } else if (c >= 'a' && c <= 'f') {
                int t = c - 'a' + 10;
                stack.push(t);
            } else {
                int a = stack.pop();
                int b = stack.pop();
                if (c == '+') {
                    stack.push(a + b);
                } else if (c == '-') {
                    stack.push(b - a);
                } else {
                    stack.push(a * b);
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{1, 2});
                }
            }
        }
        return stack.peek();
    }
}