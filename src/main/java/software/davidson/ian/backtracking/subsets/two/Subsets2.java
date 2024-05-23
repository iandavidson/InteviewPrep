package software.davidson.ian.backtracking.subsets.two;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Subsets2 {

    public static void main(String[] args) {
        Subsets2 subsets2 = new Subsets2();
        List<List<Integer>> answer = subsets2.subsetsWithDup(new int[]{1, 2});
        log.info("answer: {}", answer);
        answer = subsets2.subsetsWithDup(new int[]{1, 2, 1});
        log.info("answer: {}", answer);
        answer = subsets2.subsetsWithDup(new int[]{1, 2, 1, 3});
        log.info("answer: {}", answer);
        int i = 0;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        recurse(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void recurse(int[] nums, int index, List<Integer> list, List<List<Integer>> res) {

        if (index >= nums.length) {
            res.add(list);
            return;
        }

        //1
        List<Integer> first = new ArrayList<>(list);
        first.add(nums[index]);
        recurse(nums, index + 1, first, res);

        //2 by pass all instances of element at index
        List<Integer> second = new ArrayList<>(list);;
        int i = index;
        while (i < nums.length && nums[i] == nums[index]) {
            i++;
        }

        recurse(nums, i, second, res);
    }
}
