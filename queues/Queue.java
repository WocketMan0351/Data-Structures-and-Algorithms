package queues;

public interface Queue<E> {
	/**
	 * Returns the number of elements in the queue
	 * 
	 * @return int
	 */
	int size();

	/**
	 * Tests whether the queue is empty
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
