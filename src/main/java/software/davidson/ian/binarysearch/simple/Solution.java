package software.davidson.ian.binarysearch.simple;

class Solution {

    public static void main(String [] args){
        Solution solution = new Solution();
        System.out.println(solution.search(new int[]{-1,0,3,5,9,12}, 9));

        System.out.println(solution.search(new int[]{-1,0,3,5,9,12}, 2));
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;
        int middle = (right - left) / 2;

        if(target < nums[left] || target > nums[right]){
            return -1;
        }

        while(left < right){
            System.out.println("left: " + left + "; middle: " + middle + "; right: " + right);
            if(target == nums[middle]){
                return middle;

            }else if(target < nums[middle]){
                right = middle - 1;
                middle = (right + left)/2;
            }else{
                //target > nums[middle]:


                //we want left to now be middle : 2
                //we want right to remain : 5
                // we want middle to be
                left = middle +1;
                middle = (right + left)/2;
            }
        }

        return -1;
    }
}
