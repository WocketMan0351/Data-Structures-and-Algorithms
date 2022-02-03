package stacks;

import java.util.EmptyStackException;

public class MatchingBrackets {

	public static void main(String[] args) {
		String str = "())";
		System.out.println(isMatched(str));
	}

	/**
	 * 
	 * @param expression the string to be checked
	 * @return true if the string's parenthesis and bracket pairs match up
	 */
	public static boolean isMatched(String expression) {
		final String opening = "({[";
		final String closing = ")}]";

		Stack<Character> buffer = new LinkedStack<>();

		// TESTS EMPTY STACK EXCEPTION
		System.out.println("Testing empty stack exception...");
		try {
			System.out.println(buffer.top());
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}

		for (char c : expression.toCharArray()) {
			if (opening.indexOf(c) != -1) {
				buffer.push(c);
			} else if (closing.indexOf(c) != -1) {
				if (buffer.isEmpty()) {
					return false;
				}
				if (closing.indexOf(c) != opening.indexOf(buffer.pop())) {
					return false;
				}
			}
		}
		return buffer.isEmpty();
	}

}
