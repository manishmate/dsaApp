package org.example.patterns;

import java.util.HashMap;
import java.util.Map;

public class PrefixSum {

    //subarraySumK : O(n) time, O(n) space
    //subarraySumZERO : O(n) time, O(n) space
    //maxSubArrayLen : O(n) time, O(n) space
    //checkSubarraySum : O(n) time, O(n) space
    //numberOfSubarrays : O(n) time, O(n) space

    //Summary Rule :
    // If the map stores where (Index) -> -1
    // If the map stores how many (Frequency) -> Use 1.

    //SUBSARRAY SUM K
    public int subarraySumK(int[] nums, int k) {
        int count = 0, currentSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // Base case: a sum of 0 has happened once
        for (int num : nums) {
            currentSum += num;
            // If currentSum is 10 and K is 7, we check if we ever saw a 3.
            if (map.containsKey(currentSum - k)) {
                count += map.get(currentSum - k);
            }
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }
        return count;
    }

    //SUB array sum = 0
    public int subarraySumZERO(int[] nums, int k) {
        int count = 0, currentSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // Base case: a sum of 0 has happened once
        for (int num : nums) {
            currentSum += num;
            // If currentSum is 8 and we saw an 8 before, the middle was a "Net Zero".
            if (map.containsKey(currentSum)) {
                count += map.get(currentSum);
            }
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }
        return count;
    }

    //nums = [1, -1, 5, -2, 3], k = 3
    //out :  [1, -1, 5, -2]
    //max sub array SIZE equals K
    public int maxSubArrayLen(int[] nums, int k) {
        int maxLen = 0;
        int currentSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); //why -1 : if k is found at 0 index then  0 - (-1) = 1

        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if (map.containsKey(currentSum - k)) {
                maxLen = Math.max(maxLen, i - map.get(currentSum - k));
            }
            // This preserves the earliest possible index for the longest length
            if (!map.containsKey(currentSum)) {
                map.put(currentSum, i);
            }
        }

        return maxLen;
    }

    //contiguous  subarray sum
    //An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
    //inputs  : 23,2,4,5,6 , K=6
    //output : true , [2,4] = 6%6 = 0
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // Base Case: Remainder 0 is seen at index -1
        map.put(0, -1);
        int runningSum = 0;

        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            // Handle k = 0 if necessary, but usually k > 0
            int remainder = runningSum % k;

            // Handle negative remainders in Java (if array had negatives)
            if (remainder < 0)
                remainder += k;

            if (map.containsKey(remainder)) {
                // Check if the length is at least 2
                if (i - map.get(remainder) >= 2) {
                    return true;
                }
            } else {
                // Only store the first occurrence to keep the subarray as long as possible
                map.put(remainder, i);
            }
        }
        return false;
    }

    //Count Number of Nice Subarrays
    //Nice subarray means subarray with odd number
    //Input: nums = [1,1,2,1,1], k = 3
    //Output: 2
    //Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
    //*************** SIMILAR to SUB ARRAY SUM K *******************
    public int numberOfSubarrays(int[] nums, int k) {
        int count = 0;
        int currentOddCount = 0;
        Map<Integer, Integer> map = new HashMap<>();
        // Base case: 0 odd numbers seen once before starting
        map.put(0, 1);
        for (int num : nums) {
            // 1. If the number is odd, increment our "running sum"
            if (num % 2 != 0) {
                currentOddCount++;
            }
            // If we have 5 odds now and need 3, we check if we ever had 2.
            if (map.containsKey(currentOddCount - k)) {
                count += map.get(currentOddCount - k);
            }
            map.put(currentOddCount, map.getOrDefault(currentOddCount, 0) + 1);
        }
        return count;
    }
}
