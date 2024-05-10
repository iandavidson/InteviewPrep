package software.davidson.ian.binarySearch.matrix;

public class Solution {
    public static void main(String [] args){
        Solution solution = new Solution();
        int[][] matrix = new int[][]{ {1,3,5,7}, {10,11,16,20}, {23,30,34,60}};
//        System.out.println(solution.searchMatrix(matrix, 3));
//        System.out.println(solution.searchMatrix(matrix, 13));


        /*
        {1,3,5,7},
        {10,11,16,20},
        {23,30,34,60}
         */
//
        matrix = new int[][]{{1,1}};
        System.out.println(solution.searchMatrix(matrix, 0));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        // Treat it like one long array
        int m = matrix.length;
        int n = matrix[0].length;

        int low = 0;
        int high = n * m;

        while(low < high){

            int middle = (high + low)/2;
            System.out.println("Low: " + low + " middle:  " + middle + "; high: " + high);

            if(matrix[middle / n][middle % n] == target){
                return true;
            } else if(matrix[middle / n][middle % n] < target){
                low = middle +1;
            } else {
                high = middle;
            }
        }

        return false;
    }
}
