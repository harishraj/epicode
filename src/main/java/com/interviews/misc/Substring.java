package com.interviews.misc;

// https://leetcode.com/problems/minimum-window-substring/
// https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems

public class Substring {

    public String minWindow(String s, String t) {
        String result = "";
        if (s == "" || t.length() > s.length())
            return result;
        int[] map = new int[128];
        int start = 0;
        int minStart = 0;
        int end = 0;
        int count = t.length();
        int minLength = Integer.MAX_VALUE;
        for (char temp : t.toCharArray()) {
            map[temp]++;
        }
        while (end < s.length()) {
            if (map[s.charAt(end)] > 0)
                count--;
            map[s.charAt(end)]--;
            end++;
            while (count == 0) {
                if (end - start < minLength) {
                    minStart = start;
                    minLength = end - start;
                }
                map[s.charAt(start)]++;
                if (map[s.charAt(start)] > 0)
                    count++;
                start++;
            }
        }
        return (minLength == Integer.MAX_VALUE) ? "" : s.substring(minStart, minStart + minLength);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = 0;
        int head = 0, i = 0;
        int[] sTable = new int[256];
        int repeat = 0;
        while (i < s.length()) {
            if (sTable[s.charAt(i++)]++ > 0) repeat++;   //total number of repeat
            while (repeat > 0) {
                if (sTable[s.charAt(head++)]-- > 1) repeat--;
            }
            len = Math.max(len, i - head);
        }
        return len;
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int start = 0, end = 0, counter = 0;
        int[] map = new int[128];
        int max = Integer.MIN_VALUE;
        while (end < s.length()) {
            if (map[s.charAt(end)] == 0) {
                counter++;
            }
            map[s.charAt(end)]++;
            end++;
            while (counter > k) {
                map[s.charAt(start)]--;
                if (map[s.charAt(start)] == 0) {
                    counter--;
                }
                start++;
            }
            max = Math.max(max, end - start);
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

}


