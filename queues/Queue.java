package queues;

/**
 * Interface for a single-ended queue: a sequence of elements that can only be
 * added at the rear and removed form the front.
 * 
 * @author Cody Worthen
 *
 * @param <E>
 */
public interface Queue<E> {
	/**
	 * Returns the number of elements in the queue
	 * 
	 * @return int
	 */
	int size();

	/**
	 * Returns a boolean indicating whether the Queue is empty.
	 * 
	 * @return boolean
	 */
	boolean isEmpty();

	/**
	 * Inserts an element at the rear of the queue
	 * 
	 * @param E element
	 * @return void
	 */
	void enqueue(E element);

	/**
	 * Returns, but does not remove, the first element (null if empty)
	 * 
	 * @return E element
	 */
	E first();

	/**
	 * Removes and returns the first element of the queue (null if empty)
	 * 
	 * @return E element
	 */
	E dequeue();
}
