class Solution {
    private static List<Long> evenPals = new ArrayList<>();
    private static List<Long> oddPals = new ArrayList<>();
    
    static {
        for (int len = 1; len <= 10; len++) {
            int halfLen = (len + 1) / 2;
            long start = (long) Math.pow(10, halfLen - 1);
            long end = (long) Math.pow(10, halfLen) - 1;
            
            
            if (len == 10) {
                end = 20000;
            }
            
            for (long i = start; i <= end; i++) {
                long p = i;
                long temp = i;
                if (len % 2 == 1) {
                    temp /= 10;
                }
                while (temp > 0) {
                    p = p * 10 + (temp % 10);
                    temp /= 10;
                }
                if (p % 2 == 0) {
                    evenPals.add(p);
                } else {
                    oddPals.add(p);
                }
            }
        }
    }

    public long minOperations(int[] nums) {
        long totalOps = 0;
        
        for (int num : nums) {
            List<Long> list = (num % 2 == 0) ? evenPals : oddPals;
            int idx = Collections.binarySearch(list, (long) num);
            
            if (idx < 0) {
                int ins = -idx - 1;
                long minDiff = Long.MAX_VALUE;
                if (ins < list.size()) {
                    minDiff = Math.min(minDiff, list.get(ins) - num);
                }
                if (ins > 0) {
                    minDiff = Math.min(minDiff, num - list.get(ins - 1));
                }
                totalOps += minDiff / 2;
            }
        }

        return totalOps;
    }
}