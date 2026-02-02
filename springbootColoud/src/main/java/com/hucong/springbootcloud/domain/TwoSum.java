package com.hucong.springbootcloud.domain;
import java.util.HashMap;
import java.util.Map;
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[]{numMap.get(complement), i};
            }
            numMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("无解");
    }
    // 测试代码
    public static void main(String[] args) {
        TwoSum solution = new TwoSum();
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = solution.twoSum(nums1, target1);
        System.out.println("[" + result1[0] + ", " + result1[1] + "]");
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = solution.twoSum(nums2, target2);
        System.out.println("[" + result2[0] + ", " + result2[1] + "]");
        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = solution.twoSum(nums3, target3);
        System.out.println("[" + result3[0] + ", " + result3[1] + "]");
    }
}
