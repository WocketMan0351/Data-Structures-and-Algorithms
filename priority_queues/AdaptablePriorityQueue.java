package priority_queues;

public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V> {
	/**
	 * Removes entry e from the priority queue.
	 * 
	 * @param entry
	 */
	void remove(Entry<K, V> e);

	/**
	 * Replaces the key of entry e with key k.
	 * 
	 * @param entry
	 * @param key
	 */
	void replaceKey(Entry<K, V> e, K k);

	/**
	 * Replaces the value of entry e with value v.
	 * 
	 * @param entry
	 * @param value
	 */
	void replaceValue(Entry<K, V> e, V v);
}
