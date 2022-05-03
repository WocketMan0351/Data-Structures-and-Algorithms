package assignments.priorityqueue;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PriorityQueueAssignment {

	public static void main(String[] args) {
		System.out.println("** Method A Test **");
		int[] a = { -2, 4, 3, -5, 7 };
		int aInt = 3;
		System.out.println("Input: " + Arrays.toString(a) + ", " + aInt);
		System.out.println("Output: " + methodA(a, aInt));

		System.out.println("\n** Method B Test **");
		int[] b = { 20, 14, 35, 26, 123, 53, 34 };
		int bInt = 100;
		System.out.println("Input: " + Arrays.toString(b) + ", " + bInt);
		System.out.println("Output: " + methodB(b, bInt));

		System.out.println("\n** Method C Test ** ");
		int[] c = { 4, 1, 5 };
		int[] c2 = { 3, 2 };
		System.out.println("Input: " + Arrays.toString(c) + ", " + Arrays.toString(c2));
		System.out.print("Output: ");
		methodC(c, c2);

		System.out.println("\n\n** Method D Test ** ");
		int[] d = { 1, 7, 3, 10, 34, 5, 8 };
		int dInt = 4;
		System.out.println("Input: " + Arrays.toString(d) + ", " + dInt);
		System.out.println("Output: " + methodD(d, dInt));
	}

	/**
	 * Given an array a (containing a set of integers) and an integer k, iteratively
	 * replaces the smallest element in the set, m, by -m, k times. Computes and
	 * returns the sum of all elements after k modifications.
	 * 
	 * @param a int[]
	 * @param k int
	 * @return the sum of all elements after k modifications
	 */
	public static int methodA(int[] a, int k) {
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for (int i = 0; i < a.length; i++) {
			q.add(a[i]);
		}
		for (int j = 0; j < k; j++) {
			int smallest = q.remove();
			q.add(smallest * -1);
		}
		int sum = 0;
		while (!q.isEmpty()) {
			sum += q.remove();
		}
		return sum;
	}

	/**
	 * Given an array of toy prices and money available, returns a count of the max
	 * number of toys that can be purchased.
	 * 
	 * @param prices         int[]
	 * @param moneyAvailable int
	 * @return the max number of toys that can be purchased given the cheapest toys
	 *         in prices[] and int moneyAvailable
	 */
	public static int methodB(int[] prices, int moneyAvailable) {
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for (int i = 0; i < prices.length; i++) {
			q.add(prices[i]);
		}
		int numberOfToys = 0;
		int total = 0;
		while (!q.isEmpty() && (total + q.peek()) <= moneyAvailable) {
			total += q.remove();
			numberOfToys++;
		}
		return numberOfToys;
	}

	/**
	 * Given arrays A and B with n and m integers respectively, prints all elements
	 * in sorted order.
	 * 
	 * @param a int[]
	 * @param b int[]
	 */
	public static void methodC(int[] a, int[] b) {
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for (int i = 0; i < a.length; i++) {
			q.add(a[i]);
		}
		for (int i = 0; i < b.length; i++) {
			q.add(b[i]);
		}
		while (!q.isEmpty()) {
			System.out.print(q.remove() + " ");
		}
	}

	/**
	 * Given array A and integer k, returns the k-th maximum element in the array.
	 * 
	 * @param a int[]
	 * @param k int
	 * @return the k-th maximum element in the array
	 */
	public static int methodD(int[] a, int k) {
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for (int i = 0; i < a.length; i++) {
			q.add(a[i]);
		}
		for (int i = 0; i < a.length - k; i++) {
			q.remove();
		}
		return q.remove();
	}

}
