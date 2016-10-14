package leetcode.dynamicProgramming;

/**
 * Created by FengSi on 2016/10/14 at 18:29.
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0)
            return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] height = new int[rows+1][cols];
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1')
                    height[i][j] = height[i-1][j] + 1;
            }
        }
        return max(height[0]);
    }

    private int max(int[] h) {
        return 0;
    }
}

/*
85. Maximal Rectangle   QuestionEditorial Solution  My Submissions
Given a 2D binary matrix filled with 0's and 1's, find the largest
rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.
 */