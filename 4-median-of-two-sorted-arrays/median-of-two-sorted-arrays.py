class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        # Ensure nums1 is the smaller array for binary search
        if len(nums1) > len(nums2):
            nums1, nums2 = nums2, nums1
            
        m, n = len(nums1), len(nums2)
        total = m + n
        half = (total + 1) // 2
        
        left, right = 0, m
        
        while left <= right:
            partition1 = (left + right) // 2
            partition2 = half - partition1
            
            # Handle edge cases where partition is at the boundary
            maxLeft1 = float('-inf') if partition1 == 0 else nums1[partition1 - 1]
            minRight1 = float('inf') if partition1 == m else nums1[partition1]
            maxLeft2 = float('-inf') if partition2 == 0 else nums2[partition2 - 1]
            minRight2 = float('inf') if partition2 == n else nums2[partition2]
            
            if maxLeft1 <= minRight2 and maxLeft2 <= minRight1:
                # Correct partition found
                if total % 2 == 0:
                    return (max(maxLeft1, maxLeft2) + min(minRight1, minRight2)) / 2.0
                else:
                    return max(maxLeft1, maxLeft2)
            elif maxLeft1 > minRight2:
                # Move partition in nums1 to the left
                right = partition1 - 1
            else:
                # Move partition in nums1 to the right
                left = partition1 + 1   