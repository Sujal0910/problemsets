import java.util.*;

class Solution {
    public long shadowPairs(int[] nums) {
        int n = nums.length;
        int[] R = new int[n];
        Arrays.fill(R, n);
        
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peek()] > nums[i]) {
                R[st.pop()] = i;
            }
            st.push(i);
        }
        
        Map<Integer, List<Integer>> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pos.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        Map<Integer, Integer> currentIndex = new HashMap<>();
        long totalShadowPairs = 0;
        
        for (int i = 0; i < n; i++) {
            int rightBoundary = R[i];
            
            int elementsInRange = rightBoundary - 1 - i;
            
            if (elementsInRange > 0) {
                List<Integer> list = pos.get(nums[i]);
                int idx_i = currentIndex.getOrDefault(nums[i], 0);
                
                int searchResult = Collections.binarySearch(list, rightBoundary);
                int idx_R = searchResult >= 0 ? searchResult : -searchResult - 1;
                
                int countEqual = idx_R - 1 - idx_i;
                
                totalShadowPairs += (elementsInRange - countEqual);
            }
            
            currentIndex.put(nums[i], currentIndex.getOrDefault(nums[i], 0) + 1);
        }
        
        return totalShadowPairs;
    }
}