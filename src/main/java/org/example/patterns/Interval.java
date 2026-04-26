package org.example.patterns;

import java.util.*;

public class Interval {

    //mergeOverlap : O(n log n) time, O(n) space
    //mergeOverlap2 : O(n) time, O(n) space
    //insertInterval : O(n) time, O(n) space

    /**
     * INTERVAL PATTERN
     */

    /**
     *
     * @param Intervals: arr[][] = [[1, 3], [2, 4], [6, 8], [9, 10]]
     * Output: [[1, 4], [6, 8], [9, 10]]
     * @return
     */
    public List<int[]> mergeOverlap(int[][] Intervals) {
        if (Intervals.length == 0) return null;

        List<int[]> res = new ArrayList<int[]>();
        Arrays.sort(Intervals, (a, b) -> a[0] - b[0]);
        int[] current = Intervals[0];

        for (int i = 1; i < Intervals.length; i++) {
            if (Intervals[i][0] <= current[1]) {
                //overlapping then get max from last index
                current[1] = Math.max(current[1], Intervals[i][1]);
            } else {
                //no overlapping
                res.add(current);
                current = Intervals[i];
            }
        }
        res.add(current);
        return res;
    }
    //linked list solution with space
    public int[][] mergeOverlap2(int[][] Intervals){
        LinkedList<int[]> merged = new LinkedList<>();
        for(int i=0; i<Intervals.length; i++){
            if(merged.isEmpty() || merged.getLast()[1] < Intervals[i][0]){
                merged.add(Intervals[i]);
            }
            else{
                merged.getLast()[1] = Math.max(merged.getLast()[1], Intervals[i][1]);
            }
        }
        int res[][] = new int[merged.size()][2];
        return merged.toArray(res);
    }


    /**
     * Insert Interval
     * [1,3],[6,7] , newinterval : [2,5]
     * out : [1,5],[6,7]
     */

    public int[][] insert(int[][] intervals,int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        for(int[] curr : intervals){

            // Case 1
            if(curr[1] < newInterval[0]){
                res.add(curr);
            }

            // Case 2
            else if(curr[0] > newInterval[1]){
                res.add(newInterval);
                newInterval = curr;
            }

            // Case 3 overlap
            else{
                newInterval[0] = Math.min(newInterval[0], curr[0]);
                newInterval[1] = Math.max(newInterval[1], curr[1]);
            }
        }
        res.add(newInterval);

        return res.toArray(new int[res.size()][2]);
    }

}