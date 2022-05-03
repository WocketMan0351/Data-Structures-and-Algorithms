package assignments.stack;

/**
 * A collection of objects that are inserted and removed according to the LIFO
 * (last in - first out) principle. This differs from the java.util.Stack.
 * 
 * @author Cody Worthen
 * @param <E>
 */
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
	 * @param element E
	 * @return void
	 */
	void push(E element);

	/**
	 * Returns but does not remove, the top element from the stack
	 * 
	 * 
	 * @return E
	 */
	E top();

	/**
	 * Removes and returns the top element from the stack
	 * 
	 * @return E
	 */
	E pop();
}
