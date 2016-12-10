package leetcode.heap;

import leetcode.classes.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by FengSi on 2016/09/23 at 16:19.
 */
public class MergekSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        // Comparator in jdk1.7
//        Comparator<ListNode> comp = new Comparator<ListNode>() {
//            public int compare(ListNode a, ListNode b) {
//                return a.val - b.val;
//            }
//        };
        Comparator<ListNode> comp = (a, b) -> a.val - b.val;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(comp);
        for (ListNode n : lists)
            if (n != null)
                pq.offer(n);
        ListNode head = new ListNode(0);
        ListNode pos = head;
        ListNode tail = null;
        while (!pq.isEmpty()) {
            tail = pq.poll();
            pos.next = tail;
            pos = tail;
            if (tail.next != null)
                pq.offer(tail.next);
        }
        return head.next;
    }
}

/*
23. Merge k Sorted Lists  QuestionEditorial Solution  My Submissions
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Subscribe to see which companies asked this question
 */