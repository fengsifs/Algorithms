package leetcode.tree;

import leetcode.classes.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by FengSi on 2017/02/05 at 17:19.
 */
public class MostFrequentSubtreeSum {
    public int[] findFrequentTreeSum(TreeNode root) {
        int[] max = new int[1];
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Set<Integer>> nums = new HashMap<>();
        s(root, max, map, nums);
        Set<Integer> list = nums.get(max[0]);
        int[] res = new int[list.size()];
        int i = 0;
        for (int k : list)
            res[i++] = k;
        return res;
    }

    private int s(TreeNode node, int[] max, Map<Integer, Integer> map, Map<Integer, Set<Integer>> nums) {
        int l = 0, r = 0;
        if (node.left != null)
            l = s(node.left, max, map, nums);
        if (node.right != null)
            r = s(node.right, max, map, nums);
        int sum = l + r + node.val;
        int ap = map.containsKey(sum) ? (map.get(sum) + 1) : 1;
        map.put(sum, ap);
        if (ap > max[0])
            max[0] = ap;
        Set<Integer> list = nums.containsKey(ap) ? nums.get(ap) : new HashSet<>();
        list.add(sum);
        nums.put(ap, list);
        return sum;
    }
}
