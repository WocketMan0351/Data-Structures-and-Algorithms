package recursion;

import java.util.Arrays;
import java.util.Scanner;

public class RecursionWithArrays {

	public static void main(String[] args) {
		System.out.println("1) Reverse Array");
		System.out.println("2) Binary Search Array");
		System.out.println("3) Linear Sum");
		System.out.println("4) Binary Sum");
		System.out.print("\nChoose an option: ");

		Scanner input = new Scanner(System.in);
		String option = input.next();

		switch (option) {
		case "1":
			int[] numbers = { 1, 2, 3, 4, 5 };
			System.out.println(Arrays.toString(numbers));
			reverseArray(numbers, 0, numbers.length - 1);
			System.out.println(Arrays.toString(numbers));
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
			System.out.println(target + " found: " + binarySearch(arr, target, 0, arr.length - 1) + "\n");
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
		default:
			break;
		}
	}

	/**
	 * 
	 * @param arr  an int[]
	 * @param low  an int
	 * @param high an int
	 * @return void
	 */
	public static void reverseArray(int[] arr, int low, int high) {
		if (low < high) {
			int temp = arr[low];
			arr[low] = arr[high];
			arr[high] = temp;
			reverseArray(arr, low + 1, high - 1);
		}
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
	public static boolean binarySearch(int[] data, int target, int low, int high) {
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
	 * Uses linear recursion
	 * 
	 * @param data an int[]
	 * @param n    an int
	 * @return int
	 */
	public static int linearSum(int[] data, int n) {
		if (n == 0) {
			return 0;
		} else {
			return linearSum(data, n - 1) + data[n - 1];
		}
	}

	/**
	 * Returns the sum of the subarry data[low] through data[high] inclusive
	 * 
	 * Uses binary recursion
	 * 
	 * @param data an int[]
	 * @param n    an int
	 * @return int
	 */
	public static int binarySum(int[] data, int low, int high) {
		if (low > high) {
			return 0;
		} else if (low == high) {
			return data[low];
		} else {
			int mid = (low + high) / 2;
			return binarySum(data, low, mid) + binarySum(data, mid + 1, high);
		}
	}

}
