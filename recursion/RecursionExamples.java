package recursion;

import java.util.Arrays;
import java.util.Scanner;

public class RecursionExamples {

	public static void main(String[] args) {
		System.out.println("1) Factorial");
		System.out.println("2) Binary Search");
		System.out.print("\nChoose an option: ");

		Scanner input = new Scanner(System.in);
		String option = input.next();

		switch (option) {
		case "1":
			try {
				Scanner in = new Scanner(System.in);
				System.out.print("Enter an integer: ");
				int number = in.nextInt();
				System.out.println(number + "! = " + factorial(number) + "\n");
				in.close();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
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
		default:
			break;
		}
	}

	/**
	 * Calculates factorial recursively.
	 * 
	 * @param n an int
	 * @return n!
	 */
	public static int factorial(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		} else if (n == 1) {
			return 1;
		}
		return n * factorial(n - 1);
	}

	/**
	 * binary search method that runs in O(log n) time for sorted arrays
	 * 
	 * @param data   an array of type int
	 * @param target the value to search for
	 * @param low    the integer index of the low position
	 * @param high   the integer index of the high position
	 * @return true if the target is found in the indicated portion of the area
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

}
