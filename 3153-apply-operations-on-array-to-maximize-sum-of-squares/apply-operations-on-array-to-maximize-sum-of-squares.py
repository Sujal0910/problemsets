class Solution:
    def maxSum(self, nums: List[int], k: int) -> int:
        MOD = 10**9 + 7
      
        # Count the number of set bits at each position across all numbers
        bit_count = [0] * 31
        for num in nums:
            for bit_position in range(31):
                # Check if the bit at position 'bit_position' is set
                if num >> bit_position & 1:
                    bit_count[bit_position] += 1
      
        total_sum = 0
      
        # Greedily construct k numbers to maximize the sum of squares
        for _ in range(k):
            current_number = 0
          
            # For each bit position, if there are available bits, set them
            for bit_position in range(31):
                if bit_count[bit_position] > 0:
                    # Set the bit at this position in the current number
                    current_number |= 1 << bit_position
                    # Decrement the available count for this bit position
                    bit_count[bit_position] -= 1
          
            # Add the square of the constructed number to the total sum
            total_sum = (total_sum + current_number * current_number) % MOD
      
        return total_sum
