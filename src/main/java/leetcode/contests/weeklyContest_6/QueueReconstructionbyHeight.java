package leetcode.contests.weeklyContest_6;

import java.util.*;

/**
 * Created by FengSi on 2016/09/25 at 21:49.
 */
public class QueueReconstructionbyHeight {
    public int[][] reconstructQueue(int[][] people) {
        int[][] result = new int[people.length][];
        List<int[]> list = new ArrayList<>();
        /*use a pq to sort all the people based on h*/
        PriorityQueue<int[]> queue = new PriorityQueue<>(new listComparator());
        for (int[] p : people) queue.offer(p);
        /*insertion sort based on k*/
        while(queue.size() != 0){
            insert(list, queue.poll());
        }
        for(int i = 0; i < people.length; i++){
            result[i] = list.get(i);
        }
        return result;
    }

    /*insertion sort based on k*/
    private void insert(List<int[]> list, int[] element){
        if(list.size() == 0) {
            list.add(element);
            return;
        }
        int count = 0;
        for(int i = 0; i < list.size(); i++){
            if(count == element[1]){
                list.add(i, element);
                return;
            }else if(list.get(i)[0] >= element[0]){
                count++;
            }
        }
        list.add(element);
    }

    /*pq comparator class*/
    class listComparator implements Comparator<int[]> {
        public int compare(int[] first, int[] second){
            if(first[0] != second[0])  return second[0] - first[0];
            else return first[1] - second[1];
        }
    }
}

/*
406. Queue Reconstruction by Height  QuestionEditorial Solution  My Submissions
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */