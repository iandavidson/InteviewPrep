package software.davidson.ian.backtracking.permutations;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Permutations {

    /*
    iterate over nums, recursively call with list of nums
     */

    public static void main(String [] args){
        Permutations permutations = new Permutations();
        List<List<Integer>> result1 = permutations.permute(new int[]{1,2,3});
    }

    public List<List<Integer>> permute(int[] nums) {
        return recurse(nums, new ArrayList<>());
    }

    public List<List<Integer>> recurse(int[] nums, List<Integer> currentList){
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length == currentList.size()){
            result.add(currentList);
            return result;
        }

        for(int num : nums){ //will always iterate over all possible members, 1, 2, 3
            if(!currentList.contains(num)){ //we will only build more permuatations with ones not in current list.
                List<Integer>  currentCopy = new ArrayList<>(currentList);
                currentCopy.add(num);
                List<List<Integer>> temp = recurse(nums, currentCopy);
                result.addAll(temp);
            }
        }

        return result;
    }
}
