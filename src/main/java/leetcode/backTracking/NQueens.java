package leetcode.backTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FengSi on 2016/10/14 at 15:51.
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = '.';
        dfs(res, board, 0);
        return res;
    }

    private void dfs(List<List<String>> res, char[][] board, int row) {
        if (row == board.length) {
            res.add(construct(board));
            return;
        }
        for (int i = 0; i < board.length; i++) {
            if (validate(board, row, i)) {
                board[row][i] = 'Q';
                dfs(res, board, row+1);
                board[row][i] = '.';
            }
        }
    }

    private boolean validate(char[][] board, int i, int j) {
        for (int row = 0; row < i; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == 'Q' && (i - row == j - col || col == j || col - j == i - row))
                    return false;
            }
        }
        return true;
    }

    private List<String> construct(char[][] board) {
        List<String> l = new ArrayList<>();
        for (char[] cs : board)
            l.add(new String(cs));
        return l;
    }
}

/*
51. N-Queens   QuestionEditorial Solution  My Submissions
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
 */