package recursion;

public class Palindrome {

	public static void main(String[] args) {
		System.out.println(isPalindrome("racecar"));
	}

	public static boolean isPalindrome(String str) {
		int length = str.length();

		if (length <= 1) {
			return true;
		}

		if (str.charAt(0) == str.charAt(length - 1)) {
			return isPalindrome(str.substring(1, length - 1));
		}

		return false;
	}

}
