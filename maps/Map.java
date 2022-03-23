package maps;

import priority_queues.Entry;

public interface Map<K, V> {
	/**
	 * Returns the number of entries in the Map.
	 * 
	 * @return int
	 */
	int size();

	/**
	 * Returns a boolean indicating whether the Map is empty.
	 * 
	 * @return boolean
	 */
	boolean isEmpty();

	/**
	 * Returns the value v associate with key k, if such an entry exists; otherwise
	 * returns null.
	 * 
	 * @param key
	 * @return value
	 */
	V get(K k);

	/**
	 * If the Map does not have an entry with key equal to k, adds the entry (k, v)
	 * to the Map and returns null; otherwise replaces the existing value of the
	 * entry with with v and returns the old value.
	 * 
	 * @param k
	 * @param v
	 * @return value
	 */
	V put(K k, V v);

	/**
	 * Removes from the map the entry with key equal to k, and returns its value; if
	 * the Map has no such entry, returns null.
	 * 
	 * @param k
	 * @return value
	 */
	V remove(K k);

	/**
	 * Returns an iterable collection containing all the keys stored in the Map.
	 * 
	 * @return Iterable
	 */
	Iterable<K> keySet();

	/**
	 * Returns an iterable collection containing all the values of entries stored in
	 * the Map (with repetition if multiple keys map to the same value).
	 * 
	 * @return Iterable
	 */
	Iterable<V> values();

	/**
	 * Returns an iterable collection containing all the key-value entries in the
	 * Map.
	 * 
	 * @return Iterable
	 */
	Iterable<Entry<K, V>> entrySet();
}
