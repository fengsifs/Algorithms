package leetcode;

/**
 * Created by FengSi on 2016/09/04 at 23:59.
 */
public class DecodeString {
    public String decodeString(String s) {
        if (s == null || s.length() == 0)
            return s;
        char[] cs = s.toCharArray();
        return decode(cs, 0, cs.length-1);
    }

    private String decode(char[] cs, int start, int end) {
        String res = "";
        if (cs[start] > '0' && cs[start] <= '9') {
            int k = start;
            int rep = 0;
            while (cs[k] != '[')
                rep = rep * 10 + (cs[k++] - '0');
            int count = 1;
            k++;
            int instart = k;
            while (k <= end) {
                if (cs[k] == '[')
                    count++;
                else if (cs[k] == ']')
                    count--;
                if (count == 0)
                    break;
                k++;
            }
            String s = decode(cs, instart, k - 1);
            while (rep-- > 0)
                res += s;
            if (k < end)
                res += decode(cs, k+1, end);
        }
        else {
            while (start <= end && cs[start] >= 'a' && cs[start] <= 'z') {
                res += cs[start];
                start++;
            }
            if (start < end)
                res += decode(cs, start, end);
        }
        return res;
    }
}

/*
 394. Decode String
 Given an encoded string, return it's decoded string.

 The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is
 being repeated exactly k times. Note that k is guaranteed to be a positive integer.

 You may assume that the input string is always valid; No extra white spaces, square brackets are
 well-formed, etc.

 Furthermore, you may assume that the original data does not contain any digits and that digits
 are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

 Examples:

 s = "3[a]2[bc]", return "aaabcbc".
 s = "3[a2[c]]", return "accaccacc".
 s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */