package org.example.patterns;

public class ArrayBasicsPatterns {

    // Time: O(n)
    // Space: O(1)
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

    // Time: O(n)
    // Space: O(1)
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

    /** Max Balanced Split of a Binary String
     * Input: s = "0100110101"
     * Output: 4
     * Explanation:
     * The required substrings are 01, 0011, 01 and 01.
     */
    public static int maxSubStr(String s) {
        // 0111100010
        int count = 0;
        int res = 0;
        for(char ch : s.toCharArray()){
            if(ch=='0')
                count++;
            else
                count--;

            if(count==0)
                res++;
        }
        return count == 0 ? res : -1;
    }

    /**
     * Convert a sentence into its equivalent mobile numeric keypad sequence
     * S = "GFG"
     * Output: 43334
     */
     String printSequence(String S)
    {
        //ASCII
        //A-Z : 65-90
        //a-z : 97-122
        //0-9 : 48-57
        //WE CAN use hashMap too ex : A : 2, B:22, C:222, D:3,E:33,F:333, G:4,H:33, I:333
        String[]keyNum =
                       {"2", "22", "222",
                        "3", "33", "333",
                        "4", "44", "444",
                        "5", "55", "555",
                        "6", "66", "666",
                        "7", "77", "777","7777",
                        "8", "88", "888",
                        "9", "99","999", "9999"
                        };
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<S.length(); i++){
            char c = S.charAt(i);
            if(c==' '){
                sb.append(0);
            }else{
                sb.append(keyNum[c-'A']);
            }
        }
        return sb.toString();

    }

}