package software.davidson.ian.dp.largestDivisibleSubset;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class LargestDivisibleSubset {
    public static void main(String [] args){
        LargestDivisibleSubset largestDivisibleSubset = new LargestDivisibleSubset();
        log.info("{}", largestDivisibleSubset.largestDivisibleSubset(new int[]{1,2,3}));
    }

    //attempt 1 without DP
//    public List<Integer> largestDivisibleSubset(int[] nums) {
//        Arrays.sort(nums);
//
//        List<List<Integer>> res = new ArrayList<>();
//
//        for(int num : nums){
//            List<Integer> sub = new ArrayList<>();
//            sub.add(num);
//            res.add(sub);
//        }
//
//
//        for(int i = 1 ;i  < nums.length; i++){
//            List<Integer> temp = new ArrayList<>();
//            for(int j = 0; j < i; j++){
//                if(nums[i] % nums[j] == 0 && res.get(i).size() < res.get(j).size()+1){
//                    res.set(i, new ArrayList<>(res.get(j)));
//                    res.get(i).add(nums[i]);
//                }
//            }
//            System.out.println(res.get(i));
//        }
//
//        List<Integer> max = new ArrayList<>();
//        for(List<Integer> subset: res){
//            if(subset.size() > max.size()){
//                max = subset;
//            }
//        }
//
//        return max;
//    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];
        int[] pre = new int[n];

        Arrays.sort(nums);

        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            count[i] = 1;
            pre[i] = -1;

            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (1 + count[j] > count[i]) {
                        count[i] = count[j] + 1;
                        pre[i] = j;
                    }
                }
            }

            if (count[i] > max) {
                max = count[i];
                index = i;
            }
        }

        List<Integer> res = new ArrayList<>();
        while (index != -1) {
            res.add(nums[index]);
            index = pre[index];
        }

        return res;
    }

}
