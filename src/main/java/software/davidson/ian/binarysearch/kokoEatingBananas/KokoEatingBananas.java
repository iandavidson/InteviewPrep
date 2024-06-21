package software.davidson.ian.binarysearch.kokoEatingBananas;

public class KokoEatingBananas {
    public static void main(String [] args){
        KokoEatingBananas kokoEatingBananas = new KokoEatingBananas();

//        System.out.println(kokoEatingBananas.minEatingSpeed(new int []{30,11,23,4,20}, 5));
        System.out.println(kokoEatingBananas.minEatingSpeed(new int []{805306368,805306368,805306368}, 1000000000));
    }

    /*
//    //brute force iteration 1
    public int minEatingSpeed(int[] piles, int h) {
        int highestPile = 0;
        for(int pile : piles){
            highestPile = Math.max(highestPile, pile);
        }

        for(int k = 1; k < highestPile+1; k++){
            if(satisfiedWithK(piles, h, k)){
                return k;
            }
        }

        return -1;
    }

    //binary search impl iteration 2
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        for(int pile : piles){
            right = Math.max(right, pile);
        }

        int eatRate = 0;

        while(left <= right){
            int mid = (right + left) / 2;

            if(satisfiedWithK(piles, h, mid)){
                eatRate = mid;
                right = mid -1;
            }else{
                left = mid +1;
            }
        }

        return eatRate;
    }
    private boolean satisfiedWithK(int [] piles, int hours, int k){
        int pileIndex = 0;
        int hoursTaken = 0;
        while(pileIndex < piles.length){
            for(int i = 0 ; i < piles[pileIndex]; i += k){
                hoursTaken++;
            }

            pileIndex++;

            if(hoursTaken > hours){
                return false;
            }
        }

        return true;

    }
    */

    //optimized binary search iteration 3; swap simulation helper method for inline math calculation
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        for(int pile : piles){
            right = Math.max(right, pile);
        }

        int eatRate = right;

        while(left <= right){
            int mid = (right + left) / 2;

            long hoursTaken = 0;
            for(int pile : piles){
                hoursTaken += Math.ceil((double) pile / mid);
            }

            if(hoursTaken <= h){
                eatRate = mid;
                right = mid -1;
            }else{
                left = mid +1;
            }
        }

        return eatRate;
    }

}
