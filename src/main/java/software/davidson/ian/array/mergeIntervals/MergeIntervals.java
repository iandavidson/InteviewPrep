package software.davidson.ian.array.mergeIntervals;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class MergeIntervals {

    public static void main(String[] args){
        MergeIntervals mergeIntervals = new MergeIntervals();
        int [][] input = new int[][]{ new int[]{1,3}, new int[]{2,6}, new int[]{8,10}, new int[]{15,18}};

                //[[1,3],[2,6],[8,10],[15,18]]
        log.info("answer: {}", (Object) mergeIntervals.merge(input));
    }


    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        if (intervals.length == 1) {
            return intervals;
        }

        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            int leftVal = intervals[i][0];
            int rightVal = intervals[i][1];
            while(i < intervals.length -1 && intervals[i+1][0] <= rightVal){
                i++;
                rightVal = Math.max(rightVal, intervals[i][1]);
            }

            result.add(new int[]{leftVal, rightVal});
        }

        int[][] realResult = new int[result.size()][2];

        for (int i = 0; i < result.size(); i++) {
            realResult[i] = result.get(i);
        }

        return realResult;
    }
}
