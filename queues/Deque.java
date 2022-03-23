package queues;

/**
 * Interface for a doubled-ended queue: a collection of elements that can be
 * inserted and removed at both ends.
 * 
 * @author Cody Worthen
 *
 */
public interface Deque<E> {
	/**
	 * Returns the number of elements in the deque.
	 */
	int size();

	/**
	 * Tests whether the deque is empty.
	 */
	boolean isEmpty();

	/**
	 * Returns, but does not remove, the first element of the deque (null if empty).
	 * 
	 * @return E element
	 */
	E first();

	/**
	 * Returns, but does not remove, the last element of the deque (null if empty).
	 * 
	 * @return
	 */
	E last();

	/**
	 * Inserts element e at the front of the deque.
	 * 
	 * @param E element
	 */
	void addFirst(E e);

	/**
	 * Inserts element e at the back of the deque.
	 * 
	 * @param element
	 */
	void addLast(E e);

	/**
	 * Removes and returns the first element in the deque (null if empty).
	 * 
	 * @return E element
	 */
	E removeFirst();

	/**
	 * Removes and returns the last element of the deque (null if empty).
	 * 
	 * @return E element
	 */
	E removeLast();
}
