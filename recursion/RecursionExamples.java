package recursion;

import java.util.Scanner;

public class RecursionExamples {

	public static void main(String[] args) {
		System.out.println("1) Factorial");
		System.out.println("2) Fibonacci");
		System.out.println("3) Power1 (less efficient)");
		System.out.println("4) Power2 (more efficient)");
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
				System.out.println(number + " fibonacci = " + fib(number));
				in.close();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			break;
		case "3":
			long before1 = System.nanoTime();
			double answer1 = power1(2, 256);
			long after1 = System.nanoTime();
			System.out.println(answer1 + " took " + (after1 - before1) + "ns");
		case "4":
			long before2 = System.nanoTime();
			double answer2 = power2(2, 256);
			long after2 = System.nanoTime();
			System.out.println(answer2 + " took " + (after2 - before2) + "ns");
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
	 * Returns the nth number in the Fibonacci series
	 * 
	 * @param n an int
	 * @return long
	 */
	public static long fib(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		if (n <= 2) {
			return 1;
		} else {
			return fib(n - 1) + fib(n - 2);
		}
	}

	/**
	 * Returns a number x raised to the n power
	 * 
	 * Runs in O(n) time
	 * 
	 * Memory usage is O(n)
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
	 * Runs in O(log n) time
	 * 
	 * Memory usage is O(log n)
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
