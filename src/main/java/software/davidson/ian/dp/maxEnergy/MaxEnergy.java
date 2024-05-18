package software.davidson.ian.dp.maxEnergy;

import java.util.Arrays;

public class MaxEnergy {

    public static void main(String [] args){
        MaxEnergy maxEnergy = new MaxEnergy();
        maxEnergy.maximumEnergy(new int[]{5,2,-10,-5,1}, 3);
    }


    public int maximumEnergy(int[] energy, int k) {
        int maxSoFar = Integer.MIN_VALUE;
        int[] dp = new int[energy.length];
        Arrays.fill(dp, Integer.MIN_VALUE);

        for (int i = 0; i < energy.length; i++) {
            int currentMax = energy[i];
            int j = i;
            if (i - k > 0) {
                currentMax = dp[i - k] - energy[i - k];
            } else {
                while (j + k < energy.length) {
                    currentMax += energy[j + k];
                    j += k;
                }
            }
            dp[i] = Math.max(dp[i], currentMax);
            maxSoFar = Math.max(dp[i], maxSoFar);
        }

        return maxSoFar;
    }
}
