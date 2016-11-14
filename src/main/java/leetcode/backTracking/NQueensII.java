package leetcode.backTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FengSi on 2016/10/14 at 15:30.
 */
public class NQueensII {
    public int totalNQueens(int n) {
        boolean[][] b = new boolean[n][n];
        List<Integer> l = new ArrayList<>();
        dfs(l, 0, n, b);
        return l.size();
    }

    private void dfs(List<Integer> l, int row, int n, boolean[][] b) {
        if (row == n) {
            l.add(0);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (validate(b, row, i)) {
                b[row][i] = true;
                dfs(l, row+1, n, b);
                b[row][i] = false;
            }
        }
    }

    private boolean validate(boolean[][] b, int x, int y) {
        for (int i = 0; i < x; i++)
            for (int j = 0; j < b.length; j++)
                if (b[i][j] && (x - i == y - j || j - y == x - i || y == j))
                    return false;
        return true;
    }
}

/*
52. N-Queens II   QuestionEditorial Solution  My Submissions
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
 */