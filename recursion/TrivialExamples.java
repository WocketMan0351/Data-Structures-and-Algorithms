package recursion;

public class TrivialExamples {

	public static void main(String[] args) {
		System.out.println(count7(71777));
		System.out.println(changePi("abcpixyz"));
		System.out.println(endX("xXBelxla is aX cute dog Xx"));
	}

	/**
	 * Recursively counts the number of occurrences of the digit '7' in an integer
	 * 
	 * @param n int
	 * @return
	 */
	public static int count7(int n) {
		if (n == 0) {
			return 0;
		} else if (n % 10 == 7) {
			return count7(n / 10) + 1;
		} else {
			return count7(n / 10);
		}
	}

	/**
	 * Recursively replaces occurrences of "pi" in a String with "3.14"
	 * 
	 * @param str String
	 * @return String
	 */
	public static String changePi(String str) {
		if (str.length() < 2) {
			return str;
		} else if (str.substring(0, 2).equalsIgnoreCase("pi")) {
			return "3.14" + changePi(str.substring(2));
		} else {
			return str.substring(0, 1) + changePi(str.substring(1));
		}
	}

	/**
	 * Recursively constructs a new string where all occurrences of "x" have been
	 * moved to the end of the String.
	 * 
	 * @param str String
	 * @return String
	 */
	public static String endX(String str) {
		if (str.equals("")) {
			return str;
		} else if (str.substring(0, 1).equalsIgnoreCase("x")) {
			return endX(str.substring(1)) + str.substring(0, 1);
		} else {
			return str.substring(0, 1) + endX(str.substring(1));
		}
	}

}
