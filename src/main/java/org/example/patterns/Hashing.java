package org.example.patterns;

import java.util.*;

public class Hashing {

    //twoSumUnSorted // Time O(n), Space O(n)
    //isAnagram // O(n), O(1)
    //grpAnagram // O(n*k), O(n)
    //majority // O(n), O(1)
    //topKFrequent // O(n log k), O(n)
    //longestConsecutive // O(n), O(n)

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

}
