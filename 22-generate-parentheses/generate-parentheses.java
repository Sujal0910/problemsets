class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(n, 0, 0, new StringBuilder(), res);
        return res;
    }

    private void backtrack(int n, int open, int closed, StringBuilder sb, List<String> res) {
        if (sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append('(');
            backtrack(n, open + 1, closed, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (closed < open) {
            sb.append(')');
            backtrack(n, open, closed + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}   