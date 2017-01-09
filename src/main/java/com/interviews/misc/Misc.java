package com.interviews.misc;

import java.util.*;

public class Misc {

    /*
    The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi],
    where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively,
    and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0.
    You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at
    height 0.
    */

    public List<int[]> getSkyline(int[][] buildings) {

        List<int[]> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();

        for (int[] b : buildings) {
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }

        Collections.sort(height, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int prev = 0;
        for (int[] h : height) {
            if (h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                pq.remove(h[1]);
            }
            int cur = pq.peek();
            if (prev != cur) {
                result.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return result;
    }

}
