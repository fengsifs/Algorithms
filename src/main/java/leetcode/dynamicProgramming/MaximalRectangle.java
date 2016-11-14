package leetcode.dynamicProgramming;

import java.util.Arrays;

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
                if (matrix[i-1][j] == '1')
                    height[i][j] = height[i-1][j] + 1;
            }
        }
        int max = 0;
        for (int[] h : height)
            max = Math.max(max, max(h));
        return max;
    }

    private int max(int[] h) {
        int n = h.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 0;
        right[n-1] = n -1;
        for (int i = 1, j = n-2; i < n; i++, j--) {
            int currentLeft = i - 1;
            while (currentLeft >= 0 && h[currentLeft] >= h[i])
                currentLeft = left[currentLeft] - 1;
            left[i] = currentLeft + 1;
            int currentRight = j + 1;
            while (currentRight < n && h[currentRight] >= h[j])
                currentRight = right[currentRight] + 1;
            right[j] = currentRight - 1;
        }
        int max = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, (right[i] - left[i] + 1) * h[i]);
        return max;
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