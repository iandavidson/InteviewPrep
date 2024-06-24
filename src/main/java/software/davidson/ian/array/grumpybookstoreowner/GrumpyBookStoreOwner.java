package software.davidson.ian.array.grumpybookstoreowner;

public class GrumpyBookStoreOwner {

    public static void main(String[] args) {
        GrumpyBookStoreOwner grumpyBookStoreOwner = new GrumpyBookStoreOwner();
        System.out.println(grumpyBookStoreOwner.maxSatisfied(
                new int[]{1, 0, 1, 2, 1, 1, 7, 5},
                new int[]{0, 1, 0, 1, 0, 1, 0, 1},
                3
        ));

        System.out.println(grumpyBookStoreOwner.maxSatisfied(
                new int[]{10,1,7},
                new int[]{0,0,0},
                2
        ));
    }

//      iteration 1: single loop, recompute the grumpy adjustment range sum each time;
//    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
//        int n = customers.length;
//        int defaultHappy = 0;
//        int max = 0;
//
//        for(int i = 0; i < n; i++){
//            defaultHappy += grumpy[i] == 0 ? customers[i] : 0;
//
//            int temp = 0;
//            for(int j = 0; j < minutes && j + i < n ; j++){
//                temp += grumpy[i+j] == 1 ? customers[i+j] : 0;
//            }
//
//            max = Math.max(max, temp);
//        }
//
//        return max + defaultHappy;
//    }
//iteration 2
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int defaultHappy = 0;

        for(int i = 0 ; i < n; i++){
            defaultHappy += customers[i] * grumpy[i];
        }

        int max = 0;
        int right = 0;
        int rangeSum = 0;

        while(right < n){
            rangeSum += grumpy[right] == 0 ? customers[right] : 0;

            if(right - minutes >= 0 && grumpy[right - minutes] == 0){
                rangeSum -= customers[right - minutes];
            }

            max = Math.max(rangeSum, max);
            right++;
        }

        return max + defaultHappy;
    }

}
