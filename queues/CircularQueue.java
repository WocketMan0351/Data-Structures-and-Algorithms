package queues;

public interface CircularQueue<E> extends Queue<E> {
	/**
	 * Rotates the front element of the queue to the back of the queue. Does nothing
	 * if the queue is empty.
	 * 
	 */
	void rotate();
}
