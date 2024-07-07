package software.davidson.ian.dp;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class LongestIncreasingSequence {
    public static void main(String [] args){
        LongestIncreasingSequence longestIncreasingSequence = new LongestIncreasingSequence();
        log.info("{}", longestIncreasingSequence.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                log.info("i: {}, j:{}", i, j);

                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return Arrays.stream(dp).max().orElse(0);

        // checking [1]; means checking 1
        // checking [2]; means checking 0 and 1

    }
}
