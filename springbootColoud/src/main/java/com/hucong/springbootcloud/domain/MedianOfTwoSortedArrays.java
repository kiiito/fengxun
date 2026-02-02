package com.hucong.springbootcloud.domain;

public class MedianOfTwoSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int m = nums1.length;
        int n = nums2.length;
        int totalLeft = (m + n + 1) / 2;

        int left = 0, right = m;
        while (left < right) {
            int i = left + (right - left + 1) / 2;
            int j = totalLeft - i;
            if (nums1[i - 1] > nums2[j]) {
                right = i - 1;
            } else {
                left = i;
            }
        }

        int i = left;
        int j = totalLeft - i;

        int maxLeft;
        if (i == 0) {
            maxLeft = nums2[j - 1];
        } else if (j == 0) {
            maxLeft = nums1[i - 1];
        } else {
            maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
        }

        int minRight;
        if (i == m) {
            minRight = nums2[j];
        } else if (j == n) {
            minRight = nums1[i];
        } else {
            minRight = Math.min(nums1[i], nums2[j]);
        }

        if ((m + n) % 2 == 1) {
            return (double) maxLeft;
        } else {
            return (maxLeft + minRight) / 2.0;
        }
    }

    public static void main(String[] args) {
        int[] nums1_1 = {1, 3};
        int[] nums2_1 = {2};
        System.out.println(findMedianSortedArrays(nums1_1, nums2_1));

        int[] nums1_2 = {1, 2};
        int[] nums2_2 = {3, 4};
        System.out.println(findMedianSortedArrays(nums1_2, nums2_2));
    }
}
