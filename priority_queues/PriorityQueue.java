package priority_queues;

/**
 * Interface for the priority queue ADT.
 */
public interface PriorityQueue<K, V> {
	/**
	 * Returns the number of entries in the priority queue.
	 * 
	 * @return int
	 */
	int size();

	/**
	 * Returns a boolean indicating whether the priority queue is empty.
	 * 
	 * @return boolean
	 */
	boolean isEmpty();

	/**
	 * Creates an entry with key k and value v in the priority queue.
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws IllegalAccessException
	 */
	Entry<K, V> insert(K key, V value) throws IllegalAccessException;

	/**
	 * Returns (but does not remove) a priority queue entry (k, v) having minimal
	 * key; returns null if the priority queue is empty.
	 * 
	 * @return Entry<K, V>
	 */
	Entry<K, V> min();

	/**
	 * Removes and returns an entry (k, v) having minimal key from the priority
	 * queue; returns null if the priority queue is empty.
	 * 
	 * @return Entry<K, V>
	 */
	Entry<K, V> removeMin();
}
