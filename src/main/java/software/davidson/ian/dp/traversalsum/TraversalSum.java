package software.davidson.ian.dp.traversalsum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TraversalSum {

    /*
    Given 2 integer (values from 0 - 20000) arrays of equal length (length is 1 - 100000).
    What's the minimum sum traversing each element of the array with the cost of switching between the first array to the second has a fixed cost X and switching from the second array to the first array costs Y.
    The sum includes the element value in the array plus the cost of switching (if that step involved switching).


     2 decisions:
        1. take the cost of current array[i]
        2. take the cost of other array[i] + switch cost

     Sum is built from choosing 1 on each
     */

    public static void main(String [] args){
        TraversalSum traversalSum = new TraversalSum();
//        int[] a = {1, 3, 5, 8};
//        int[] b = {2, 4, 6, 7};
//        int x = 10;
//        int y = 20;

        int[] a = {1, 10, 1};
        int[] b = {10, 1, 10};
        int x = 5;
        int y = 1;

        log.info("{}", traversalSum.traversalSum(a,b,x,y));
    }


    public Integer traversalSum(int [] a, int [] b, int x, int y){
        int n = a.length;
        int [] dpA = new int[n];
        int [] dpB = new int[n];

        dpA[0] = a[0];
        dpB[0] = b[0];

        for(int i = 1; i < n; i++){
            //cost of switching from b -> a: y
            dpA[i] = Math.min(a[i] + dpA[i-1], dpB[i-1] + y + a[i]);

            //cost of switching from a -> b: x
            dpB[i] = Math.min(b[i] + dpB[i-1], dpA[i-1] + x + b[i]);
        }

        return Math.min(dpA[n-1], dpB[n-1]);
    }


}
