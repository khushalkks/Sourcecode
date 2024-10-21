/**
 * @file RadixSort.java
 * @brief A Java implementation of the Radix Sort algorithm.
 * 
 * Radix sort is a non-comparative integer sorting algorithm. It sorts data
 * by processing individual digits. This implementation sorts numbers by
 * their decimal representation (base 10).
 */

public class RadixSort {

    /**
     * @brief Gets the maximum value in an array.
     * 
     * This function returns the maximum value in the input array, which is
     * used to determine the number of digits of the largest number.
     * 
     * @param arr The array to be searched.
     * @param n The number of elements in the array.
     * @return The maximum value in the array.
     */
    static int getMax(int arr[], int n) {
        int max = arr[0];  /**< Initialize max with the first element. */
        for (int i = 1; i < n; i++) {  /**< Iterate over all elements of the array. */
            if (arr[i] > max) {  /**< Update max if the current element is greater. */
                max = arr[i];
            }
        }
        return max;  /**< Return the maximum value found. */
    }

    /**
     * @brief Performs counting sort based on the digit represented by exp.
     * 
     * This function uses counting sort to sort the array based on a specific
     * digit (units, tens, hundreds, etc.) as represented by the exponent `exp`.
     * 
     * @param arr The array to be sorted.
     * @param n The number of elements in the array.
     * @param exp The exponent representing the digit position to be sorted (1 for units, 10 for tens, etc.).
     */
    static void countingSort(int arr[], int n, int exp) {
        int output[] = new int[n];  /**< Output array to store sorted results. */
        int count[] = new int[10];  /**< Initialize count array to store frequency of each digit (0-9). */
        
        // Initialize count array with 0
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }

        // Store count of occurrences in count[]
        for (int i = 0; i < n; i++) {
            int index = (arr[i] / exp) % 10;  /**< Extract digit at the current position. */
            count[index]++;  /**< Increment count of the digit. */
        }

        // Change count[i] so it contains actual position of the digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];  /**< Modify count array to have cumulative counts. */
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            int index = (arr[i] / exp) % 10;  /**< Extract digit at the current position again. */
            output[count[index] - 1] = arr[i];  /**< Place the element in the correct position in output[]. */
            count[index]--;  /**< Decrease count for the digit. */
        }

        // Copy the output array to arr[], so that arr[] now contains sorted numbers
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];  /**< Overwrite the original array with the sorted values. */
        }
    }

    /**
     * @brief Main function to implement radix sort.
     * 
     * This function uses the countingSort() method to sort the array for each
     * digit, from least significant to most significant.
     * 
     * @param arr The array to be sorted.
     * @param n The number of elements in the array.
     */
    static void radixSort(int arr[], int n) {
        // Find the maximum number to determine the number of digits
        int max = getMax(arr, n);  /**< Get the largest number in the array. */

        // Do counting sort for every digit. exp is 10^i where i is the current digit position
        for (int exp = 1; max / exp > 0; exp *= 10) {  /**< Loop through each digit (units, tens, hundreds, etc.). */
            countingSort(arr, n, exp);  /**< Sort by the current digit. */
        }
    }

    /**
     * @brief Main method to test the Radix Sort implementation.
     * 
     * This method provides an example array and prints the sorted result after calling radixSort().
     */
    public static void main(String args[]) {
        int arr[] = {170, 45, 75, 90, 802, 24, 2, 66};  /**< Example array to be sorted. */
        int n = arr.length;  /**< Get the number of elements in the array. */
        
        radixSort(arr, n);  /**< Call the radixSort function to sort the array. */

        // Print sorted array
        System.out.println("Sorted array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");  /**< Print each element of the sorted array. */
        }
    }
}
