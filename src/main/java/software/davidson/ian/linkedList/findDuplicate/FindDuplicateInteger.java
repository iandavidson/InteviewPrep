package software.davidson.ian.linkedList.findDuplicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindDuplicateInteger {

    public static void main(String [] args){
        FindDuplicateInteger findDuplicateInteger = new FindDuplicateInteger();
        log.info("{}", findDuplicateInteger.findDuplicate(new int[]{3,1,3,4,2}));
    }

    public int findDuplicate(int[] nums) {
        int fast = 0;
        int slow = 0;
        while(true){
            fast = nums[fast];
            fast = nums[fast];
            slow = nums[slow];
            if(fast == slow){
                break;
            }
        }

        int newSlow = 0;
        while(true){
            slow = nums[slow];
            newSlow = nums[newSlow];
            if(slow == newSlow){
                return slow;
            }
        }

    }
}
