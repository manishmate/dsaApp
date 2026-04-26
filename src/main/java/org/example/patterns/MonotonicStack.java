package org.example.patterns;

import java.util.Stack;

public class MonotonicStack {
    //nextGreater : O(n) time, O(n) space
    //dailyTemperatures : O(n) time, O(n) space
    //largestRectangleArea : O(n) time, O(n) space
    //trap (prefix array) : O(n) time, O(n) space
    //rainWater (two pointer) : O(n) time, O(1) space
    //trapByStack : O(n) time, O(n) space

    /**
     * MONOTONIC STACK
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
}
