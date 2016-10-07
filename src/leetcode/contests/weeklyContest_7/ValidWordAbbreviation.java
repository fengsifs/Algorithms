package leetcode.contests.weeklyContest_7;

/**
 * Created by FengSi on 2016/10/07 at 22:07.
 */
public class ValidWordAbbreviation {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        int j = 0;
        int count = 0;
        while (i < word.length() && j < abbr.length()) {
            if (Character.isDigit(abbr.charAt(j))) {
                if (count == 0 && abbr.charAt(j) == '0') return false;
                count = count * 10 + abbr.charAt(j) - '0';
                j++;
            } else {
                if (count != 0) {
                    i += count;
                    count = 0;
                }
                if (i > word.length() || i < word.length() && word.charAt(i) != abbr.charAt(j)) {
                    return false;
                }
                i++;
                j++;
            }
        }
        if (count != 0) {
            i += count;
            count = 0;
        }
        return i == word.length() && j == abbr.length();
    }
}
