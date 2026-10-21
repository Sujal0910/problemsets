class Solution:
    def reverse(self, x: int) -> int:
        is_negative = False
        if x < 0:
            is_negative = True
        num = abs(x)
        answer = 0
        while num > 0:
            last_digit = num % 10
            answer = (answer * 10) + last_digit
            num //= 10

        if answer < (-(21**31)) or answer > (2**31 - 1):
            return 0
        return -answer if is_negative else answer