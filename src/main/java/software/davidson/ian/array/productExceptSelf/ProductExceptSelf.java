package software.davidson.ian.array.productExceptSelf;

import java.util.Arrays;

public class ProductExceptSelf {
    public static void main(String [] args){
        ProductExceptSelf productExceptSelf = new ProductExceptSelf();
        System.out.println(Arrays.toString(productExceptSelf.productExceptSelf(new int[]{1, 2, 4, 6})));
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int [] prefix = new int[n];
        int temp =1;
        for(int i = 0; i < n; i++){
            prefix[i] = temp;
            temp *= nums[i];
        }

        int [] postfix = new int[n];
        temp = 1;
        for(int i = n - 1; i > -1; i--){
            postfix[i] = temp;
            temp *= nums[i];
        }

        for(int i = 0; i < n; i++){
            System.out.println("int i: " + i);
            nums[i] = prefix[i] * postfix[i];
        }

        return nums;
    }
}
