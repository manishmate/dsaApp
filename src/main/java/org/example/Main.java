package org.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        removeDuplicates(new int[]{0, 0, 1, 1, 3});

    }

    /**
     * Input: nums = [2, 7, 11, 15], target = 9
     * Output: [0, 1]
     */
    public int[] twoSumUnSorted(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    /**
     * Input: arr = [-4, -1, 1, 4], target = 0 (target is unused in logic, finds pairs sum to 0)
     * Output: [[-4, 4], [-1, 1]]
     */
    public ArrayList<ArrayList<Integer>> twoSumPair(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == 0) {
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(arr[left]);
                pair.add(arr[right]);
                res.add(pair);

                left++;
                right--;

                while (left < right && arr[left] == arr[left - 1]) left++; //--- duplicate
                while (left < right && arr[right] == arr[right + 1]) right--;
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        return res;
    }

    /**
     * Input: nums = [-1, 0, 1, 2, -1, -4]
     * Output: [[-1, -1, 2], [-1, 0, 1]]
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    /**
     * Input: nums = [1, 0, -1, 0, -2, 2], target = 0
     * Output: [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) return result;
        Arrays.sort(nums);

        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Input: nums = [0, 0, 1, 1, 1, 2, 2, 3]
     * Output: 4 (nums becomes [0, 1, 2, 3, ...])
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * Input: nums = [0, 1, 0, 3, 12]
     * Output: nums = [1, 3, 12, 0, 0]
     */
    public void moveZeroes(int[] nums) {
        int lastNonZeroFoundAt = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[lastNonZeroFoundAt];
                nums[lastNonZeroFoundAt] = temp;
                lastNonZeroFoundAt++;
            }
        }
    }

    /**
     * Input: height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
     * Output: 49
     */
    public int maxWaterInContainer(int height[]) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            int width = right - left;
            int currentMinHeight = Math.min(height[right], height[left]);
            max = Math.max(max, width * currentMinHeight);
            if (height[left] < height[right]) left++;
            else right--;
        }
        return max;
    }

    /**
     * Input: arr = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
     * Output: 6
     */
    static int maxRainWater(int arr[]) {
        int sum = 0;
        int n = arr.length;
        int left[] = new int[n];
        int right[] = new int[n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            left[i] = max;
            max = Math.max(max, arr[i]);
        }
        max = 0;
        for (int i = n - 1; i >= 0; i--) {
            right[i] = max;
            max = Math.max(max, arr[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            int min = Math.min(left[i], right[i]);
            if (min > arr[i])
                sum += min - arr[i];
        }
        return sum;
    }

    /**
     * Input: arr = [3, 0, 1, 0, 4, 0, 2]
     * Output: 10
     */
    static int maxRainWater2(int arr[]) {
        int sum = 0;
        int n = arr.length;
        int left = 0;
        int right = n - 1;
        int mi = 0, ma = 0;
        while (left <= right) {
            if (arr[left] < arr[right]) {
                if (arr[left] > mi) mi = arr[left];
                else sum += mi - arr[left];
                left++;
            } else {
                if (arr[right] > ma) ma = arr[right];
                else sum += ma - arr[right];
                right--;
            }
        }
        return sum;
    }


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


    //PREFIX SUM

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


    //HASHING AND ARRAY

    /**
     * input [100,2,7,4,5,44,6]
     * output 4 -> 4,5,6,7
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longestStreak = 0;
        for (int num : set) {
            // Check if this number is the start of a sequence
            // (i.e., the number before it is not in the set)
            if (!set.contains(num - 1)) {   //IMP to avoid extra while iteration for element, it will iterate only for start element
                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }

    //Top K repeated element
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int n : nums) {
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        }

        // 2. Build Min-Heap: O(N log k)
        // We compare elements based on their frequency (the values in the map)
        PriorityQueue<Integer> heap = new PriorityQueue<>(
                (a, b) -> countMap.get(a) - countMap.get(b)
        );

        for (int n : countMap.keySet()) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        // 3. Extract results: O(k)
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap.poll();
        }

        return result;
    }

    //MAJORITY ELEMENT length/2
    public int majority(int nums[]) {
        //Buyer moores voting algo
        int count = 0;
        int candidate = 0;
        //find candidate
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        //validate if candidate is more than length/2
        count = 0;
        for (int num : nums) {
            if (candidate == num)
                count++;
        }

        return count > nums.length / 2 ? candidate : -1;
    }

    //GROUP ANANGRAM
    public List<List<String>> grpAnagram(String str[]) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String word : str) {
            int freq[] = new int[26];
            for (char c : word.toCharArray()) {
                freq[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int f : freq) {
                sb.append(f);
                sb.append("#");
            }

            String key = sb.toString();
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(word);
        }

        return new ArrayList<>(map.values());
    }

    //Valid Anagram
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        for (int count : freq) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }


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


    /**
     * BINARY SEARCH PATTERN 1 2 3 4 5
     */

    public int binarySearch(int arr[], int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target)
                return mid;

            else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Find first and last pos of element
     */
    public int[] searchRange(int[] nums, int target) {
        return new int[]{
                findFirst(nums, target),
                findLast(nums, target)
        };
    }

    private int findFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                ans = mid;
                right = mid - 1; // keep going left
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private int findLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                ans = mid;
                left = mid + 1; // keep going right
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    //[4, 5, 6, 7, 0, 1, 2]
    public int roatatedArraySearch(int arr[], int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target)
                return mid;

            // Identify which side is sorted
            if (arr[left] <= arr[mid]) {
                // Left half is sorted
                if (target >= arr[left] && target < arr[mid]) {
                    //----- = not used for arr[mid] bcos above if already checked for ==
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                if (target > arr[mid] && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    //[3,4,5,6,1,2,]
    public int findMinimumInRotatedArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] > arr[right]) {
                // The minimum is in the right half
                left = mid + 1;
            } else {
                // The minimum is in the left half, including mid
                right = mid;
            }
        }
        // When left == right, we have found the minimum
        return arr[left];
    }

    //[1,2,3,4,4,5]
    public int FirstInArray(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return arr[left];
    }


    /**
     * KOKO eat banana
     * Array Not sorted
     * piles = [3,3,5,2,11]  --> each index pile of banana
     * h = 8
     * output : 4 --> at a time lowest 4 (K) banana should be eaten to eat all from index in 8 hour
     * basically frame K banana range [1,2,3,4,5,6,7,8,9,10,11] apply search over it and find min K to eat all in 8h
     */

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;

        for (int pile : piles) {
            right = Math.max(right, pile); //Array not sorted so find the highest pile
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            long hours = 0;

            for (int pile : piles) {
                hours += (pile + mid - 1) / mid; // ceiling division 4pile/3k becomes 2h by this logic
            }

            if (hours <= h) {
                right = mid;      // try smaller speed
            } else {
                left = mid + 1;   // need faster speed
            }
        }
        return left;
    }


    /**
     * MONOTONI STACK
     */

    /**
     * @param nums [2,1,2,4,3]
     * @return [4, 2, 4,-1,-1]
     */
    public static int[] nextGreater(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() &&
                    stack.peek() <= nums[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }
            stack.push(nums[i]);
        }

        return result;
    }

    /**
     * Input:
     * [73,74,75,71,69,72,76,73] --> daily temperature in index, need to find after how many day next hotter day
     * Output:
     * [1,1,4,2,1,1,0,0]
     */
    public static int[] dailyTemperatures(int[] temps) {
        int n = temps.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        // stores indices

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() &&
                    temps[i] > temps[stack.peek()]) {

                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return result;
    }


    /**
     * @param heights = {2,1,5,6,2,3}; width is 1 for each index height
     * @return 5*2 = 10 (5,6)
     */
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= heights.length; i++) {  // no  heights.length-1
            // intentionally i=0 at last to empty the stack via while loop
            int currentHeight = (i == heights.length) ? 0 : heights[i];

            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int width;
                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }

                maxArea = Math.max(maxArea, h * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    /**
     * Rain water trapped
     * can be done by PREFIX SUM, TWO POINTER, MONOTONIC STACK
     */

    //PREFIX SUM
    //Height:    3 0 2 0 4 2
    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        //LeftMax:   3 3 3 3 4 4

        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        //rightMax: 4 4 4 4 4 4 2
        int water = 0;

        for (int i = 0; i < n; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return water;
    }

    //TWO POINTER
    //[3,0,2,0,4]
    // ans : 7

    public int rainWater(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        return water;
    }

    //STACK
    public int trapBySTack(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int water = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int bottom = stack.pop();
                if (stack.isEmpty())
                    break;

                int left = stack.peek();
                int width = i - left - 1;
                int boundedHeight = Math.min(height[left], height[i]) - height[bottom];

                water += width * boundedHeight;
            }
            stack.push(i);
        }
        return water;
    }


    /**
     * INTERVAL PATTERN
     */

    /**
     *
     * @param Intervals: arr[][] = [[1, 3], [2, 4], [6, 8], [9, 10]]
     * Output: [[1, 4], [6, 8], [9, 10]]
     * @return
     */
    public List<int[]> mergeOverlap(int[][] Intervals) {
        if (Intervals.length == 0) return null;

        List<int[]> res = new ArrayList<int[]>();
        Arrays.sort(Intervals, (a, b) -> a[0] - b[0]);
        int[] current = Intervals[0];

        for (int i = 1; i < Intervals.length; i++) {
            if (Intervals[i][0] <= current[1]) {
                //overlapping then get max from last index
                current[1] = Math.max(current[1], Intervals[i][1]);
            } else {
                //no overlapping
                res.add(current);
                current = Intervals[i];
            }
        }
        res.add(current);
        return res;
     }
        //linked list solution with space
        public int[][] mergeOverlap2(int[][] Intervals){
         LinkedList<int[]> merged = new LinkedList<>();
         for(int i=0; i<Intervals.length; i++){
            if(merged.isEmpty() || merged.getLast()[1] < Intervals[i][0]){
                merged.add(Intervals[i]);
            }
            else{
                merged.getLast()[1] = Math.max(merged.getLast()[1], Intervals[i][1]);
            }
          }
         int res[][] = new int[merged.size()][2];
         return merged.toArray(res);
    }


    /**
     * Insert Interval
     * [1,3],[6,7] , newinterval : [2,5]
     * out : [1,5],[6,7]
     */

    public int[][] insert(int[][] intervals,int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        for(int[] curr : intervals){

            // Case 1
            if(curr[1] < newInterval[0]){
                res.add(curr);
            }

            // Case 2
            else if(curr[0] > newInterval[1]){
                res.add(newInterval);
                newInterval = curr;
            }

            // Case 3 overlap
            else{
                newInterval[0] = Math.min(newInterval[0], curr[0]);
                newInterval[1] = Math.max(newInterval[1], curr[1]);
            }
        }
        res.add(newInterval);

        return res.toArray(new int[res.size()][2]);
    }


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


    /**
     * MATRIX PATTERN
     */


    /**
     * Spiral matrix
     * int[][] mat = {
     *     {1,2,3},
     *     {4,5,6},
     *     {7,8,9}
     * }
     *
     * Output:
     * [1,2,3,6,9,8,7,4,5]
     */
    public ArrayList<Integer> spirallyTraverse(int mat[][]) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int top = 0;
        int left = 0;
        int right = mat[0].length - 1;
        int bottom = mat.length - 1;
        int direction = 0;
        while(top<=bottom && left<=right){
            if(direction==0){
                for(int i=left; i<=right; i++){
                    res.add(mat[top][i]);
                }
                top++;
            }
            if(direction==1){
                for(int i=top; i<=bottom; i++){
                    res.add(mat[i][right]);
                }
                right--;
            }
            if(direction==2){
                for(int i=right; i>=left; i--){
                    res.add(mat[bottom][i]);
                }
                bottom--;
            }
            if(direction==3){
                for(int i=bottom; i>=top; i--){
                    res.add(mat[i][left]);
                }
                left++;
            }
            direction = (direction+1)%4;
        }
        return res;
    }

    /**
     * ROTATE MATRIX
     */


     /**  ROTATE MATRIX BY 90% Clockwise
      *  APPROACH :
      *   1) REVERSE COLUMNS + TRANSPOSE
      *   2) TRANSPOSE + REVERSE ROWS
      *
      *  =======ANTI-Clockwise=========//
      *  APPROACH :
      *  1) TRANSPOSE + REVERSE COLUMNS
      *  2) REVERSE ROWS + TRANSPOSE
     */

    //TWO APPROACH
    //APROACH 1

    /**
     * Input:
     * 1 2 3
     * 4 5 6
     * 7 8 9
     *
     * Out :
     * 7 4 1
     * 8 5 2
     * 9 6 3
     * @param mat
     */
    static void rotate(int mat[][]) {
        //steps
        //this works for sqaure matrix n*n not for rectangular
        //1)reverse each COLUMN of transverse matrix
        //2)transpose matrix -> means a[i][i] to a[j][i] -> will bcome 90

        int n = mat.length;
        for(int i=0;i<n/2;i++){
            int temp[]= mat[i];
            mat[i]= mat[n-1-i];
            mat[n-i-1]=temp;
        }

        //transpose
        // j=i+1 to swap only above diagonal , this will avoid duplicate swap
        // each pair will be swapped only once and skip diagonal i==j

        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                int temp  = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        //APPROACH 2
        //We can also achive this by 1) transpose first and then 2) reverse ROWS
        //But ROws reverse need double for loop, as inner content needs to be reverse.

    }

    /**
     * Set Zero in matrix
     * input :
     * 1 2 3
     * 4 0 6
     * 7 8 9
     * out:
     * 1 0 3
     * 0 0 0
     * 7 0 9
     */

    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Check first row
        for(int j=0; j<cols; j++){
            if(matrix[0][j] == 0)
                firstRowZero = true;
        }

        // Check first column
        for(int i=0; i<rows; i++){
            if(matrix[i][0] == 0)
                firstColZero = true;
        }

        // Mark rows and columns
        //Why i,j start from 1 instead of 0?
        //Because:  First row (row 0) is being used as column markers
        //First column (col 0) is being used as row markers
        //We must not overwrite those while scanning.
        for(int i=1; i<rows; i++){
            for(int j=1; j<cols; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Zero using markers
        for(int i=1; i<rows; i++){
            for(int j=1; j<cols; j++){

                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }

        // Zero first row
        if(firstRowZero){
            for(int j=0; j<cols; j++)
                matrix[0][j]=0;
        }

        // Zero first column
        if(firstColZero){
            for(int i=0; i<rows; i++)
                matrix[i][0]=0;
        }
    }

    /**
     * Search in 2D Matrix
     */
    public boolean searchMatrix(int[][] mat, int x) {
        int m = mat.length;
        int n = mat[0].length;
        int left = 0;
        int right =  m*n-1;

        while(left<=right){
            int mid = left+(right-left)/2;
            int row = mid/n;
            int col = mid%n;
            int midValue = mat[row][col];

            if(midValue==x)
                return true;
            else if(midValue>x)
                right = mid-1;
            else
                left = mid+1;
        }
        return false;
    }

    //Approach 2
    public boolean searchMatrix2(int[][] mat, int x) {
        int row = 0;
        int col = mat[0].length - 1;

        while(row < mat.length && col >= 0){
            if(mat[row][col] == x)
                return true;

            else if(mat[row][col] > x)
                col--;       // move left
            else
                row++;       // move down
        }
        return false;
    }


    /**
     * In-place / Rearrangement Pattern
     */

    /**
     * Next permutation
     * Input: arr[] = [2, 4, 1, 7, 5, 0]
     * Output: [2, 4, 5, 0, 1, 7]
     * @param arr
     */
    void nextPermutation(int arr[]){
        // code here
        int N = arr.length;
        int last= N-2;
        for(int i=N-1; i>0; i--){
            //find the first lowest index ele from right
            if(arr[i]<=arr[i-1])
                last--;//2->1
            else
                break;
        }
        if(last==-1){
            //means high ele is at 0th index and all are lower on in descedning order
            //so just reverese and return
            reverse(arr,0);
            return;
        }
        //find nextIndex to replace last(lowest element) with next greater element
        int nextIndex=-1;
        for(int i=N-1; i>0; i--){
            if(arr[i]>arr[last]){
                nextIndex = i;
                break;
            }
        }

        swap(arr,last,nextIndex);
        reverse(arr,last+1);
    }

    public static void swap (int arr[], int x, int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void reverse(int arr[],int index){
        int n = arr.length - 1;
        while(index < n) {
            swap(arr,index,n);
            index++;
            n--;
        }
    }

    /**
     * Rotate array by K
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;

        k = k % n;   // important
        //n=5,k=7 , 7 % 5 = 2
        //So rotating 7 steps is same as rotating 2 steps.

        reverse(nums, 0, n-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, n-1);
    }
    private void reverse(int[] nums, int left, int right){
        while(left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * Dutch flag
     */

    public static void sort012(int a[], int n) {
        int lo=0,mid=0, h=n-1,temp=0;

        while(mid<=h){
            switch(a[mid]) {
                case 0 :
                    temp = a[lo];
                    a[lo]= a[mid];
                    a[mid]=temp;

                    mid++;
                    lo++;
                    break;

                case 1 :
                    mid++;
                    break;

                case 2:
                    temp = a[mid];
                    a[mid] = a[h];
                    a[h] = temp;
                    h--;
                    break;
            }
        }
    }
}