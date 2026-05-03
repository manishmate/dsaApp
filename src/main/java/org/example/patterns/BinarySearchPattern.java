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

    /** FInd Peak element
     *
     * @param [1,2,3,1]
     * @return 3
     */
        public int findPeakElement(int[] nums) {
            int left = 0;
            int right = nums.length - 1;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (nums[mid] < nums[mid + 1]) {
                    // We are on an upward slope. Peak is to the right.
                    left = mid + 1;
                } else {
                    // We are on a downward slope. Peak is to the left (or is mid).
                    right = mid;
                }
            }
            // Left and Right converge to the peak index
            return left;
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
     * max book allocation for students
     * Books (Pages): [25, 46, 28, 49, 24]
     * Students : 3
     * output : 74
     */

    public static int bookAllocation(int[] books, int stu) {
        int n = books.length;
        if (stu > n) return -1;

        int left = 0;
        int right = 0;
        for (int i = 0; i < n; i++) {
            left = Math.max(books[i], left);
            right += books[i];
        }

        int ans = right; // Initialize with the maximum possible sum
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isPossible(books, mid, stu)) {
                ans = mid;      // Found a possible solution, try to find a smaller one
                right = mid - 1;
            } else {
                left = mid + 1; // Not possible, need a larger limit
            }
        }
        return ans;
    }

    public static boolean isPossible(int[] books, int mid, int stu) {
        int studentCount = 1;
        int currentPages = 0;

        for (int i = 0; i < books.length; i++) {
            // Fix 1: Compare against 'mid' directly, not 'books[mid]'
            if (currentPages + books[i] <= mid) {
                currentPages += books[i];
            } else {
                studentCount++;
                // Fix 2: Check if we've used more students than allowed
                if (studentCount > stu) {
                    return false;
                }
                currentPages = books[i];
            }
        }
        return true;
    }

    /** Capacity to Ship Packages
     * You have a conveyor belt with packages that must be shipped in D days.
     * The i-th package has a weight weights[i].
     *
     * Weights: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], Days: 5
     * output : 15
     *
     * Why it's the same as Book Allotment:
     * Books = Packages
     * Pages = Weights
     * Students = Days
     *
     * Contiguous Allocation = Shipping in order
     */

    public int shipWithinDays(int[] weights, int days) {
        int low = 0;
        int high = 0;

        for (int w : weights) {
            low = Math.max(low, w); // Must carry the heaviest package
            high += w;             // Max capacity needed for 1 day
        }

        int ans = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isPossibles(weights, days, mid)) {
                ans = mid;
                high = mid - 1; // Try a smaller capacity
            } else {
                low = mid + 1;  // Capacity too small, increase it
            }
        }
        return ans;
    }
    private boolean isPossibles(int[] weights, int days, int capacity) {
        int daysUsed = 1;
        int currentLoad = 0;

        for (int w : weights) {
            if (currentLoad + w <= capacity) {
                currentLoad += w;
            } else {
                daysUsed++;
                currentLoad = w;
                if (daysUsed > days) return false;
            }
        }
        return true;
    }

}