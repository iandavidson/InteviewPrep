package software.davidson.ian.greedy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class MaximizeTopElementAfterK {
    public static void main(String [] args){
        MaximizeTopElementAfterK maximizeTopElementAfterK = new MaximizeTopElementAfterK();
        log.info("answer, {}", maximizeTopElementAfterK.maximumTop(new int[]{99,95,68,24,18}, 69));
    }

    public int maximumTop(int[] nums, int k) {

        if (nums.length == 1) {
            if (k % 2 == 1) {
                return -1;
            } else {
                return nums[0];
            }
        }

        int maxDiscovered = nums[0];
        int n = nums.length;

        if (k < n) {
            maxDiscovered = nums[k];
        } else if (k > n) {
            k = n + 1;
        }


        for (int i = 0; i < k - 1 && i < nums.length; i++) {
            maxDiscovered = Math.max(maxDiscovered, nums[i]);
        }

        return maxDiscovered;
    }
}
