package assignments.recursion;

/**
 * Contains a two recursive methods. One checks if a String is a palindrome, and
 * the other finds the longest palindrome in a given String.
 * 
 * @author Cody Worthen
 *
 */
public class ACORecursionPalindrome {

	public static void main(String[] args) {
		System.out.println("abcde: " + isPalindrome("abcde"));
		System.out.println("aba: " + isPalindrome("aba"));
		System.out.println("XabaX: " + isPalindrome("XabaX"));
		System.out.println("abcba: " + isPalindrome("abcba"));
		System.out.println("a: " + isPalindrome("a"));
		System.out.println(": " + isPalindrome(""));
		System.out.println("abbbbbba: " + isPalindrome("abbbbbba"));

		System.out.println("longest in abcabbbbbaa is " + longestPalindrome("abcabbbbbaa"));
	}

	/**
	 * Recursively checks if a String is a palindrome
	 * 
	 * @param word String
	 * @return boolean
	 */
	public static boolean isPalindrome(String word) {
		int length = word.length();
		if (length <= 1) {
			return true;
		}
		if (word.charAt(0) == word.charAt(length - 1)) {
			return isPalindrome(word.substring(1, length - 1));
		} else {
			return false;
		}
	}

	/**
	 * Recursively returns the longest palindrome found in a String
	 * 
	 * @param word String
	 * @return String
	 */
	public static String longestPalindrome(String word) {
		if (isPalindrome(word)) {
			return word;
		} else if (isPalindrome(word.substring(0, word.length() - 1))) {
			return word.substring(0, word.length() - 1);
		} else if (isPalindrome(word.substring(1))) {
			return word.substring(1);
		} else {
			String longest = (longestPalindrome(word.substring(0, word.length() - 1))
					.length() > longestPalindrome(word.substring(1)).length())
							? longestPalindrome(word.substring(0, word.length() - 1))
							: longestPalindrome(word.substring(1));
			return longest;
		}
	}

}
