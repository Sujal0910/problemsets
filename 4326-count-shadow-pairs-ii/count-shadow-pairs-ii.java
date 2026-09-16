import java.util.Arrays;

class Solution {
    int MAX_RANK;
    int[] bitMin;
    int[] bitMax;
    int[] bitSum;
    int[] rank;
    int[] U;
    int[] D;
    int ans;

    void updateMin(int rankVal, int val) {
        int rIdx = MAX_RANK + 1 - rankVal;
        for (; rIdx < bitMin.length; rIdx += rIdx & -rIdx) {
            bitMin[rIdx] = Math.min(bitMin[rIdx], val);
        }
    }

    int queryMin(int rankVal) {
        int rIdx = MAX_RANK + 1 - rankVal;
        int res = MAX_RANK + 1;
        for (; rIdx > 0; rIdx -= rIdx & -rIdx) {
            res = Math.min(res, bitMin[rIdx]);
        }
        return res;
    }

    void resetMin(int rankVal) {
        int rIdx = MAX_RANK + 1 - rankVal;
        for (; rIdx < bitMin.length; rIdx += rIdx & -rIdx) {
            bitMin[rIdx] = MAX_RANK + 1;
        }
    }

    void updateMax(int rankVal, int val) {
        for (; rankVal < bitMax.length; rankVal += rankVal & -rankVal) {
            bitMax[rankVal] = Math.max(bitMax[rankVal], val);
        }
    }

    int queryMax(int rankVal) {
        int res = 0;
        for (; rankVal > 0; rankVal -= rankVal & -rankVal) {
            res = Math.max(res, bitMax[rankVal]);
        }
        return res;
    }

    void resetMax(int rankVal) {
        for (; rankVal < bitMax.length; rankVal += rankVal & -rankVal) {
            bitMax[rankVal] = 0;
        }
    }

    void addSum(int rankVal, int val) {
        for (; rankVal < bitSum.length; rankVal += rankVal & -rankVal) {
            bitSum[rankVal] += val;
        }
    }

    int querySum(int rankVal) {
        int sum = 0;
        for (; rankVal > 0; rankVal -= rankVal & -rankVal) {
            sum += bitSum[rankVal];
        }
        return sum;
    }

    void solve(int L, int R) {
        if (L >= R) return;
        int mid = L + (R - L) / 2;

        solve(L, mid);
        solve(mid + 1, R);

        for (int i = mid; i >= L; i--) {
            U[i] = queryMin(rank[i] + 1);
            updateMin(rank[i], rank[i]);
        }
        for (int i = mid; i >= L; i--) {
            resetMin(rank[i]);
        }

        for (int j = mid + 1; j <= R; j++) {
            D[j] = queryMax(rank[j] - 1);
            updateMax(rank[j], rank[j]);
        }
        for (int j = mid + 1; j <= R; j++) {
            resetMax(rank[j]);
        }

        int lenLeft = mid - L + 1;
        long[] leftArr = new long[lenLeft];
        for (int i = 0; i < lenLeft; i++) {
            leftArr[i] = ((long) U[L + i] << 32) | (L + i);
        }
        Arrays.sort(leftArr);

        int lenRight = R - mid;
        long[] rightArr = new long[lenRight];
        for (int j = 0; j < lenRight; j++) {
            rightArr[j] = ((long) rank[mid + 1 + j] << 32) | (mid + 1 + j);
        }
        Arrays.sort(rightArr);

        int pLeft = lenLeft - 1;
        for (int pRight = lenRight - 1; pRight >= 0; pRight--) {
            int j = (int) rightArr[pRight];
            int Y_j = rank[j];
            int D_j = D[j];

            while (pLeft >= 0) {
                int U_i = (int) (leftArr[pLeft] >>> 32);
                if (U_i >= Y_j) {
                    int i = (int) leftArr[pLeft];
                    addSum(rank[i], 1);
                    pLeft--;
                } else {
                    break;
                }
            }

            if (Y_j - 1 >= Math.max(1, D_j)) {
                ans += querySum(Y_j - 1) - querySum(Math.max(1, D_j) - 1);
            }
        }

        for (int k = pLeft + 1; k < lenLeft; k++) {
            int i = (int) leftArr[k];
            addSum(rank[i], -1);
        }
    }

    public int shadowPairs(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int uniqueCount = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                sorted[uniqueCount++] = sorted[i];
            }
        }

        MAX_RANK = uniqueCount;
        bitMin = new int[MAX_RANK + 2];
        Arrays.fill(bitMin, MAX_RANK + 1);
        bitMax = new int[MAX_RANK + 2];
        bitSum = new int[MAX_RANK + 2];

        rank = new int[n];
        for (int i = 0; i < n; i++) {
            int l = 0, r = uniqueCount - 1;
            int res = 0;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (sorted[m] == nums[i]) {
                    res = m;
                    break;
                } else if (sorted[m] < nums[i]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            rank[i] = res + 1;
        }

        U = new int[n];
        D = new int[n];
        ans = 0;

        solve(0, n - 1);

        return ans;
    }
}