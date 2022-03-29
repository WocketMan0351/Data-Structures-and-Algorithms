package maps;

import priority_queues.Entry;

public interface SortedMap<K, V> extends Map<K, V> {

	/**
	 * Returns the entry with the smallest key value (or null, if the map is empty).
	 * 
	 * @return Entry
	 */
	Entry<K, V> firstEntry();

	/**
	 * Returns the entry with the largest key value (or null, if the map is empty).
	 * 
	 * @return Entry
	 */
	Entry<K, V> lastEntry();

	/**
	 * Returns the entry with the least key value greater than or equal to k (or
	 * null, if no such entry exists).
	 * 
	 * @return Entry
	 */
	Entry<K, V> ceilingEntry(K k);

	/**
	 * Returns the entry with the greatest key value less than or equal to k (or
	 * null, if no such entry exists).
	 * 
	 * @return Entry
	 */
	Entry<K, V> floorEntry(K k);

	/**
	 * Returns the entry with the greatest value strictly less than k (or null, if
	 * no such entry exists).
	 * 
	 * @return Entry
	 */
	Entry<K, V> lowerEntry(K k);

	/**
	 * Returns the entry with the least key value strictly greater than k (or null,
	 * if no such entry exists).
	 * 
	 * @return Entry
	 */
	Entry<K, V> higherEntry(K k);

	/**
	 * Returns an iteration of all entries with key greater than or equal to k1, but
	 * strictly less than k2.
	 * 
	 * @return Entry
	 */
	Iterable<Entry<K, V>> subMap(K k1, K k2);
}
