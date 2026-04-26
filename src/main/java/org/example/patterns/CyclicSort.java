package org.example.patterns;

public class CyclicSort {

    //missingNumber (cyclic) : O(n) time, O(1) space
    //missingNumber (math) : O(n) time, O(1) space
    //missingNumber (xor) : O(n) time, O(1) space
    //findDuplicate (cyclic) : O(n) time, O(1) space
    //findDuplicate (Floyd) : O(n) time, O(1) space
    //firstMissingPositive : O(n) time, O(1) space
    /**
     * CYCLIC SORT PATTERN
     */

    //[3,0,1]
    //ans : 2
    public int missingNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correctIndex = nums[i];
            // place number in correct index
            if (nums[i] < nums.length && nums[i] != nums[correctIndex]) {
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            } else {
                i++;
            }
        }
        //after [0,1,3]
        // find missing index
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }

    //By MATH
    // nums = [3,0,1]
    //Expected = 3*4/2 = 6
    //Actual   = 3+0+1 = 4
    //Missing = 2

    public int missingNumber2(int[] nums) {
        int n = nums.length;
        int expected = n * (n + 1) / 2;
        int actual = 0;
        for (int num : nums) {
            actual += num;
        }

        return expected - actual;
    }

    //XOR approach [3,0,1]
    //3^0^3 = 0 (same cancle)
    //0^1^0 = 1
    //1^2^1 = 2 ans
    public int missingNumber3(int[] nums) {
        int xor = nums.length;
        for(int i=0; i<nums.length; i++){
            xor ^= i ^ nums[i];
        }
        return xor;
    }


    /**
     * Find duplicate
     * nums = [1,3,4,2,2]
     */

    public int findDuplicate(int[] nums){
        int i=0;
        while(i < nums.length){
            if(nums[i] != i+1){
                int correct = nums[i]-1;
                if(nums[i] == nums[correct]){
                    return nums[i];
                }

                int temp=nums[i];
                nums[i]=nums[correct];
                nums[correct]=temp;
            } else{
                i++;
            }
        }
        return -1;
    }


    //FLYOD CYCLE DETECTION
    public int findDuplicate2(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        // Phase 1: find meeting point
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);

        // Phase 2: find duplicate
        slow = nums[0];

        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    /**
     * Find FIRST missing
     * inp : [3,2,0,-1] out : 1
     * inp : [7,8,9] out : 1
     */

    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correct = nums[i] - 1;
            if (nums[i] > 0 &&  nums[i] <= nums.length &&  nums[i] != nums[correct]) {
                int temp = nums[i];
                nums[i] = nums[correct];
                nums[correct] = temp;
            }
            else {
                i++;
            }
        }
        //after [0,2,3,-1]
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}

