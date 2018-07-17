/***
 * Return the median of 2 sorted arrays of different size
 * Array A(m) = A(0), A(1), ..., A(m-1)
 * Array B(n) = B(0), B(1), ..., B(n1-1)
 * Solution: recursive with time complexity O(log(m+n))
 */
public class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int k = (m + n + 1) / 2; // 0 -> 0th, 1 -> 1th, 2 -> 1th, 3 -> 2nd. Could check K==0 to handle empty arrays
        if ((m + n) % 2 == 1) // odd number of numbers, return middle one
            return findKthInSortedArrays(A, B, 0, 0, k);
        else // even number of numbers, return average of middle two
            return (findKthInSortedArrays(A, B, 0, 0, k) +
                    findKthInSortedArrays(A, B, 0, 0, k + 1)) / 2d;
    }

    public double findKthInSortedArrays(int[] A, int[] B, int startA, int startB, int k) {
        if (startA > A.length - 1) // array A numbers all reduced
            return B[startA + startB - A.length + k - 1]; // k-th number in array B from index (total reduced - A size)
        if (startB > B.length - 1)
            return A[startA + startB - B.length + k - 1];
        if (k == 1) // 1st number, note K reduce to 1 only
            return Math.min(A[startA], B[startB]);

        int reduce = k / 2; // 3 -> 2 -> 1 -> 1
        int tail1 = A[Math.min(startA + reduce - 1, A.length - 1)];
        int tail2 = B[Math.min(startB + reduce - 1, B.length - 1)];

        if (tail1 < tail2)
            return findKthInSortedArrays(A, B, startA + reduce, startB, k - reduce);
        else
            return findKthInSortedArrays(A, B, startA, startB + reduce, k - reduce);
    }

}