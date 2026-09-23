class Solution {
    public int minOperations(int[] nums, int x) {
        // Calculate target sum for the middle subarray
        int targetSum = 0;
        for (int num : nums) {
            targetSum += num;
        }
        targetSum -= x;

        // If target is negative, it's impossible
        if (targetSum < 0) return -1;
        // If target is 0, we must remove all elements
        if (targetSum == 0) return nums.length;

        // Find the longest subarray with sum equal to targetSum
        int maxLength = -1;
        int currentSum = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];

            // Shrink window from the left if sum exceeds target
            while (currentSum > targetSum && left <= right) {
                currentSum -= nums[left];
                left++;
            }

            // Check if we found a valid subarray
            if (currentSum == targetSum) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        return maxLength == -1 ? -1 : nums.length - maxLength;
    }
}   