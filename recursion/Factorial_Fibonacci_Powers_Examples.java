package recursion;

import java.util.Arrays;
import java.util.Scanner;

public class Factorial_Fibonacci_Powers_Examples {

	public static void main(String[] args) {
		System.out.println("1) Factorial");
		System.out.println("2) Binary Fibonacci");
		System.out.println("3) Linear Fibonacci");
		System.out.println("4) Power Methods");
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
			try {
				Scanner in = new Scanner(System.in);
				System.out.print("Enter and integer: ");
				int number = in.nextInt();
				System.out.println(number + " fibonacci = " + binaryFib(number));
				in.close();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			break;
		case "3":
			try {
				Scanner in = new Scanner(System.in);
				System.out.print("Enter an integer: ");
				int number = in.nextInt();
				System.out.println(number + " fibonacci = " + Arrays.toString(linearFib(number)));
				in.close();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			break;
		case "4":
			System.out.println("\n2^256:");
			long before1 = System.nanoTime();
			double answer1 = power1(2, 256);
			long after1 = System.nanoTime();
			System.out.println("O(n): " + answer1 + " took " + (after1 - before1) + "ns");

			long before2 = System.nanoTime();
			double answer2 = power2(2, 256);
			long after2 = System.nanoTime();
			System.out.println("O(log n): " + answer2 + " took " + (after2 - before2) + "ns");
			break;
		default:
			break;
		}
	}

	/**
	 * Returns n factorial
	 * 
	 * @param n an int
	 * @return int
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
	 * Returns the nth number in the Fibonacci series using binary recursion
	 * 
	 * Runs in O(2^n) time
	 * 
	 * @param n an int
	 * @return long
	 */
	public static long binaryFib(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		if (n <= 2) {
			return 1;
		} else {
			return binaryFib(n - 1) + binaryFib(n - 2);
		}
	}

	/**
	 * Returns the nth number in the Fibonacci series using linear recursion
	 * 
	 * @param n an int
	 * @return long[]
	 */
	public static long[] linearFib(int n) {
		if (n <= 1) {
			long[] answer = { n, 0 };
			return answer;
		} else {
			long[] temp = linearFib(n - 1);
			long[] answer = { temp[0] + temp[1], temp[0] };
			return answer;
		}
	}

	/**
	 * Returns a number x raised to the n power
	 * 
	 * Runs in O(n) time and uses O(n) memory
	 * 
	 * @param x a double
	 * @param n an int
	 * @return double
	 */
	public static double power1(double x, int n) {
		if (n == 0) {
			return 1;
		} else {
			return x * power1(x, n - 1);
		}
	}

	/**
	 * Returns a number x raised to the n power
	 * 
	 * Runs in O(log n) time and uses O(log n) memory
	 * 
	 * Each time we make a recursive call, we halve the value of n. The number of
	 * times we can halve n while n > 1 is O(log n). Each individual invocation of
	 * the method runs in constant time.
	 * 
	 * Therefore, the method is O(log n).
	 * 
	 * @param x a double
	 * @param n an int
	 * @return double
	 */
	public static double power2(double x, int n) {
		if (n == 0) {
			return 1;
		} else {
			double partial = power2(x, n / 2);
			double result = partial * partial;
			if (n % 2 == 1) {
				result *= x;
			}
			return result;
		}
	}

}
