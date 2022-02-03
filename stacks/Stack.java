package stacks;

public interface Stack<E> {
	/**
	 * Returns the number of elements in the stack
	 * 
	 * @return int
	 */
	int size();

	/**
	 * Tests whether the stack is empty
	 * 
	 * @return boolean
	 */
	boolean isEmpty();

	/**
	 * Inserts an element at the top of the stack
	 * 
	 * @param E element
	 * @return void
	 */
	void push(E element);

	/**
	 * Returns but does not remove, the top element from the stack
	 * 
	 * @return E element
	 */
	E top();

	/**
	 * Removes and returns the top element from the stack
	 * 
	 * @return E element
	 */
	E pop();
}
