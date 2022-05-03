package recursion;

import java.util.Arrays;
import java.util.Scanner;

public class RecursionWithArrays {

	public static void main(String[] args) {
		System.out.println("1) Reverse Array");
		System.out.println("2) Binary Search Array");
		System.out.println("3) Linear Sum");
		System.out.println("4) Binary Sum");
		System.out.println("5) Recursively Count '11' Occurrences");
		System.out.print("\nChoose an option: ");

		Scanner input = new Scanner(System.in);
		String option = input.next();

		// DRIVER CODE
		switch (option) {
		case "1":
			int[] numbers = { 1, 2, 3, 4, 5 };
			System.out.println(Arrays.toString(numbers) + "\n");

			System.out.println("Recursive:");
			reverseArray(numbers, 0, numbers.length - 1);
			System.out.println(Arrays.toString(numbers) + "\n");
			reverseArray(numbers, 0, numbers.length - 1);

			System.out.println("Iterative:");
			reverseArrayIterative(numbers, 0, numbers.length - 1);
			System.out.println(Arrays.toString(numbers) + "\n");

			break;
		case "2":
			int[] arr = new int[1000];
			for (int i = 0; i < 1000; i++) {
				arr[i] = i;
			}
			System.out.println(Arrays.toString(arr));
			Scanner in = new Scanner(System.in);
			System.out.print("Enter a target integer: ");
			int target = in.nextInt();
			System.out.println(target + " found: " + binarySearch(arr, target) + "\n");
			in.close();
			break;
		case "3":
			int[] values = { 10, 10, 10, 10 };
			System.out.println(Arrays.toString(values));
			System.out.println("Sum: " + linearSum(values, values.length));
			break;
		case "4":
			int[] values2 = { 10, 10, 10, 10 };
			System.out.println(Arrays.toString(values2));
			System.out.println("Sum: " + binarySum(values2, 0, values2.length - 1));
		case "5":
			int[] values3 = { 11, 5, 11, 17, 3 };
			System.out.println(Arrays.toString(values3));
			System.out.println("11 occurs " + array11(values3, 0) + " time(s)");
		default:
			break;
		}
	}

	/**
	 * Reverses an array recursively
	 * 
	 * Has tail recursion, as its recursive call is its last step. Can be easily
	 * converted to be iterative, as shown in reverseArrayIterative()
	 * 
	 * @param arr  an int[]
	 * @param low  an int
	 * @param high an int
	 * @return void
	 */
	private static void reverseArray(int[] arr, int low, int high) {
		if (low < high) {
			int temp = arr[low];
			arr[low] = arr[high];
			arr[high] = temp;
			reverseArray(arr, low + 1, high - 1);
		}
	}

	/**
	 * Reverses an array iteratively
	 * 
	 * @param arr  an int[]
	 * @param low  an int
	 * @param high an int
	 * @return void
	 */
	private static void reverseArrayIterative(int[] arr, int low, int high) {
		do {
			int temp = arr[low];
			arr[low] = arr[high];
			arr[high] = temp;
			low = low + 1;
			high = high - 1;
		} while (low < high);
	}

	/**
	 * Public method with a cleaner interface for binarySearch
	 * 
	 * @param data
	 * @param target
	 * @return
	 */
	private static boolean binarySearch(int[] data, int target) {
		return binarySearch(data, target, 0, data.length - 1);
	}

	/**
	 * Binary search method that runs in O(log n) time for sorted arrays
	 * 
	 * Returns true if the given array contains the target
	 * 
	 * @param data   an int[]
	 * @param target an int
	 * @param low    an int
	 * @param high   an int
	 * @return boolean
	 */
	private static boolean binarySearch(int[] data, int target, int low, int high) {
		if (low > high) {
			return false;
		} else {
			int mid = (low + high) / 2;
			if (target == data[mid]) {
				return true;
			} else if (target < data[mid]) {
				return binarySearch(data, target, low, mid - 1);
			} else {
				return binarySearch(data, target, mid + 1, high);
			}
		}
	}

	/**
	 * Returns the sum of the first n integers of the given array
	 * 
	 * Runs in O(n) time and uses O(n) memory
	 * 
	 * Uses linear recursion
	 * 
	 * @param data an int[]
	 * @param n    an int
	 * @return int
	 */
	private static int linearSum(int[] data, int n) {
		if (n == 0) {
			return 0;
		} else {
			return linearSum(data, n - 1) + data[n - 1];
		}
	}

	/**
	 * Returns the sum of the sub array data[low] through data[high] inclusive
	 * 
	 * Runs in O(n) time and uses O(log n) memory
	 * 
	 * Uses binary recursion
	 * 
	 * @param data an int[]
	 * @param n    an int
	 * @return int
	 */
	private static int binarySum(int[] data, int low, int high) {
		if (low > high) {
			return 0;
		} else if (low == high) {
			return data[low];
		} else {
			int mid = (low + high) / 2;
			return binarySum(data, low, mid) + binarySum(data, mid + 1, high);
		}
	}

	private static int array11(int[] nums, int index) {
		if (index > nums.length - 1) {
			return 0;
		} else if (nums[index] == 11) {
			return 1 + array11(nums, index + 1);
		} else {
			return array11(nums, index + 1);
		}
	}

}
