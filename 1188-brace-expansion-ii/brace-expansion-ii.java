import java.util.*;

class Solution {
    private TreeSet<String> resultSet = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        return new ArrayList<>(resultSet);
    }

    private void dfs(String exp) {
        int j = exp.indexOf('}');
        // Base case: no more braces, add to set
        if (j == -1) {
            resultSet.add(exp);
            return;
        }
        // Find matching opening brace
        int i = exp.lastIndexOf('{', j);
        String a = exp.substring(0, i); // Prefix
        String c = exp.substring(j + 1); // Suffix
        // Split brace content by comma
        String[] parts = exp.substring(i + 1, j).split(",");
        
        for (String b : parts) {
            dfs(a + b + c);
        }
    }
}   