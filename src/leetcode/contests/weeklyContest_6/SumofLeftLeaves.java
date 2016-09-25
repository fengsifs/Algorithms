package leetcode.contests.weeklyContest_6;

import leetcode.classes.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by FengSi on 2016/09/25 at 21:35.
 */
public class SumofLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        int res = 0;
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            TreeNode l = n.left;
            if (l != null) {
                if (l.left == null && l.right == null)
                    res += l.val;
                else
                    q.offer(l);
            }
            if (n.right != null)
                q.offer(n.right);
        }
        return res;
    }
}

/*
404. Sum of Left Leaves My SubmissionsBack To Contest
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */