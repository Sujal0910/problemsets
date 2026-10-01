class Solution {
    public boolean isValid(String s) {
        Stack<Character>stk = new Stack<Character>();
        
        int n = s.length(), i;
        
        for(i=0; i<n; i++)
        {
            char c = s.charAt(i);
            
            if(c=='(' || c=='{' || c=='[')
                stk.push(c);
            else{
                if(stk.size()==0) return false;
                char top = stk.peek();
                if(c==')' && top!='(' ) return false;
                else if(c=='}' && top!='{') return false;
                else if(c==']' && top!='[') return false;
                else stk.pop();
            }
        }
        
        return stk.size()==0;
    }
}