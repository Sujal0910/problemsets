class Solution:
    def removeInvalidParentheses(self, s: str) -> List[str]:
        
        left_remove = right_remove = 0
        for char in s:
            if char == '(':
                left_remove += 1
            elif char == ')':
                if left_remove > 0:
                    left_remove -= 1
                else:
                    right_remove += 1
        
        result = set()
        
        def dfs(index, l_rem, r_rem, l_cnt, r_cnt, current_str):
            
            if index == len(s):
                if l_rem == 0 and r_rem == 0:
                    result.add(current_str)
                return
            
            
            if len(s) - index < l_rem + r_rem or l_cnt < r_cnt:
                return
            
            char = s[index]
            
            if char == '(' and l_rem > 0:
                dfs(index + 1, l_rem - 1, r_rem, l_cnt, r_cnt, current_str)
            
           
            if char == ')' and r_rem > 0:
                dfs(index + 1, l_rem, r_rem - 1, l_cnt, r_cnt, current_str)
            
            
            dfs(index + 1, l_rem, r_rem, 
                l_cnt + (1 if char == '(' else 0), 
                r_cnt + (1 if char == ')' else 0), 
                current_str + char)

        dfs(0, left_remove, right_remove, 0, 0, '')
        return list(result)   