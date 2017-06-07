import java.util.*;

public class Main {
    public static void main(String[] agrs) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//        boolean[][] area = new boolean[n][m];
//        int persons = 0;
//        for (int i = 0; i < n; i++) {
//            String s = scanner.next();
//            for (int j = 0; j < m; j++) {
//                if (s.charAt(j) == '.') {
//                    area[i][j] = true;
//                    persons++;
//                }
//            }
//        }
//        System.out.println(count(n, m, area, persons));

        int[] nums = new int[n + 2];
        nums[0] = 1;
        nums[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            nums[i] = scanner.nextInt();
        }
        System.out.println(max(n, nums));
        int total = scanner.nextInt();
        List<Integer> cost = new ArrayList<>();
        List<Integer> value = new ArrayList<>();
        while (scanner.hasNext()) {
            cost.add(scanner.nextInt());
            value.add(scanner.nextInt());
        }
        int[][] mostValue = new int[cost.size() + 1][total + 1];
        for (int i = 1; i <= cost.size(); i++) {
            for (int j = 1; j <= total; j++) {
                if (cost.get(i - 1) <= j) {
                    mostValue[i][j] = Math.max(mostValue[i - 1][j], mostValue[i - 1][j - cost.get(i - 1)] + value.get(i - 1));
                } else {
                    mostValue[i][j] = mostValue[i - 1][j];
                }
            }
        }
        System.out.println(mostValue[cost.size()][total]);
        System.out.println();
    }

    private static int count(int n, int m, boolean[][] area, int persons) {
        boolean[][] move = new boolean[n][m];
        int[] count = new int[1];
        backTracking(count, area, move, 1, persons, n, m, n - 1, 0);
        return count[0];
    }

    private static void backTracking(int[] count, boolean[][] area, boolean[][] move, int step, int persons, int n, int m, int x, int y) {
        if (step == persons) {
            if (x == n - 1 && y == m - 1) {
                count[0]++;
            }
            return;
        }

        int[][] go = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        move[x][y] = true;
        boolean noNext = true;
        for (int[] aGo : go) {
            int nextX = x + aGo[0];
            int nextY = y + aGo[1];
            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m && area[nextX][nextY] && !move[nextX][nextY]) {
                noNext = false;
                backTracking(count, area, move, step + 1, persons, n, m, nextX, nextY);
            }
        }
        move[x][y] = false;
        if (noNext) {
            return;
        }
    }

    private static int max(int n, int[] nums) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[1];
        }
        int max = 0;
        while (n > 0) {
            if (n == 2) {
                max += nums[1] * nums[2] + Math.max(nums[1], nums[2]);
                break;
            } else {
                int[] points = new int[n + 2];
                int temp = 0;
                int index = 0;
                for (int i = 1; i <= n; i++) {
                    points[i] = nums[i - 1] * nums[i] * nums[i + 1];
                    if (points[i] > temp) {
                        temp = points[i];
                        index = i;
                    } else if (points[i] == temp) {
                        if (nums[i] < nums[index]) {
                            index = i;
                        }
                    }
                }
                max += temp;
                System.arraycopy(nums, index + 1, nums, index, n + 1 - index);
                n--;
            }
        }
        return max;
    }

    private static int longest(int k, List<Integer> woods, int total) {
        int res = 0;
        if (total < k) {
            return res;
        } else if (total < k * 2) {
            return 1;
        } else {
            res = 1;
            while (true) {
                res++;
                int count = 0;
                for (Integer wood : woods) {
                    count += wood / res;
                    if (count >= k) {
                        break;
                    }
                }
                if (count < k) {
                    return res - 1;
                }
            }
        }
    }

    private static boolean check(int[] eff, int k, int target) {
        for (int i = 0; i < k; i++) {
            if (eff[i] == target) {
                return false;
            }
        }
        return true;
    }

    private static int sum(int[] eff, int[] fee, int k, int[][] efficacy) {
        int res = 0;
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                res += efficacy[eff[i]][eff[j]];
                res += efficacy[fee[i]][fee[j]];
            }
        }
        return res;
    }

    private static void swap(int[] nums, int a, int b) {
        int t = nums[b];
        nums[b] = nums[a];
        nums[a] = t;
    }

    private static void reverse(int[] nums, int a, int b) {
        for (int i = a, j = b; i < j; i++, j--)
            swap(nums, i, j);
    }

    private static boolean next(int[] nums) {
        int start = nums.length - 1;
        while (start > 0 && nums[start] < nums[start - 1])
            start--;
        if (start == 0)
            return false;
        int end = nums.length - 1;
        while (nums[end] < nums[start-1])
            end--;
        swap(nums, start - 1, end);
        reverse(nums, start, nums.length - 1);
        return true;
    }

    private static boolean isLegel(int[] nums, int smaller) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                count++;
            }
        }
        return smaller == count;
    }

    private static double findMax(int[][] points, int length) {
        double max = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    if ((points[i][3] == points[j][3] && points[i][3] == points[k][3]) ||
                            points[i][3] + points[j][3] + points[k][3] == 3) {
                        max = Math.max(max, area(points[i], points[j], points[k]));
                    }
                }
            }
        }
        return max;
    }

    private static double area(int[] a, int[] b, int[] c) {
        double ab = Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2) + Math.pow(a[2] - b[2], 2));
        double ac = Math.sqrt(Math.pow(a[0] - c[0], 2) + Math.pow(a[1] - c[1], 2) + Math.pow(a[2] - c[2], 2));
        double bc = Math.sqrt(Math.pow(c[0] - b[0], 2) + Math.pow(c[1] - b[1], 2) + Math.pow(c[2] - b[2], 2));
        if (ab + ac <= bc || ab + bc <= ac || ac + bc <= ab) {
            return 0;
        }
        double p = (ab + ac + bc) / 2;
        return Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
    }

    private static int check(String message, String pattern) {
        boolean[][] match = new boolean[message.length() + 1][pattern.length() + 1];
        match[0][0] = true;
        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < pattern.length(); j++) {
                if (message.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '?') {
                    match[i+1][j+1] = match[i][j];
                }
                if (pattern.charAt(j) == '*') {
                    match[i + 1][j + 1] = match[i][j] || match[i][j + 1] || match[i + 1][j];
                }
            }
        }
        return match[message.length()][pattern.length()] ? 1 : 0;
    }

    @SuppressWarnings("Convert2Lambda")
    private static int max(int[][] russian) {
        Arrays.sort(russian, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });
        int[] length = new int[russian.length];
        int len = 0;
        for (int[] aRussian : russian) {
            int index = Arrays.binarySearch(length, 0, len, aRussian[1]);
            if (index < 0) {
                index = -index - 1;
                length[index] = aRussian[1];
                if (index + 1 > len) {
                    len++;
                }
            }
        }
        return len;
    }

}