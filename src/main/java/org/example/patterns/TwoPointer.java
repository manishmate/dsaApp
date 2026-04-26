package org.example.patterns;

import java.util.*;

public class TwoPointer {
    //twoSumPair : O(n) time, O(1) space
    //threeSum : O(n²) time, O(1) space
    //fourSum : O(n³) time, O(1) space
    //maxWaterInContainer : O(n) time, O(1) space
    //maxRainWater : O(n) time, O(n) space
    //maxRainWater2 : O(n) time, O(1) space

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
}
