package org.example.patterns;

public class Kodane {

    //maxSubArray // O(n), O(1)
    //maxSubarraySumCircular // O(n), O(1)
    //maxProduct // O(n), O(1)

    /**
     * Kodane's pattern
     * Input: [-2,1,-3,4,-1,2,1,-5,4]
     * Output: 6 -> [4,-1,2,1]
     **/

    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    /**
     * Kadane’s Algorithm (twice) ✅
     * in: [5, -3, 5]
     * Possible circular subarray:
     * 5 (end) + 5 (start) = 10
     * Normal Kadane would only give 7.
     * <p>
     * circularMax = totalSum−minSubarraySum
     * <p>
     * Why?
     * <p>
     * Removing the worst middle chunk leaves the best wrapped chunk.
     **/
    public int maxSubarraySumCircular(int[] nums) {
        int total = 0;
        int currentMax = nums[0];
        int maxSum = nums[0];
        int currentMin = nums[0];
        int minSum = nums[0];

        for (int i = 0; i < nums.length; i++) {
            total += nums[i];

            if (i > 0) {
                currentMax = Math.max(nums[i], currentMax + nums[i]);
                maxSum = Math.max(maxSum, currentMax);

                currentMin = Math.min(nums[i], currentMin + nums[i]);
                minSum = Math.min(minSum, currentMin);
            }
        }

        // all negative case
        if (maxSum < 0) {
            return maxSum;
        }

        return Math.max(maxSum, total - minSum);
    }


    /**
     * Kadane’s Algorithm hard
     * input : [-2,3,-4] output: 24
     * At each index, track BOTH:
     * 1)Maximum product ending here
     * 2)Minimum product ending here
     * Why minimum too?
     * Because a very negative product can become maximum when multiplied by another negative.
     **/
    public int maxProduct(int[] nums) {

        int maxProd = nums[0];
        int minProd = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num < 0) {
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }
            maxProd = Math.max(num, maxProd * num);
            minProd = Math.min(num, minProd * num);

            result = Math.max(result, maxProd);
        }
        return result;
    }
}
