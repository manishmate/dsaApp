package org.example.patterns;

public class BinarySearchPattern {

    //binarySearch : O(log n) time, O(1) space
    //searchRange : O(log n) time, O(1) space
    //findFirst : O(log n) time, O(1) space
    //findLast : O(log n) time, O(1) space
    //rotatedArraySearch : O(log n) time, O(1) space
    //findMinimumInRotatedArray : O(log n) time, O(1) space
    //FirstInArray : O(log n) time, O(1) space
    //minEatingSpeed : O(n log m) time, O(1) space

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
}