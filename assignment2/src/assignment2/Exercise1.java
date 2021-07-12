package assignment2;

public class Exercise1 {
    /**
     * Implementation of merge sort. Mergesort algorithm's time complexity is O(n log n)
     * @param arr array to sort
     * @return sorted array
     */
    public static int[] mergeSort(int[] arr){
        int arrLen = arr.length;
        // if only 1 element or less, the list is already sorted. Recursion base case.
        if (arrLen <2) return arr;
        // split the length to get middle index
        int middle = arrLen / 2;
        // instantiate list for left and right sides
        int[] left = new int[middle];
        int[] right = new int[arrLen - middle];

        // populate left and right list
        for (int i=0; i<middle; i++){
            left[i] = arr[i];
        }
        for (int i=0; i<arrLen - middle; i++){
            right[i] = arr[middle + i];
        }
        // recursive call to split middle and sort
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    /**
     * Merge two sorted arrays
     * @param left left array
     * @param right right array
     * @return a merged array from the two sorted arrays
     */
    public static int[] merge(int[] left, int[] right){
        if (left == null) return right;
        if (right == null) return left;
        int leftLen = left.length;
        int rightLen = right.length;
        int[] merged = new int[leftLen + rightLen];

        int i = 0, l = 0, r = 0;
        while (l < leftLen && r < rightLen && i < leftLen + rightLen){
            if (left[l] <= right[r]){
                merged[i] = left[l];
                l++;
            } else{
                merged[i] = right[r];
                r++;
            }
            i++;
        }
        while (l < leftLen){
            merged[i] = left[l];
            l++;
            i++;
        }
        while (r < rightLen){
            merged[i] = right[r];
            r++;
            i++;
        }
        return merged;
    }

    /**
     * Compute sum of k largest numbers in the array
     * @param arr array to select elements from
     * @param k number of largest elements to sum
     * @return sum of k largest numbers in the array
     */
    public static int kLargestSum(int[] arr, int k){
        // initialize sum variable
        int sum = 0;
        // output of mergesort
        int[] sorted = mergeSort(arr);
        // since array is in ascending order, sum the last k numbers
        for (int i=sorted.length-1; i>=sorted.length-k; i--){
            sum += sorted[i];
        }
        return sum;
    }

    public static void main(String[] args){
        int k = 3;
        int[] arr = {3,7,5,12,6};
        System.out.println(kLargestSum(arr, k));
    }
}

