class Solution:
    def missingNumber(self, nums: list[int]) -> int:
        n=len(nums)
        sum=0
        for i in nums:
            sum+=i
        return (int((n*(n+1))/2-sum))