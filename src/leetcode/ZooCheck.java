package leetcode;

import leetcode.backTracking.GenerateParentheses;

import java.util.PriorityQueue;

/**
 * Created by FengSi on 2016/08/28 at 9:22.
 */
public class ZooCheck {
    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        generateParentheses.generateParenthesis(4).forEach(System.out::println);
    }
}
