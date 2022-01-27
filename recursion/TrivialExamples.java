package recursion;

public class TrivialExamples {

	public static void main(String[] args) {
		System.out.println(count7(717));
		System.out.println(changePi("abcpixyz"));
	}

	/**
	 * Count the number of occurrences of the digit '7' in an integer
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
	 * Replaces occurrences of "pi" in a String with "3.14"
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

}
