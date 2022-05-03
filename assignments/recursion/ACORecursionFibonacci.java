package assignments.recursion;
/**
 * Implements the recursive definition of the Fibonacci sequence.
 * 
 * @author Cody Worthen
 *
 */
public class ACORecursionFibonacci {

	public static void main(String[] args) {
		System.out.println("0th Fibonacci number: " + fibAt(0));
		System.out.println("1st Fibonacci number: " + fibAt(1));
		System.out.println("7th Fibonacci number: " + fibAt(7));
	}

	/**
	 * Recursively calculates and returns the nth term of the Fibonacci sequence.
	 * 
	 * Runs in O(n^2) time.
	 * 
	 * The number of calls doubles at least every other integer n is raised by
	 * 
	 * @param index int
	 * @return int
	 */
	private static int fibAt(int index) {
		if (index == 0) {
			return 0;
		} else if (index == 1) {
			return 1;
		} else {
			return fibAt(index - 1) + fibAt(index - 2);
		}
	}

}
