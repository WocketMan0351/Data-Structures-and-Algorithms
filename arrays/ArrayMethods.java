package arrays;

import java.util.Arrays;

public class ArrayMethods {

	public static void main(String[] args) {
		// insertion sort
		char[] chars = { 'g', 'd', 'e', 'a', 'x', 'l', 'b', 'c', 'f', 'y', 'z' };
		insertionSort(chars);
		System.out.println("insertionSort(): " + Arrays.toString(chars));

		// STATIC METHODS
		System.out.println("\n*** STATIC METHODS ***\n");
		int[] ints = { 5, 3, 7, 55, 6667, 87, 34, 2, 1, 999 };

		// fill()
		int[] arr = new int[10];
		Arrays.fill(arr, 21); // fills every index of arr with 21
		System.out.println("fill(): " + Arrays.toString(arr));

		// copyOf
		int[] copiedArray = Arrays.copyOf(ints, ints.length + 2); // excess filled with default values
		System.out.println("copyOf(): " + Arrays.toString(copiedArray));

		// sort
		Arrays.sort(copiedArray); // O(n log (n))
		System.out.println("sort(): " + Arrays.toString(copiedArray));

		// binarySearch()
		System.out.println("binarySearch(): 87 is at index " + Arrays.binarySearch(copiedArray, 87)); // searches a
																										// SORTED ARRAY,
		// returns index of 87

		// equals
		System.out.println("equals(): " + Arrays.equals(ints, copiedArray)); // returns true if A and B have same values
																				// in same
																				// order
	}

	// worse case O(n^2), best case O(n)
	public static void insertionSort(char[] data) {
		int n = data.length;

		for (int k = 1; k < n; k++) {
			char current = data[k];

			int j = k;

			while (j > 0 && data[j - 1] > current) {
				data[j] = data[j - 1];
				j--;
			}

			data[j] = current;
		}
	}
}
