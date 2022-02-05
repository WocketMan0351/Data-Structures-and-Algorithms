package stacks;

import java.util.EmptyStackException;

public class StackExercises {

	public static void main(String[] args) {
		Stack<Integer> s = new LinkedStack<>();
		Stack<Integer> t = new LinkedStack<>();

		for (int i = 0; i < 10; i++) {
			s.push(i);
		}

		// transfers all elements from s to t
		System.out.println("size: " + s.size() + " top: " + s.top());
		transfer(s, t);
		System.out.println("size: " + t.size() + " top: " + t.top());

		// recursively empties the stack
		try {
			recursivelyEmptyStack(t);
			System.out.println(t.top());
		} catch (EmptyStackException e) {
			e.printStackTrace();
			System.out.println("stack t is now empty");
		}

	}

	/**
	 * Transfers all elements from stack s to stack t
	 * 
	 * @param Stack<E> s
	 * @param Stack<E> t
	 */
	public static <E> void transfer(Stack<E> s, Stack<E> t) {
		int size = s.size();

		for (int i = 0; i < size; i++) {
			t.push(s.pop());
		}
	}

	/**
	 * Recursively empties a stack
	 * 
	 * @param <E>
	 * @param <E>
	 * @param Stack<E> s
	 */
	public static <E> Stack<E> recursivelyEmptyStack(Stack<E> s) {
		s.pop();
		if (s.isEmpty()) {
			return s;
		} else {
			return recursivelyEmptyStack(s);
		}
	}

}
