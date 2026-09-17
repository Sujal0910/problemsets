class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        int[] prefixSum = new int[n + 1];
        Map<Integer, Integer> sumIndexMap = new HashMap<>();
        
        // Initialize prefix sum array and map
        prefixSum[0] = 0;
        sumIndexMap.put(0, 0);
        
        int minTotal = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
            
            // Check if a subarray ending at i has sum = target
            int currentSum = prefixSum[i + 1];
            int prevSum = currentSum - target;
            
            if (sumIndexMap.containsKey(prevSum)) {
                int startIdx = sumIndexMap.get(prevSum);
                int currentLen = i + 1 - startIdx;
                
                // If there was a valid subarray before this one, update total
                if (startIdx > 0 && minLen[startIdx - 1] != Integer.MAX_VALUE) {
                    minTotal = Math.min(minTotal, minLen[startIdx - 1] + currentLen);
                }
                
                // Update minLen for current position
                // minLen[i] stores the min length of a valid subarray ending at or before i
                if (i > 0) {
                    minLen[i] = Math.min(minLen[i - 1], currentLen);
                } else {
                    minLen[i] = currentLen;
                }
            } else {
                // If no valid subarray ends exactly at i, carry forward the previous minimum
                if (i > 0) {
                    minLen[i] = minLen[i - 1];
                } else {
                    minLen[i] = Integer.MAX_VALUE;
                }
            }
            
            // Store current prefix sum index (latest index for each sum)
            sumIndexMap.put(currentSum, i + 1);
        }
        
        return minTotal == Integer.MAX_VALUE ? -1 : minTotal;
    }
}   