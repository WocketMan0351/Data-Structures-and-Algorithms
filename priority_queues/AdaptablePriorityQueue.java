package priority_queues;

public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V> {
	/**
	 * Removes the entry from the priority queue.
	 * 
	 * @param entry
	 */
	void remove(Entry<K, V> entry);

	/**
	 * Replaces the key of existing entry.
	 * 
	 * @param entry
	 * @param key
	 */
	void replaceKey(Entry<K, V> entry, K key);

	/**
	 * Replaces the value of existing entry.
	 * 
	 * @param entry
	 * @param value
	 */
	void replaceValue(Entry<K, V> entry, V value);
}
