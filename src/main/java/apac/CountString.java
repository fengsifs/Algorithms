package apac;

/**
 * Created by FengSi on 2016/08/25 at 18:57.
 */
public class CountString {
    public static int count(String s) {
        int[] nums = new int[26];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                if (nums[s.charAt(i) - 'A'] == 0) {
                    res++;
                    nums[s.charAt(i) - 'A'] = 1;
                }
            }
        }
        return res;
    }
}
