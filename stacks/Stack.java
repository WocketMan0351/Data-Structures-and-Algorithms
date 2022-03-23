package stacks;

public interface Stack<E> {
	/**
	 * Returns the number of elements in the stack.
	 * 
	 * @return int
	 */
	int size();

	/**
	 * Tests whether the stack is empty.
	 * 
	 * @return boolean
	 */
	boolean isEmpty();

	/**
	 * Inserts element e at the top of the stack.
	 * 
	 * @param element E
	 * @return void
	 */
	void push(E e);

	/**
	 * Returns but does not remove, the top element from the stack.
	 * 
	 * @return E
	 */
	E top();

	/**
	 * Removes and returns the top element from the stack.
	 * 
	 * @return E
	 */
	E pop();
}
