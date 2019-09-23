package com.digisky.liuwei2.leetcode;

import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode506
 *
 * @author Eric Liu
 * @date 2019/09/23 23:06
 */
public class LeetCode506 {
    public String[] findRelativeRanks(int[] nums) {
        int[] nums1 = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums1);
        Map<Integer, String> map = new HashMap<>(nums.length);
        int index = 0;
        for (int i = nums1.length - 1; i >= 0; i--) {
            index++;
            if (index == 1) {
                map.put(nums1[i], "Gold Medal");
                continue;
            }
            if (index == 2) {
                map.put(nums1[i], "Silver Medal");
                continue;
            }
            if (index == 3) {
                map.put(nums1[i], "Bronze Medal");
                continue;
            }
            map.put(nums1[i], String.valueOf(index));
        }

        String[] rank = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            rank[i] = map.get(nums[i]);
        }

        return rank;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 4, 3, 2, 1};
        String[] rank = new LeetCode506().findRelativeRanks(nums);

        for (String v : rank) {
            System.out.println(v);
        }
    }
}
