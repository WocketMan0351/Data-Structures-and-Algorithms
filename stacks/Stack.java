package stacks;

public interface Stack<E> {
	/**
	 * Returns the number of elements in the stack
	 * 
	 * @return number of elements in the stack
	 */
	int size();

	/**
	 * Tests whether the stack is empty
	 * 
	 * @return number of elements in the stack
	 */
	boolean isEmpty();

	/**
	 * Inserts an element at the top of the stack
	 * 
	 * @param element the element to be inserted
	 */
	void push(E element);

	/**
	 * Returns but does not remove, the top element from the stack
	 * 
	 * @return top element in the stack (or null if empty)
	 */
	E top();

	/**
	 * Removes and returns the top element from the stack
	 * 
	 * @return element removed (or null if empty)
	 */
	E pop();
}
