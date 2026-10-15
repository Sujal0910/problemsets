class Solution:
    def findDisappearedNumbers(self, nums: list[int]) -> list[int]:
        original_length = len(nums) 
        unique_nums = set(nums)
        a = []
        for i in range(1, original_length + 1):
            if i not in unique_nums:
                a.append(i)
                
        return a
