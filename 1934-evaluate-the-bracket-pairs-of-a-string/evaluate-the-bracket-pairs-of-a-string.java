import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        
        // Store key -> value
        Map<String, String> map = new HashMap<>();
        
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        
        int i = 0;
        
        while (i < s.length()) {
            
            if (s.charAt(i) == '(') {
                
                // Find closing bracket
                int j = i + 1;
                while (s.charAt(j) != ')') {
                    j++;
                }
                
                // Extract key
                String key = s.substring(i + 1, j);
                
                // Replace with value or ?
                result.append(map.getOrDefault(key, "?"));
                
                // Move after ')'
                i = j + 1;
                
            } else {
                result.append(s.charAt(i));
                i++;
            }
        }
        
        return result.toString();
    }
}