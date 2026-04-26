package org.example.patterns;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {

    //maxWindowSumForK : O(n) time, O(1) space
    //lengthOfLongestSubstring : O(n) time, O(k) space
    //minWindowSubstring : O(n) time, O(m) space
    //characterReplacement : O(n) time, O(1) space
    //characterReplacement_2 : O(n) time, O(1) space
    //subarraysWithKDistinct : O(n) time, O(k) space
    //totalFruit : O(n) time, O(1) space

    /**
     * Input: s = "abcabcbb"
     * Output: 3 ("abc")
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            if (map.containsKey(currentChar)) {
                left = Math.max(left, map.get(currentChar) + 1);
            }
            map.put(currentChar, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    /**
     * Input: str = "ADOBECODEBANC", target = "ABC"
     * Output: "BANC"
     */
    public String minxWindowSubstring(String str, String target) {
        Map<Character, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> windowMap = new HashMap<>();
        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int have = 0;
        int need = targetMap.size();
        int res[] = {-1, -1};
        for (int right = 0; right < str.length(); right++) {
            char c = str.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

            if (targetMap.containsKey(str.charAt(right)) && windowMap.get(str.charAt(right)).equals(targetMap.get(str.charAt(right))))
                have++;

            while (have == need) {
                int currentWindowSize = (right - left) + 1;
                if (currentWindowSize < minLength) {
                    minLength = currentWindowSize;
                    res[0] = left;
                    res[1] = right;
                }
                Character leftChar = str.charAt(left);
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                if (targetMap.containsKey(leftChar) && windowMap.get(leftChar) < (targetMap.get(leftChar)))
                    have--;
                left++;
            }
        }
        return res[0] == -1 ? "" : str.substring(res[0], res[1] + 1);
    }

    /**
     * Input: nums = [2, 1, 5, 1, 3, 2], k = 3
     * Output: 9 (subarray [5, 1, 3])
     */
    public int maxWindowSumForK(int[] nums, int k) {
        if (nums.length < k) return 0;
        int maxSum = 0;
        int currentWindowSum = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            currentWindowSum += nums[right];
            if (right - left + 1 == k) {
                maxSum = Math.max(maxSum, currentWindowSum);
                currentWindowSum -= nums[left];
                left++;
            }
        }
        return maxSum;
    }

    /**
     * Input: s = "AABABBA", k = 1
     * Output: 4
     * What is the longest substring with same character
     * I can create if I am allowed to change up to k characters to any other character?
     * Replacements Needed = Window Size - Max Frequency
     */
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0;
        int maxLength = 0;
        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'A']++;
            int window = (right - left + 1);
            while (window - getMaxFreq(count) <= k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            maxLength = Math.max(maxLength, window);
        }
        return maxLength;
    }

    private int getMaxFreq(int[] count) {
        int max = 0;
        for (int i : count)
            max = Math.max(max, i);
        return max;
    }

    /**
     * Input: s = "AABABBA", k = 1
     * Output: 4
     */
    public int characterReplacement_2(String s, int k) {
        int[] count = new int[26];
        int left = 0;
        int maxRepeatCount = 0;
        int maxLength = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            count[c - 'A']++;
            maxRepeatCount = Math.max(maxRepeatCount, count[c - 'A']);
            int window = (right - left + 1);
            while (window - maxRepeatCount <= k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            maxLength = Math.max(maxLength, window);
        }
        return maxLength;
    }

    //subarraysWithKDistinct
    //Input: nums = [1,2,1,2,3], k = 2
    //Output: 7
    //Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int left = 0, totalSubarrays = 0;
        for (int right = 0; right < nums.length; right++) {
            // Expand: Add the current number
            countMap.put(nums[right], countMap.getOrDefault(nums[right], 0) + 1);

            // Shrink: If we have more than K distinct integers
            while (countMap.size() > k) {
                int leftNum = nums[left];
                countMap.put(leftNum, countMap.get(leftNum) - 1);
                if (countMap.get(leftNum) == 0) {
                    countMap.remove(leftNum);
                }
                left++;
            }

            // Key Logic: The number of subarrays ending at 'right'
            // that have AT MOST k distinct elements is (right - left + 1)
            totalSubarrays += (right - left + 1);
        }
        return totalSubarrays;
    }


    // fruits in basket, array has different fruits in indexs
    // input: fruits = [1, 2, 3, 2, 2]  output : 4
    //only 2 different fruits can be considered
    // To solve this, you need to track the number of fruits you have picked
    // while respecting the limit of two baskets (meaning only two distinct types of fruit).
    //Goal: Find the longest continuous subarray that contains only two distinct integers.
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0;
        int maxFruits = 0;

        for (int right = 0; right < fruits.length; right++) {
            int currentFruit = fruits[right];
            basket.put(currentFruit, basket.getOrDefault(currentFruit, 0) + 1);

            // 2. If we have more than 2 types of fruit, shrink from the left
            while (basket.size() > 2) {
                int leftFruit = fruits[left];
                basket.put(leftFruit, basket.get(leftFruit) - 1);

                if (basket.get(leftFruit) == 0) {
                    basket.remove(leftFruit);
                }
                left++;
            }

            // 3. Update the maximum number of fruits collected so far
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        return maxFruits;
    }

}
